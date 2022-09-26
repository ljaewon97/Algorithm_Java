package boj.p2;

public class P2_25639_수열과_최대_상승_쿼리 {
	static Reader in = new Reader();
	static final int MAX = 1000000001;
	static final int MIN = -1000000001;
	static int[] ARR = {MAX, MIN, MIN};
	static int[] arr;
	static int[][] tree;
	static int N, Q;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		arr = new int[N];
		tree = new int[4*N][];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		build(1, 0, N-1);
		
		Q = in.nextInt();
		
		for(int i = 0; i < Q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			if(a == 1) {
				update(b-1, c, 1, 0, N-1);
			}
			else {
				sb.append(query(b-1, c-1, 1, 0, N-1)[2]).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int[] build(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = new int[] {arr[nl], arr[nl], 0};
		}
		
		int mid = (nl + nr) / 2;
		int[] la = build(2*n, nl, mid);
		int[] ra = build(2*n+1, mid+1, nr);
		
		return tree[n] = new int[] {Math.min(la[0], ra[0]), Math.max(la[1], ra[1]), Math.max(ra[1] - la[0], Math.max(la[2], ra[2]))};
	}
	
	static int[] update(int k, int x, int n, int nl, int nr) {
		if(nr < k || k < nl) {
			return tree[n];
		}
		
		if(nl == nr) {
			tree[n][0] = x;
			tree[n][1] = x;
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		int[] la = update(k, x, 2*n, nl, mid);
		int[] ra = update(k, x, 2*n+1, mid+1, nr);
		
		tree[n][0] = Math.min(la[0], ra[0]);
		tree[n][1] = Math.max(la[1], ra[1]);
		tree[n][2] = Math.max(ra[1] - la[0], Math.max(la[2], ra[2]));
		return tree[n];
	}
	
	static int[] query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return ARR;
		}
		
		if(l <= nl && nr <= r) {
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		int[] la = query(l, r, 2*n, nl, mid);
		int[] ra = query(l, r, 2*n+1, mid+1, nr);

		return new int[] {Math.min(la[0], ra[0]), Math.max(la[1], ra[1]), Math.max(ra[1] - la[0], Math.max(la[2], ra[2]))};
	}
	
	private static class Reader {
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
