package boj.p5;

public class P5_01725_히스토그램 {
	static Reader in = new Reader();
	static int[] arr, tree;
	static int N;
	static long ans;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		ans = 0;
		
		arr = new int[N+1];
		tree = new int[4*N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		arr[N] = Integer.MAX_VALUE;
		
		build(1, 0, N-1);
		maxArea(0, N-1);
		
		sb.append(ans).append("\n");
		
		System.out.println(sb);
	}
	
	static int build(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = nl;
		}
		
		int mid = (nl + nr) / 2;
		int lidx = build(2*n, nl, mid);
		int ridx = build(2*n+1, mid+1, nr);
		
		return tree[n] = arr[lidx] <= arr[ridx] ? lidx : ridx;
	}
	
	static int minIdx(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return N;
		}
		
		if(l <= nl && nr <= r) {
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		int lidx = minIdx(l, r, 2*n, nl, mid);
		int ridx = minIdx(l, r, 2*n+1, mid+1, nr);
		
		return arr[lidx] <= arr[ridx] ? lidx : ridx;
	}
	
	static void maxArea(int l, int r) {
		if(l > r) return;
		if(l == r) {
			ans = Math.max(ans, arr[l]);
			return;
		}
		
		int idx = minIdx(l, r, 1, 0, N-1);
		long curArea = (long) arr[idx] * (r - l + 1);
		ans = Math.max(ans, curArea);
		
		maxArea(l, idx-1);
		maxArea(idx+1, r);
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
