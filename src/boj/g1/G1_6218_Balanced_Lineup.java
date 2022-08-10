package boj.g1;

public class G1_6218_Balanced_Lineup {
	static Reader in = new Reader();
	static int[][] tree;
	static int[] arr;
	static int N, Q;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		Q = in.nextInt();
		
		arr = new int[N];
		tree = new int[4*N][];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		buildTree(1, 0, N-1);
		
		for(int i = 0; i < Q; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			
			int[] res = query(a, b, 1, 0, N-1);
			sb.append(res[1] - res[0]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int[] buildTree(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = new int[] {arr[nl], arr[nl]};
		}
		
		int mid = (nl + nr) / 2;
		int[] lv = buildTree(2*n, nl, mid);
		int[] rv = buildTree(2*n+1, mid+1, nr);
		
		return tree[n] = new int[] {Math.min(lv[0], rv[0]), Math.max(lv[1], rv[1])};
	}
	
	static int[] query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
		}
		
		if(l <= nl && nr <= r) {
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		int[] lv = query(l, r, 2*n, nl, mid);
		int[] rv = query(l, r, 2*n+1, mid+1, nr);
		
		return new int[] {Math.min(lv[0], rv[0]), Math.max(lv[1], rv[1])};
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