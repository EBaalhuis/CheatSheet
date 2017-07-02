package Data_Structures;

class Prefix_Tree {
	private V root;

	public Prefix_Tree() {
		root = new V();
	}

	public void insert(String word) {
		V p = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (p.arr[index] == null) {
				V temp = new V();
				p.arr[index] = temp;
				p = temp;
			} else {
				p = p.arr[index];
			}
		}
		p.isEnd = true;
	}

	public V searchNode(String s) {
		V p = root;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = c - 'a';
			if (p.arr[index] != null) {
				p = p.arr[index];
			} else {
				return null;
			}
		}

		return (p == root) ? null : p;
	}
	
	public static class V {
		V[] arr;
		boolean isEnd;

		public V() {
			this.arr = new V[26];
		}

	}
}