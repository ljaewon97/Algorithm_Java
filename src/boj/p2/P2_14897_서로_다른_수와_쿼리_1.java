package boj.p2;

import java.util.HashMap;
import java.util.Map;

public class P2_14897_서로_다른_수와_쿼리_1 {
	static Reader in = new Reader();
	static int[] arr, nxt;
	static int[][] tree;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		arr = new int[N];
		nxt = new int[N];
		tree = new int[4*N][];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		Map<Integer, Integer> nextLoc = new HashMap<>();
		
		for(int i = N-1; i >= 0; i--) {
			if(!nextLoc.containsKey(arr[i])) {
				nxt[i] = Integer.MAX_VALUE;
			}
			else {
				nxt[i] = nextLoc.get(arr[i]);
			}
			nextLoc.put(arr[i], i);
		}
		
		nextLoc.clear();
		
		build(1, 0, N-1);

		M = in.nextInt();
		
		for(int i = 0; i < M; i++) {
			int l = in.nextInt() - 1;
			int r = in.nextInt() - 1;
			
			sb.append(query(l, r, 1, 0, N-1)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int[] build(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = new int[] {nxt[nl]};
		}
		
		int mid = (nl + nr) / 2;
		int[] la = build(2*n, nl, mid);
		int[] ra = build(2*n+1, mid+1, nr);
		
		return tree[n] = merge(la, ra);
	}
	
	static int query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return 0;
		}
		
		if(l <= nl && nr <= r) {
			return binary(tree[n], r);
		}
		
		int mid = (nl + nr) / 2;
		return query(l, r, 2*n, nl, mid) + query(l, r, 2*n+1, mid+1, nr);
	}
	
	static int binary(int[] a, int v) {
		int l = 0, m = 0;
		int r = a.length - 1;
		
		while(l <= r) {
			m = (l + r) / 2;
			
			if(a[m] <= v) l = m + 1;
			else r = m - 1;
		}
		
		return a.length - l;
	}
	
	static int[] merge(int[] la, int[] ra) {
		int ll = la.length;
		int rl = ra.length;
		int[] res = new int[ll+rl];
		int i = 0, li = 0, ri = 0;
		
		while(li < ll && ri < rl) {
			if(la[li] < ra[ri]) res[i++] = la[li++];
			else res[i++] = ra[ri++];
		}
		
		while(li < ll) {
			res[i++] = la[li++];
		}
		
		while(ri < rl) {
			res[i++] = ra[ri++];
		}
		
		return res;
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int idx, size;

		boolean isNumber(byte b) {
			return 47 < b && b < 58;
		}

		byte read() throws Exception {
			if (idx == size) {
				size = System.in.read(buffer, idx = 0, SIZE);
				if (size < 0) {
					buffer[0] = -1;
				}
			}
			return buffer[idx++];
		}

		int nextInt() throws Exception {
			int n = 0;
			byte b;
			while ((b = read()) <= 32);
			do n = (n << 3) + (n << 1) + (b & 15);
			while (isNumber(b = read()));
			return n;
		}
	}
}
