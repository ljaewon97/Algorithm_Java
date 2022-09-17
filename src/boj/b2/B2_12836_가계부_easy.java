package boj.b2;

public class B2_12836_가계부_easy {
	static Reader in = new Reader();
	static int N, Q;
	static int[] arr;
	static long[] tree;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		Q = in.nextInt();
		
		arr = new int[N];
		tree = new long[4*N];
		
		for(int i = 0; i < Q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			if(a == 1) {
				add(b-1, c, 1, 0, N-1);
			}
			else {
				sb.append(query(b-1, c-1, 1, 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static long add(int i, int v, int n, int nl, int nr) {
		if(nr < i || i < nl) {
			return tree[n];
		}
		
		if(nl == nr) {
			return tree[n] += v;
		}
		
		int mid = (nl + nr) / 2;
		return tree[n] = add(i, v, 2*n, nl, mid) + add(i, v, 2*n+1, mid+1, nr);
	}
	
	static long query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return 0;
		}
		
		if(l <= nl && nr <= r) {
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		return query(l, r, 2*n, nl, mid) + query(l, r, 2*n+1, mid+1, nr);
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
