package boj.p5;

public class P5_07578_공장 {
	static Reader in = new Reader();
	static int[] arr;
	static long[] tree;
	static int N;
	
	public static void main(String[] args) throws Exception {
		long ans = 0;
		
		N = in.nextInt();
		
		arr = new int[N];
		tree = new long[4*N];
		
		int[] map = new int[1000001];
		
		for(int i = 0; i < N; i++) {
			map[in.nextInt()] = i;
		}
		
		for(int i = 0; i < N; i++) {
			arr[map[in.nextInt()]] = i;
		}
		
		for(int i = 0; i < N; i++) {
			update(arr[i], 1, 0, N-1);
			if(arr[i]+1 > N-1) continue;
			ans += query(arr[i]+1, N-1, 1, 0, N-1);
		}
		
		System.out.println(ans);
	}
	
	static long update(int i, int n, int nl, int nr) {
		if(nr < i || i < nl) return tree[n];
		
		if(nl == nr) return ++tree[n];
		
		int mid = (nl + nr) / 2;
		return tree[n] = update(i, 2*n, nl, mid) + update(i, 2*n+1, mid+1, nr);
	}
	
	static long query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) return 0;
		
		if(l <= nl && nr <= r) return tree[n];
		
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
