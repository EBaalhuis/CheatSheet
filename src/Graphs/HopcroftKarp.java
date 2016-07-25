package Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// Hopcroft-Karp algorithm for bipartite maximum matching. 
// O(sqrt(V) * E)
// Note that the size of max matching = size of min cover.
public class HopcroftKarp {

	public static class bipartiteGraph {
		public ArrayList<ArrayList<Integer>> adj;
		public final int xV;
		public final int yV;
		private int[] pair;
		private int[] dist;

		bipartiteGraph(int _xV, int _yV) {
			xV = _xV;
			yV = _yV;
			adj = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < xV + yV + 1; i++) {
				adj.add(new ArrayList<Integer>());
			}
			pair = new int[xV + yV + 1];
			dist = new int[xV + yV + 1];
		}

		public void addEdge(int x, int y) {
			adj.get(x + 1).add(xV + y + 1);
			adj.get(xV + y + 1).add(x + 1);
		}

		private boolean BFS() {
			Queue<Integer> q = new LinkedList<Integer>();
			for (int v = 1; v <= xV; v++) {
				if (pair[v] == 0) {
					dist[v] = 0;
					q.add(v);
				} else {
					dist[v] = Integer.MAX_VALUE;
				}
			}
			dist[0] = Integer.MAX_VALUE;

			while (!q.isEmpty()) {
				int v = q.poll();
				if (dist[v] < dist[0]) {
					for (int u : adj.get(v)) {
						if (dist[pair[u]] == Integer.MAX_VALUE) {
							dist[pair[u]] = dist[v] + 1;
							q.add(pair[u]);
						}
					}
				}
			}
			return dist[0] != Integer.MAX_VALUE;
		}
		
		private boolean DFS(int v) {
			if (v != 0) {
				for (int u : adj.get(v)) {
					if (dist[pair[u]] == dist[v] + 1) {
						if (DFS(pair[u])) {
							pair[u] = v;
							pair[v] = u;
							return true;
						}
					}
				}
				dist[v] = Integer.MAX_VALUE;
				return false;
			}
			return true;
		}

		public int hc() {
			int matching = 0;
			while (BFS()) {
				for (int v = 1; v <= xV; v++) {
					if (pair[v] == 0 && DFS(v)) {
						matching = matching + 1;
					}
				}
			}
			return matching;
		}
	}

	// Main class for example
	public static void main(String[] args) throws IOException {
		in.init(System.in);

		int xV = in.nextInt();
		int yV = in.nextInt();

		bipartiteGraph g = new bipartiteGraph(xV, yV);
		
		int nE = in.nextInt();
		for (int i = 0; i < nE; i++) {
			g.addEdge(in.nextInt(), in.nextInt());
		}

		int matches = g.hc();
		System.out.println(matches);
	}

	// In class for testing only
	static class in {
		static BufferedReader reader;
		static StringTokenizer tokenizer;

		static void init(InputStream input) {
			reader = new BufferedReader(new InputStreamReader(input));
			tokenizer = new StringTokenizer("");
		}

		static String next() throws IOException {
			while (!tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			return tokenizer.nextToken();
		}

		static int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		static long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		static double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}