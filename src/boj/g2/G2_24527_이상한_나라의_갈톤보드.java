package boj.g2;

public class G2_24527_이상한_나라의_갈톤보드 {
	static Reader in = new Reader();
	static double[] tree, lazy;
	static long[] sum;
	static int H, Q, R;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		H = in.nextInt();
		Q = in.nextInt();
		R = in.nextInt();
		
		tree = new double[4*(H+1)];
		lazy = new double[4*(H+1)];
		sum = new long[H+1];
		
		for(int i = 1; i <= H; i++) {
			sum[i] = sum[i-1] + i;
		}
		
		while(Q-- > 0) {
			long a = in.nextLong();
			int b = in.nextInt();
			
			int h = upperBound(a-1);
			
			int r = (int) (H-sum[h]+a);
			int l = r-H-1+h;
			double v = 1.0 * b / (r-l+1);
			
			update(l, r, v, 1, 0, H);
		}
		
		while(R-- > 0) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			
			sb.append(query(a, b, 1, 0, H)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static double update(int l, int r, double v, int n, int nl, int nr) {
		propagate(n, nl, nr);
		
		if(nr < l || r < nl) return tree[n];
		
		if(l <= nl && nr <= r) {
			lazy[n] = v;
			propagate(n, nl, nr);
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		return tree[n] = update(l, r, v, 2*n, nl, mid) + update(l, r, v, 2*n+1, mid+1, nr);
	}
	
	static double query(int l, int r, int n, int nl, int nr) {
		propagate(n, nl, nr);
		
		if(nr < l || r < nl) return 0;
		
		if(l <= nl && nr <= r) return tree[n];
		
		int mid = (nl + nr) / 2;
		return query(l, r, 2*n, nl, mid) + query(l, r, 2*n+1, mid+1, nr);
	}
	
	static void propagate(int n, int nl, int nr) {
		if(lazy[n] != 0) {
			tree[n] += lazy[n] * (nr-nl+1);
			
			if(nl != nr) {
				lazy[2*n] += lazy[n];
				lazy[2*n+1] += lazy[n];
			}
			
			lazy[n] = 0;
		}
	}
	
	static int upperBound(long target) {
		int l = 0;
		int r = H+1;
		
		while(l < r) {
			int mid = (l + r) / 2;
			
			if(sum[mid] > target) r = mid;
			else l = mid + 1;
		}
		
		return r;
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

		long nextLong() throws Exception {
			long n = 0;
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
