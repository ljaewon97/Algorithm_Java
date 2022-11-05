package boj.p3;

public class P3_12844_XOR {
	static Reader in = new Reader();
	static int[] arr, tree, lazy;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		arr = new int[N];
		tree = new int[4*N];
		lazy = new int[4*N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		init(1, 0, N-1);
		
		M = in.nextInt();
		
		while(M-- > 0) {
			int t = in.nextInt();
			
			if(t == 1) {
				int i = in.nextInt();
				int j = in.nextInt();
				int k = in.nextInt();
				
				update(i, j, k, 1, 0, N-1);
			}
			else {
				int i = in.nextInt();
				int j = in.nextInt();
				
				sb.append(query(i, j, 1, 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int init(int n, int nl, int nr) {
		if(nl == nr) return tree[n] = arr[nl];
		
		int mid = (nl + nr) / 2;
		return tree[n] = init(2*n, nl, mid) ^ init(2*n+1, mid+1, nr);
	}
	
	static int update(int l, int r, int v, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) return tree[n];
		if(l <= nl && nr <= r) {
			lazy[n] = v;
			propagation(n, nl, nr);
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		return tree[n] = update(l, r, v, 2*n, nl, mid) ^ update(l, r, v, 2*n+1, mid+1, nr);
	}
	
	static int query(int l, int r, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < l || r < nl) return 0;
		if(l <= nl && nr <= r) return tree[n];
		
		int mid = (nl + nr) / 2;
		return query(l, r, 2*n, nl, mid) ^ query(l, r, 2*n+1, mid+1, nr);
	}
	
	static void propagation(int n, int nl, int nr) {
		if(lazy[n] != 0) {
			if((nr-nl+1) % 2 == 1) tree[n] ^= lazy[n];
			if(nl != nr) {
				lazy[2*n] ^= lazy[n];
				lazy[2*n+1] ^= lazy[n];
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
