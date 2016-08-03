package Dynamic_Programming;

import java.io.*;
import java.util.*;

public class Long_Inc_Substr {

	static int[] LIS(int[] x) {
		int n = x.length;
		int[] p = new int[n];
		int[] m = new int[n + 1];

		int l = 0;
		for (int i = 0; i < n; i++) {
			double lo = 1;
			double hi = l;
			while (lo <= hi) {
				int mid = (int) Math.ceil((lo + hi) / 2);
				if (x[m[mid]] < x[i]) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}

			int newL = (int) lo;
			p[i] = m[newL - 1];
			m[newL] = i;

			if (newL > l) {
				l = newL;
			}
		}
		
		int[] s = new int[l];
		int k = m[l];
		for (int j = l-1; j >= 0; j--) {
			s[j] = x[k];
			k = p[k];
		}
		return s;
	}
}