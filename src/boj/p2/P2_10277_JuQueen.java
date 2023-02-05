package boj.p2;

public class P2_10277_JuQueen {
	static Reader in = new Reader();
	static int[] mintree, maxtree, lazy;
	static int C, N, O;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		C = in.nextInt();
		N = in.nextInt();
		O = in.nextInt();
		
		mintree = new int[C<<2];
		maxtree = new int[C<<2];
		lazy = new int[C<<2];
		
		while(O-- > 0) {
			int t = in.nextInt();
			
			// change
			if(t == 382475) {
				int X = in.nextInt();
				int S = in.nextInt();
				int cur = rangeMin(X, X, 1, 0, C-1);
				
				if(cur + S > N) S = N - cur;
				else if(cur + S < 0) S = -cur;
				
				change(X, S, 1, 0, C-1);
				sb.append(S).append("\n");
			}
			// groupchange
			else if(t == 535938443) {
				int A = in.nextInt();
				int B = in.nextInt();
				int S = in.nextInt();
				
				if(S >= 0) {
					int max = rangeMax(A, B, 1, 0, C-1);
					if(max + S > N) S = N - max;
				}
				else {
					int min = rangeMin(A, B, 1, 0, C-1);
					if(min + S < 0) S = -min;
				}
				
				groupchange(A, B, S, 1, 0, C-1);
				sb.append(S).append("\n");
			}
			// state
			else {
				int X = in.nextInt();
				
				sb.append(rangeMin(X, X, 1, 0, C-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void change(int i, int v, int n, int nl, int nr) {
		propagate(n, nl, nr);
		
		if(nr < i || i < nl) return;
		if(nl == nr) {
			mintree[n] += v;
			maxtree[n] += v;
			return;
		}
		
		int mid = (nl + nr) >> 1;
		change(i, v, 2*n, nl, mid);
		change(i, v, 2*n+1, mid+1, nr);
		
		mintree[n] = Math.min(mintree[2*n], mintree[2*n+1]);
		maxtree[n] = Math.max(maxtree[2*n], maxtree[2*n+1]);
	}
	
	static void groupchange(int l, int r, int v, int n, int nl, int nr) {
		propagate(n, nl, nr);
		
		if(nr < l || r < nl) return;
		if(l <= nl && nr <= r) {
			lazy[n] = v;
			propagate(n, nl, nr);
			return;
		}
		
		int mid = (nl + nr) >> 1;
		groupchange(l, r, v, 2*n, nl, mid);
		groupchange(l, r, v, 2*n+1, mid+1, nr);
		
		mintree[n] = Math.min(mintree[2*n], mintree[2*n+1]);
		maxtree[n] = Math.max(maxtree[2*n], maxtree[2*n+1]);
	}
	
	static int rangeMin(int l, int r, int n, int nl, int nr) {
		propagate(n, nl, nr);
		
		if(nr < l || r < nl) return Integer.MAX_VALUE;
		if(l <= nl && nr <= r) return mintree[n];
		
		int mid = (nl + nr) >> 1;
		return Math.min(rangeMin(l, r, 2*n, nl, mid), rangeMin(l, r, 2*n+1, mid+1, nr));
	}
	
	static int rangeMax(int l, int r, int n, int nl, int nr) {
		propagate(n, nl, nr);
		
		if(nr < l || r < nl) return Integer.MIN_VALUE;
		if(l <= nl && nr <= r) return maxtree[n];
		
		int mid = (nl + nr) >> 1;
		return Math.max(rangeMax(l, r, 2*n, nl, mid), rangeMax(l, r, 2*n+1, mid+1, nr));
	}
	
	static void propagate(int n, int nl, int nr) {
		if(lazy[n] != 0) {
			mintree[n] += lazy[n];
			maxtree[n] += lazy[n];
			
			if(nl != nr) {
				lazy[2*n] += lazy[n];
				lazy[2*n+1] += lazy[n];
			}
			
			lazy[n] = 0;
		}
	}
	
	static class Node {
		int min, max;
		
		public Node(int min, int max) {
			this.min = min;
			this.max = max;
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
			while ((c = read()) > 32);
			return neg ? -n : n;
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
