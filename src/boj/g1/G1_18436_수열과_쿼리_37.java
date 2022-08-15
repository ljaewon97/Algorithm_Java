package boj.g1;

public class G1_18436_수열과_쿼리_37 {
	static Reader in = new Reader();
	static int[] tree, arr;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		arr = new int[N];
		tree = new int[4*N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		buildTree(1, 0, N-1);
		
		M = in.nextInt();
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			if(a == 1) {
				update(b-1, c, 1, 0, N-1);
			}
			else if(a == 2) {
				sb.append(query(b-1, c-1, 1, 0, N-1)).append("\n");
			}
			else {
				sb.append(c-b+1-query(b-1, c-1, 1, 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int buildTree(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = arr[nl] % 2 == 0 ? 1 : 0;
		}
		
		int mid = (nl + nr) / 2;
		int lv = buildTree(2*n, nl, mid);
		int rv = buildTree(2*n+1, mid+1, nr);
		
		return tree[n] = lv + rv;
	}
	
	static int query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return 0;
		}
		
		if(l <= nl && nr <= r) {
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		return query(l, r, 2*n, nl, mid) + query(l, r, 2*n+1, mid+1, nr);
	}
	
	static int update(int idx, int v, int n, int nl, int nr) {
		if(nr < idx || idx < nl) {
			return tree[n];
		}
		
		if(nl == nr) {
			return tree[n] = v % 2 == 0 ? 1 : 0;
		}
		
		int mid = (nl + nr) / 2;
		int lv = update(idx, v, 2*n, nl, mid);
		int rv = update(idx, v, 2*n+1, mid+1, nr);
		
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
