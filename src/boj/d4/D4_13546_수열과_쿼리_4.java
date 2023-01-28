package boj.d4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class D4_13546_수열과_쿼리_4 {
	static Reader in = new Reader();
	static Deque<Integer>[] deque;
	static Query[] queries;
	static int[] arr, ans, cnt, cntSq;
	static int N, K, M, sq, sq2;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		K = in.nextInt();
		sq = (int) Math.ceil(Math.sqrt(100000));
		sq2 = sq + 1;
		
		arr = new int[N];
		cnt = new int[101010];
		cntSq = new int[sq2];
		deque = new ArrayDeque[K+1];
		
		for(int i = 1; i <= K; i++) {
			deque[i] = new ArrayDeque<>();
		}
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		M = in.nextInt();
		
		queries = new Query[M];
		ans = new int[M];
		
		for(int i = 0; i < M; i++) {
			int l = in.nextInt()-1;
			int r = in.nextInt()-1;
			
			queries[i] = new Query(i, l, r, l/sq);
		}
		
		Arrays.sort(queries);
		
		int l = queries[0].l;
		int r = queries[0].r;
		
		for(int i = l; i <= r; i++) {
			int n = arr[i];
			if(!deque[n].isEmpty()) minus(n);
			
			deque[n].addLast(i);
			plus(n);
		}
		
		ans[queries[0].idx] = findMax();
		
		for(int i = 1; i < M; i++) {
			Query q = queries[i];
			
			while(q.l < l) {
				int n = arr[--l];
				if(!deque[n].isEmpty()) minus(n);
				
				deque[n].addFirst(l);
				plus(n);
			}
			
			while(r < q.r) {
				int n = arr[++r];
				if(!deque[n].isEmpty()) minus(n);
				
				deque[n].addLast(r);
				plus(n);
			}
			
			while(l < q.l) {
				int n = arr[l];
				minus(n);
				
				deque[n].pollFirst();
				if(!deque[n].isEmpty()) plus(n);
				
				++l;
			}
			
			while(q.r < r) {
				int n = arr[r];
				minus(n);
				
				deque[n].pollLast();
				if(!deque[n].isEmpty()) plus(n);
				
				--r;
			}
			
			ans[q.idx] = findMax();
		}
		
		for(int i = 0; i < M; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void minus(int n) {
		int diff = deque[n].getLast()-deque[n].getFirst();
		cnt[diff]--;
		cntSq[diff/sq]--;
	}
	
	static void plus(int n) {
		int diff = deque[n].getLast()-deque[n].getFirst();
		cnt[diff]++;
		cntSq[diff/sq]++;
	}
	
	static int findMax() {
		for(int i = sq2-1; i >= 0; i--) {
			if(cntSq[i] == 0) continue;
			
			int temp = i*sq;
			for(int j = sq-1; j >= 0; j--) {
				if(cnt[temp+j] > 0) return temp+j;
			}
		}
		
		return 0;
	}
	
	static class Query implements Comparable<Query> {
		int idx, l, r, sq;
		
		public Query(int idx, int l, int r, int sq) {
			this.idx = idx;
			this.l = l;
			this.r = r;
			this.sq = sq;
		}

		@Override
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
