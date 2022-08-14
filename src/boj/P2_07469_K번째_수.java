package boj;

public class P2_07469_K번째_수 {
	static Reader in = new Reader();
	static int[][] tree;
	static int[] arr;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		N = in.nextInt();
		M = in.nextInt();
		
		arr = new int[N];
		tree = new int[4*N][];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		buildTree(1, 0, N-1);
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			int c = in.nextInt() - 1;
			
			sb.append(query(a, b, c)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int[] buildTree(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = new int[] {arr[nl]};
		}
		
		int mid = (nl + nr) / 2;
		int[] la = buildTree(2*n, nl, mid);
		int[] ra = buildTree(2*n+1, mid+1, nr);
		
		return tree[n] = merge(la, ra);
	}

	static int count(int l, int r, int v, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return 0;
		}
		
		if(l <= nl && nr <= r) {
			return binarySearch(tree[n], v);
		}
		
		int mid = (nl + nr) / 2;
		return count(l, r, v, 2*n, nl, mid) + count(l, r, v, 2*n+1, mid+1, nr);
	}
	
	static int[] merge(int[] la, int[] ra) {
		if(la == null) return ra;
		if(ra == null) return la;
		
		int llen = la.length;
		int rlen = ra.length;
		int[] res = new int[llen+rlen];
		int i = 0, li = 0, ri = 0;
		
		while(li < llen && ri < rlen) {
			if(la[li] <= ra[ri]) {
				res[i++] = la[li++];
			}
			else {
				res[i++] = ra[ri++];
			}
		}
		
		while(li < llen) {
			res[i++] = la[li++];
		}
		
		while(ri < rlen) {
			res[i++] = ra[ri++];
		}
		
		return res;
	}
	
	static int binarySearch(int[] arr, int v) {
		int left = 0;
		int right = arr.length - 1;
		int mid = 0;
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			if(arr[mid] <= v) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		
		return left;
	}
	
	static int query(int l, int r, int k) {
		int left = -1000000000;
		int right = 1000000000;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(count(l, r, mid, 1, 0, N-1) <= k) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}

		return left;
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
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
