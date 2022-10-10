package boj.p2;

import java.util.Arrays;

public class P2_17411_가장_긴_증가하는_부분_수열_6 {
	static Reader in = new Reader();
	static int[] arr, arr2, arr3, C;
	static Node[] tree;
	static final int MOD = 1000000007;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		arr = new int[N];
		arr2 = new int[N];
		arr3 = new int[N];
		C = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
			arr2[i] = arr[i];
		}
		
		Arrays.sort(arr2);
		
		arr3[0] = arr2[0];
		int size = 1;
		
		for(int i = 0; i < N; i++) {
			if(i != N-1 && arr2[i] != arr2[i+1]) arr3[size++] = arr2[i+1];
		}
		
		for(int i = 0; i < N; i++) {
			int l = 0;
			int r = size;
			
			while(l < r) {
				int mid = (l + r) >> 1;
			
				if(arr3[mid] >= arr[i]) r = mid;
				else l = mid + 1;
			}
			
			C[i] = r;
		}
		
		tree = new Node[4*size];
		
		init(size);
		
		for(int i = 0; i < N; i++) {
			Node temp = query(0, C[i]-1, 1, 0, size-1);
			update(C[i], new Node(temp.len+1, Math.max(temp.cnt, 1)), 1, 0, size-1);
		}
		
		System.out.printf("%d %d\n", tree[1].len, tree[1].cnt);
	}
	
	static void init(int size) {
		for(int i = 0; i < 4*size; i++) {
			tree[i] = new Node(0, 0);
		}
	}
	
	static Node merge(Node a, Node b) {
		if(a.len > b.len) return a;
		else if(a.len < b.len) return b;
		else return new Node(a.len, (a.cnt + b.cnt) % MOD);
	}
	
	static Node update(int idx, Node node, int n, int nl, int nr) {
		if(idx < nl || nr < idx) return tree[n];
		
		if(nl == nr) return tree[n] = merge(tree[n], node);
		
		int mid = (nl + nr) / 2;
		Node left = update(idx, node, 2*n, nl, mid);
		Node right = update(idx, node, 2*n+1, mid+1, nr);
		
		return tree[n] = merge(left, right);
	}
	
	static Node query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl || l > r) return new Node(0, 0);
		
		if(l <= nl && nr <= r) return tree[n];
		
		int mid = (nl + nr) / 2;
		Node left = query(l, r, 2*n, nl, mid);
		Node right = query(l, r, 2*n+1, mid+1, nr);
		
		return merge(left, right);
	}
	
	static class Node {
		int len, cnt;
		
		public Node(int len, int cnt) {
			this.len = len;
			this.cnt = cnt;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return neg ? -n : n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}
