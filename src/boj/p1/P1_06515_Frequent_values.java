package boj.p1;

import java.util.Arrays;

public class P1_06515_Frequent_values {
	static Reader in = new Reader();
	static Query[] queries;
	static int[] paint, ans, bright, count;
	static int N, Q, n;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			N = in.nextInt();
			if(N == 0) break;
			Q = in.nextInt();
			n = (int) Math.sqrt(N);
			
			paint = new int[N];
			queries = new Query[Q];
			ans = new int[Q];
			bright = new int[200001];
			count = new int[100001];
			
			for(int i = 0; i < N; i++) {
				paint[i] = in.nextInt() + 100000;
			}
			
			for(int i = 0; i < Q; i++) {
				queries[i] = new Query(i, in.nextInt()-1, in.nextInt()-1);
			}
			
			Arrays.sort(queries);
			
			int l = queries[0].l, r = queries[0].r, max = 0;
			
			for(int i = l; i <= r; i++) {
				int c = paint[i];
				if(count[bright[c]] != 0) count[bright[c]]--;
				bright[c]++;
				count[bright[c]]++;
				max = Math.max(max, bright[c]);
			}
			
			ans[queries[0].idx] = max;
			
			for(int i = 1; i < Q; i++) {
				Query q = queries[i];
				
				while(q.l < l) {
					l--;
					int c = paint[l];
					if(count[bright[c]] != 0) count[bright[c]]--;
					bright[c]++;
					count[bright[c]]++;
					max = Math.max(max, bright[c]);
				}
				
				while(q.r > r) {
					r++;
					int c = paint[r];
					if(count[bright[c]] != 0) count[bright[c]]--;
					bright[c]++;
					count[bright[c]]++;
					max = Math.max(max, bright[c]);
				}
				
				while(l < q.l) {
					int c = paint[l];
					if(bright[c] == max && count[bright[c]] == 1) max--;
					count[bright[c]]--;
					bright[c]--;
					count[bright[c]]++;
					l++;
				}
				
				while(r > q.r) {
					int c = paint[r];
					if(bright[c] == max && count[bright[c]] == 1) max--;
					count[bright[c]]--;
					bright[c]--;
					count[bright[c]]++;
					r--;
				}
				
				ans[q.idx] = max;
			}
			
			for(int i = 0; i < Q; i++) {
				sb.append(ans[i]).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static class Query implements Comparable<Query> {
		int idx, l, r, sq;
		
		public Query(int idx, int l, int r) {
			this.idx = idx;
			this.l = l;
			this.r = r;
			this.sq = l/n;
		}

		public int compareTo(Query o) {
			if(this.sq != o.sq) return this.sq - o.sq;
			return this.r - o.r;
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
