package Graphs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

//Find strongly connected components of a graph.
//O(E + V)
public class Tarjan {
	List<Integer>[] graph;
	boolean[] visited;
	Stack<Integer> stack;
	int time;
	int[] lowlink;
	List<List<Integer>> components;

	List<List<Integer>> scc(List<Integer>[] graph) {
		int n = graph.length;
		this.graph = graph;
		visited = new boolean[n];
		stack = new Stack<>();
		time = 0;
		lowlink = new int[n];
		components = new ArrayList<>();

		for (int u = 0; u < n; u++)
			if (!visited[u])
				dfs(u);

		return components;
	}

	void dfs(int u) {
		lowlink[u] = time++;
		visited[u] = true;
		stack.add(u);
		boolean isComponentRoot = true;

		for (int v : graph[u]) {
			if (!visited[v])
				dfs(v);
			if (lowlink[u] > lowlink[v]) {
				lowlink[u] = lowlink[v];
				isComponentRoot = false;
			}
		}

		if (isComponentRoot) {
			List<Integer> component = new ArrayList<>();
			while (true) {
				int x = stack.pop();
				component.add(x);
				lowlink[x] = Integer.MAX_VALUE;
				if (x == u)
					break;
			}
			components.add(component);
		}
	}

	public static void main(String[] args) throws IOException {
		in.init(System.in);
		int n = in.nextInt();

		for (int i = 0; i < n; i++) {
			int nV = in.nextInt();
			int nE = in.nextInt();
			List<Integer>[] g = new List[nV];
			
			for (int j = 0; j < g.length; j++) {
				g[j] = new ArrayList<>();
			}
			
			for (int j = 0; j < nE; j++) {
				int start = in.nextInt();
				int end = in.nextInt();
				g[start].add(end);
				//g[end].add(start); //If the graph is not directed.
			}
			
			List<List<Integer>> components = new Tarjan().scc(g);
			//System.out.println(components); //Print array of components.
			//System.out.println(components.size()); //Print number of components.
		}
	}
	
	// Class in only for testing.
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

		static double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}