package boj.p2;

import java.util.Arrays;

public class P2_02912_백설공주와_난쟁이 {
	static Reader in = new Reader();
	static Query[] queries;
	static int[] color, cnt, cntArr, ans;
	static int N, C, M, n;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		C = in.nextInt();
		n = (int) Math.sqrt(N);
		
		color = new int[N];
		cnt = new int[C+1];
		cntArr = new int[N+1];
		
		for(int i = 0; i < N; i++) {
			color[i] = in.nextInt();
		}
		
		M = in.nextInt();
		
		queries = new Query[M];
		ans = new int[M];
		
		for(int i = 0; i < M; i++) {
			queries[i] = new Query(i, in.nextInt()-1, in.nextInt()-1);
		}
		
		Arrays.sort(queries);
		
		int l = queries[0].l, r = queries[0].r;
		
		for(int i = l; i <= r; i++) {
			int c = color[i];
			if(cntArr[cnt[c]] != 0) cntArr[cnt[c]]--;
			cnt[c]++;
			cntArr[cnt[c]]++;
		}
		
		ans[queries[0].idx] = getColor(queries[0]);
		
		for(int i = 1; i < M; i++) {
			Query q = queries[i];
			
			while(q.l < l) {
				l--;
				int c = color[l];
				if(cntArr[cnt[c]] != 0) cntArr[cnt[c]]--;
				cnt[c]++;
				cntArr[cnt[c]]++;
			}
			
			while(q.r > r) {
				r++;
				int c = color[r];
				if(cntArr[cnt[c]] != 0) cntArr[cnt[c]]--;
				cnt[c]++;
				cntArr[cnt[c]]++;
			}
			
			while(l < q.l) {
				int c = color[l];
				cntArr[cnt[c]]--;
				cnt[c]--;
				cntArr[cnt[c]]++;
				l++;
			}
			
			while(r > q.r) {
				int c = color[r];
				cntArr[cnt[c]]--;
				cnt[c]--;
				cntArr[cnt[c]]++;
				r--;
			}
			
			ans[q.idx] = getColor(q);
		}
		
		for(int i = 0; i < M; i++) {
			if(ans[i] != -1) {
				sb.append(String.format("yes %d\n", ans[i]));
			}
			else {
				sb.append("no\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int getColor(Query q) {
		int t = (q.r-q.l+1)/2;
		
		for(int i = 1; i <= C; i++) {
			if(cnt[i] > t) return i;
		}
		
		return -1;
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
		final int SIZE = 1 << 15;
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
