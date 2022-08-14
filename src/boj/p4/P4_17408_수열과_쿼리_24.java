package boj.p4;

public class P4_17408_수열과_쿼리_24 {
	static Reader in = new Reader();
	static int[][] tree;
	static int[] arr;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		N = in.nextInt();
		
		arr = new int[N];
		tree = new int[4*N][];
		
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
			else {
				int[] temp = query(b-1, c-1, 1, 0, N-1);
				sb.append(temp[0] + temp[1]).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int[] buildTree(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = new int[] {arr[nl], 0};
		}
		
		int mid = (nl + nr) / 2;
		int[] la = buildTree(2*n, nl, mid);
		int[] ra = buildTree(2*n+1, mid+1, nr);
		
		return tree[n] = compareArrays(la, ra);
	}
	
	static int[] update(int idx, int v, int n, int nl, int nr) {
		if(nr < idx || idx < nl) {
			return tree[n];
		}
		
		if(nl == nr) {
			return tree[n] = new int[] {v, 0};
		}
		
		int mid = (nl + nr) / 2;
		int[] la = update(idx, v, 2*n, nl, mid);
		int[] ra = update(idx, v, 2*n+1, mid+1, nr);
		
		return tree[n] = compareArrays(la, ra);
	}
	
	static int[] query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return new int[] {0, 0};
		}
		
		if(l <= nl && nr <= r) {
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		return compareArrays(query(l, r, 2*n, nl, mid), query(l, r, 2*n+1, mid+1, nr));
	}
	
	static int[] compareArrays(int[] la, int[] ra) {
		int m1 = Math.max(la[0], ra[0]);
		int m2 = Math.max(Math.min(la[0], ra[0]), Math.max(la[1], ra[1]));
		
		return new int[] {m1, m2};
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