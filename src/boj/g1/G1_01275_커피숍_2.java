package boj.g1;

public class G1_01275_커피숍_2 {
	static Reader in = new Reader();
	static long[] tree;
	static int[] arr;
	static int N, Q;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		Q = in.nextInt();
		
		arr = new int[N];
		tree = new long[4*N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		buildTree(1, 0, N-1);
		
		for(int i = 0; i < Q; i++) {
			int x = in.nextInt() - 1;
			int y = in.nextInt() - 1;
			int a = in.nextInt() - 1;
			int b = in.nextInt();
			
			if(x > y) {
				sb.append(query(y, x, 1, 0, N-1)).append("\n");
			}
			else {
				sb.append(query(x, y, 1, 0, N-1)).append("\n");
			}
			
			update(a, b, 1, 0, N-1);
		}
		
		System.out.println(sb);
	}
	
	static long buildTree(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = arr[nl];
		}
		
		int mid = (nl + nr) / 2;
		long lv = buildTree(2*n, nl, mid);
		long rv = buildTree(2*n+1, mid+1, nr);
		
		return tree[n] = lv + rv;
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
	
	static long update(int idx, int v, int n, int nl, int nr) {
		if(nr < idx || idx < nl) {
			return tree[n];
		}
		
		if(nl == nr) {
			return tree[n] = v;
		}
		
		int mid = (nl + nr) / 2;
		long lv = update(idx, v, 2*n, nl, mid);
		long rv = update(idx, v, 2*n+1, mid+1, nr);
		
		return tree[n] = lv + rv;
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
