package boj.p4;

public class P4_11962_Counting_Haybales {
	static Reader in = new Reader();
	static int[] arr;
	static long[][] tree;
	static long[] lazy;
	static int N, Q;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		Q = in.nextInt();
		
		arr = new int[N];
		tree = new long[4*N][2];
		lazy = new long[4*N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		build(1, 0, N-1);
		
		for(int i = 0; i < Q; i++) {
			int cmd = in.nextInt();
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			
			if(cmd == 13) {
				sb.append(minQuery(a, b, 1, 0, N-1)).append("\n");
			}
			else if(cmd == 0) {
				int c = in.nextInt();
				update(a, b, c, 1, 0, N-1);
			}
			else {
				sb.append(sumQuery(a, b, 1, 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static long[] build(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = new long[] {arr[nl], arr[nl]};
		}
		
		int mid = (nl + nr) / 2;
		long[] la = build(2*n, nl, mid);
		long[] ra = build(2*n+1, mid+1, nr);
		
		return tree[n] = new long[] {la[0]+ra[0], Math.min(la[1], ra[1])};
	}
	
	static long[] update(int l, int r, int v, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) {
			return tree[n];
		}
		
		if(l <= nl && nr <= r) {
			lazy[n] = v;
			propagation(n, nl, nr);
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		long[] la = update(l, r, v, 2*n, nl, mid);
		long[] ra = update(l, r, v, 2*n+1, mid+1, nr);
		
		return tree[n] = new long[] {la[0]+ra[0], Math.min(la[1], ra[1])};
	}
	
	static long sumQuery(int l, int r, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) {
			return 0;
		}
		
		if(l <= nl && nr <= r) {
			return tree[n][0];
		}
		
		int mid = (nl + nr) / 2;
		return sumQuery(l, r, 2*n, nl, mid) + sumQuery(l, r, 2*n+1, mid+1, nr);
	}
	
	static long minQuery(int l, int r, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) {
			return Long.MAX_VALUE;
		}
		
		if(l <= nl && nr <= r) {
			return tree[n][1];
		}
		
		int mid = (nl + nr) / 2;
		return Math.min(minQuery(l, r, 2*n, nl, mid), minQuery(l, r, 2*n+1, mid+1, nr));
	}
	
	static void propagation(int n, int nl, int nr) {
		if(lazy[n] != 0) {
			tree[n][0] += lazy[n] * (nr-nl+1);
			tree[n][1] += lazy[n];
			
			if(nl != nr) {
				lazy[2*n] += lazy[n];
				lazy[2*n+1] += lazy[n];
			}
			
			lazy[n] = 0;
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
