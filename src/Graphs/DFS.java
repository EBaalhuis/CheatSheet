package Graphs;

import java.util.*;

public class DFS {
	// Return List<Integer> sorted by DFS traversal
	static List<Integer> dfs(List<Integer>[] g, int start) {
		int n = g.length;
		boolean[] used = new boolean[n];
		List<Integer> res = new ArrayList<>();	
		dfsrec(g, used, res, start);
		return res;
	}

	static void dfsrec(List<Integer>[] g, boolean[] used,
	List<Integer> res, 	int u) {
		used[u] = true;
		for (int v : g[u]) {
			if (!used[v]) {
				dfsrec(g, used, res, v);
			}
		}
		res.add(u);
	}
}
