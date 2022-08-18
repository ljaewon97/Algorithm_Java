package boj.p1;

public class P1_13925_수열과_쿼리_13 {
	static Reader in = new Reader();
	static final long MOD = (long)1e9 + 7;
	static long[] tree, arr;
	static long[][] lazy;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		N = in.nextInt();
		
		arr = new long[N];
		tree = new long[4*N];
		lazy = new long[4*N][2];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextLong();
		}
		
		for(int i = 0; i < 4*N; i++) {
			lazy[i][1] = 1L;
		}
		
		build(1, 0, N-1);
		
		M = in.nextInt();
		
		for(int m = 0; m < M; m++) {
			int type = in.nextInt();
			int x = in.nextInt() - 1;
			int y = in.nextInt() - 1;
			
			if(type <= 3) {
				long v = in.nextLong();
				update(type, x, y, v, 1, 0, N-1);
			}
			else {
				sb.append(query(x, y, 1, 0, N-1)).append("\n");
			}
		}

		System.out.println(sb);
	}
	
	static long build(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = arr[nl];
		}
		
		int mid = (nl + nr) / 2;
		long lv = build(2*n, nl, mid);
		long rv = build(2*n+1, mid+1, nr);
		
		return tree[n] = (lv + rv) % MOD;
	}
	
	static long update(int type, int l, int r, long v, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) {
			return tree[n];
		}
		
		if(l <= nl && nr <= r) {
			if(type == 1) {
				lazy[n][0] = (lazy[n][0] + v) % MOD;
			}
			else if(type == 2) {
				lazy[n][0] = (lazy[n][0] * v) % MOD;
				lazy[n][1] = (lazy[n][1] * v) % MOD;
			}
			else if(type == 3) {
				lazy[n][0] = v;
				lazy[n][1] = 0;
			}
			
			propagation(n, nl, nr);
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		long lv = update(type, l, r, v, 2*n, nl, mid);
		long rv = update(type, l, r, v, 2*n+1, mid+1, nr);
		
		return tree[n] = (lv + rv) % MOD;
	}
	
	static long query(int l, int r, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) {
			return 0;
		}
		
		if(l <= nl && nr <= r) {
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		return (query(l, r, 2*n, nl, mid) + query(l, r, 2*n+1, mid+1, nr)) % MOD;
	}
	
	static void propagation(int n, int nl, int nr) {
		if(lazy[n][0] == 0 && lazy[n][1] == 1) return;
		
		tree[n] = (tree[n] * lazy[n][1] + lazy[n][0] * (nr - nl + 1)) % MOD;
		
		if(nl != nr) {
			lazy[2*n][0] = (lazy[2*n][0] * lazy[n][1] + lazy[n][0]) % MOD;
			lazy[2*n+1][0] = (lazy[2*n+1][0] * lazy[n][1] + lazy[n][0]) % MOD;
			lazy[2*n][1] = (lazy[2*n][1] * lazy[n][1]) % MOD;
			lazy[2*n+1][1] = (lazy[2*n+1][1] * lazy[n][1]) % MOD;
		}
		
		lazy[n][0] = 0;
		lazy[n][1] = 1;
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
			if (neg)
				return -n;
			return n;
		}

		long nextLong() throws Exception {
			long n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if (neg)
				return -n;
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
