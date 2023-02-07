package boj.p4;

public class P4_02835_인기도_조사 {
	static Reader in = new Reader();
	static long[] tree, lazy;
	static final int MAX = 86400;
	static int N, Q;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		tree = new long[4*MAX];
		lazy = new long[4*MAX];
		
		while(N-- > 0) {
			int start = convert(in.nextInt(), in.nextInt(), in.nextInt());
			int end = convert(in.nextInt(), in.nextInt(), in.nextInt());
			
			if(start > end) {
				update(start, MAX-1, 1, 0, MAX-1);
				update(0, end, 1, 0, MAX-1);
			}
			else {
				update(start, end, 1, 0, MAX-1);
			}
		}
		
		Q = in.nextInt();
		
		while(Q-- > 0) {
			int start = convert(in.nextInt(), in.nextInt(), in.nextInt());
			int end = convert(in.nextInt(), in.nextInt(), in.nextInt());
			
			if(start > end) {
				long pop = query(start, MAX-1, 1, 0, MAX-1) + query(0, end, 1, 0, MAX-1);
				long len = MAX-start+end+1;
				
				sb.append(1.0*pop/len).append("\n");
			}
			else {
				long pop = query(start, end, 1, 0, MAX-1);
				long len = end-start+1;
				
				sb.append(1.0*pop/len).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int convert(int h, int m, int s) {
		return 3600*h+60*m+s;
	}
	
	static long update(int l, int r, int n, int nl, int nr) {
		propagate(n, nl, nr);
		
		if(nr < l || r < nl) return tree[n];
		
		if(l <= nl && nr <= r) {
			++lazy[n];
			propagate(n, nl, nr);
			return tree[n];
		}
		
		int mid = (nl + nr) >> 1;
		return tree[n] = update(l, r, 2*n, nl, mid) + update(l, r, 2*n+1, mid+1, nr);
	}
	
	static long query(int l, int r, int n, int nl, int nr) {
		propagate(n, nl, nr);
		
		if(nr < l || r < nl) return 0;
		
		if(l <= nl && nr <= r) return tree[n];
		
		int mid = (nl + nr) >> 1;
		return query(l, r, 2*n, nl, mid) + query(l, r, 2*n+1, mid+1, nr);
	}
	
	static void propagate(int n, int nl, int nr) {
		if(lazy[n] != 0) {
			tree[n] += (nr-nl+1) * lazy[n];
			
			if(nl != nr) {
				lazy[2*n] += lazy[n];
				lazy[2*n+1] += lazy[n];
			}
			
			lazy[n] = 0;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while (!isNumber(c = read()));
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
