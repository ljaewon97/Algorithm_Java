package boj.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P4_16978_수열과_쿼리_22 {
	static Reader in = new Reader();
	static long[] tree, arr;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		arr = new long[N];
		tree = new long[4*N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextLong();
		}
		
		buildTree(1, 0, N-1);
		
		Deque<int[]> updates = new LinkedList<>();
		List<int[]> queries = new ArrayList<>();
		
		M = in.nextInt();
		
		int idx = 0;
		for(int m = 0; m < M; m++) {
			int a = in.nextInt();
			
			if(a == 1) {
				int i = in.nextInt() - 1;
				int v = in.nextInt();
				
				updates.add(new int[] {i, v});
			}
			else {
				int k = in.nextInt();
				int i = in.nextInt() - 1;
				int j = in.nextInt() - 1;
				
				queries.add(new int[] {k, i, j, idx});
				idx++;
			}
		}
		
		Collections.sort(queries, (o1, o2) -> o1[0] - o2[0]);
		
		int cnt = 0;
		long[] ans = new long[idx];
		
		for(int[] info: queries) {
			int k = info[0];
			
			while(k != cnt) {
				int[] temp = updates.poll();
				update(temp[0], temp[1], 1, 0, N-1);
				cnt++;
			}
			
			ans[info[3]] = query(info[1], info[2], 1, 0, N-1);
		}
		
		for(int i = 0; i < idx; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static long buildTree(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = arr[nl];
		}
		
		int mid = (nl + nr) / 2;
		long lv = buildTree(2*n, nl, mid);
		long rv = buildTree(2*n+1, mid+1, nr);
		
		return tree[n] = lv + rv;
	}
	
	static long query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return 0;
		}
		
		if(l <= nl && nr <= r) {
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		return query(l, r, 2*n, nl, mid) + query(l, r, 2*n+1, mid+1, nr);
	}
	
	static long update(int idx, int v, int n, int nl, int nr) {
		if(nr < idx || idx < nl) {
			return tree[n];
		}
		
		if(nl == nr) {
			return tree[n] = v;
		}
		
		int mid = (nl + nr) / 2;
		long lv = update(idx, v, 2*n, nl, mid);
		long rv = update(idx, v, 2*n+1, mid+1, nr);
		
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

		long nextLong() throws Exception {
			long n = 0;
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
