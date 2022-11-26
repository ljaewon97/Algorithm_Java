package boj.p2;

public class P2_17353_하늘에서_떨어지는_별 {
	static Reader in = new Reader();
	static int[] arr;
	static long[] tree;
	static long[][] lazy;
	static int N, Q;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		arr = new int[N];
		tree = new long[4*N];
		lazy = new long[4*N][2];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		init(1, 0, N-1);
		
		Q = in.nextInt();
		
		while(Q-- > 0) {
			int a = in.nextInt();
			
			if(a == 1) {
				int l = in.nextInt() - 1;
				int r = in.nextInt() - 1;
				
				update(l, r, 1, 0, N-1);
			}
			else {
				int x = in.nextInt() - 1;
				
				sb.append(query(x, 1, 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static long init(int n, int nl, int nr) {
		if(nl == nr) return tree[n] = arr[nl];
		
		int mid = (nl + nr) / 2;
		return tree[n] = init(2*n, nl, mid) + init(2*n+1, mid+1, nr);
	}
	
	static long update(int l, int r, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) return tree[n];
		
		if(l <= nl && nr <= r) {
			lazy[n][0] = 1;
			lazy[n][1] = nl - l;
			propagation(n, nl, nr);
			
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		return tree[n] = update(l, r, 2*n, nl, mid) + update(l, r, 2*n+1, mid+1, nr);
	}
	
	static long query(int x, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < x || x < nl) return 0;
		
		if(nl == nr) return tree[n];
		
		int mid = (nl + nr) / 2;
		return query(x, 2*n, nl, mid) + query(x, 2*n+1, mid+1, nr);
	}
	
	static void propagation(int n, int nl, int nr) {
		if(lazy[n][0] != 0) {
			int len = nr - nl + 1;
			tree[n] += lazy[n][0] * (len * (len + 1) / 2) + lazy[n][1] * len;
			
			if(nl != nr) {
				int mid = (nl + nr) / 2;
				lazy[2*n][0] += lazy[n][0];
				lazy[2*n+1][0] += lazy[n][0];
				lazy[2*n][1] += lazy[n][1];
				lazy[2*n+1][1] += lazy[n][1] + lazy[n][0] * (mid - nl + 1);
			}
			
			lazy[n][0] = lazy[n][1] = 0;
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
