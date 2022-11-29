package boj.d5;

public class D5_12858_Range_GCD {
	static Reader in = new Reader();
	static int[] arr;
	static long[] lazy;
	static Node[] tree;
	static int N, Q;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		arr = new int[N];
		lazy = new long[4*N];
		tree = new Node[4*N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		init(1, 0, N-1);
		
		Q = in.nextInt();
		
		while(Q-- > 0) {
			int T = in.nextInt();
			int A = in.nextInt() - 1;
			int B = in.nextInt() - 1;
			
			if(T == 0) {
				sb.append(query(A, B, 1, 0, N-1)).append("\n");
			}
			else {
				update(A, B, T, 1, 0, N-1);
			}
		}
		
		System.out.println(sb);
	}
	
	static Node init(int n, int nl, int nr) {
		if(nl == nr) return tree[n] = new Node(arr[nl], 0);
		
		int mid = (nl + nr) / 2;
		Node left = init(2*n, nl, mid);
		Node right = init(2*n+1, mid+1, nr);
		
		if(left.min < right.min) return tree[n] = new Node(left.min, calcGCD(right.min-left.min, calcGCD(left.gcd, right.gcd)));
		else return tree[n] = new Node(right.min, calcGCD(left.min-right.min, calcGCD(left.gcd, right.gcd)));
	}
	
	static Node update(int l, int r, int v, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) return tree[n];
		
		if(l <= nl && nr <= r) {
			lazy[n] = v;
			propagation(n, nl, nr);
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		Node left = update(l, r, v, 2*n, nl, mid);
		Node right = update(l, r, v, 2*n+1, mid+1, nr);
		
		if(left.min < right.min) return tree[n] = new Node(left.min, calcGCD(right.min-left.min, calcGCD(left.gcd, right.gcd)));
		else return tree[n] = new Node(right.min, calcGCD(left.min-right.min, calcGCD(left.gcd, right.gcd)));
	}
	
	static long query(int l, int r, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) return 0;
		
		if(l <= nl && nr <= r) return calcGCD(tree[n].min, tree[n].gcd);
		
		int mid = (nl + nr) / 2;
		return calcGCD(query(l, r, 2*n, nl, mid), query(l, r, 2*n+1, mid+1, nr));
	}
	
	static void propagation(int n, int nl, int nr) {
		if(lazy[n] != 0) {
			tree[n].min += lazy[n];
			
			if(nl != nr) {
				lazy[2*n] += lazy[n];
				lazy[2*n+1] += lazy[n];
			}
			
			lazy[n] = 0;
		}
	}
	
	static long calcGCD(long a, long b) {
		return b == 0 ? a : calcGCD(b, a % b);
	}
	
	static class Node {
		long min, gcd;
		
		public Node(long min, long gcd) {
			this.min = min;
			this.gcd = gcd;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return n;
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
