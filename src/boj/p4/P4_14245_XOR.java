package boj.p4;

public class P4_14245_XOR {
	static Reader in = new Reader();
	static int[] arr, tree, lazy;
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		n = in.nextInt();
		
		arr = new int[n];
		tree = new int[4*n];
		lazy = new int[4*n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		
		m = in.nextInt();
		
		while(m-- > 0) {
			int t = in.nextInt();
			
			if(t == 1) {
				int a = in.nextInt();
				int b = in.nextInt();
				int c = in.nextInt();
				
				update(a, b, c, 1, 0, n-1);
			}
			else {
				int a = in.nextInt();
				
				sb.append(arr[a] ^ query(a, 1, 0, n-1)).append("\n");
			}
		}
		
		System.out.println(sb);
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
		return update(l, r, v, 2*n, nl, mid) ^ update(l, r, v, 2*n+1, mid+1, nr);
	}
	
	static int query(int i, int n, int nl, int nr) {
		propagation(n, nl, nr);
		
		if(nr < i || i < nl) return 0;
		if(nl == nr) return tree[n];
		
		int mid = (nl + nr) / 2;
		return query(i, 2*n, nl, mid) ^ query(i, 2*n+1, mid+1, nr);
	}
	
	static void propagation(int n, int nl, int nr) {
		if(lazy[n] != 0) {
			tree[n] ^= lazy[n];
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
