package boj.p2;

import java.util.Arrays;

public class P2_08462_배열의_힘 {
	static Reader in = new Reader();
	static int s;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int n = in.nextInt();
		int t = in.nextInt();
		s = (int) Math.sqrt(n) + 1;
		
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		
		Query[] queries = new Query[t];
		
		for(int i = 0; i < t; i++) {
			queries[i] = new Query(i, in.nextInt()-1, in.nextInt()-1);
		}
		
		Arrays.sort(queries);
		
		long[] ans = new long[t];
		int[] count = new int[1000001];
		
		long power = 0;
		Query first = queries[0];
		
		for(int i = first.l; i <= first.r; i++) {
			power -= (long) count[arr[i]] * count[arr[i]] * arr[i];
			count[arr[i]]++;
			power += (long) count[arr[i]] * count[arr[i]] * arr[i];
		}
		
		ans[first.idx] = power;
		int l = first.l, r = first.r;
		
		for(int i = 1; i < t; i++) {
			Query q = queries[i];
			
			while(l != q.l) {
				if(l < q.l) {
					power -= (long) count[arr[l]] * count[arr[l]] * arr[l];
					count[arr[l]]--;
					power += (long) count[arr[l]] * count[arr[l]] * arr[l];
					l++;
				}
				else {
					l--;
					power -= (long) count[arr[l]] * count[arr[l]] * arr[l];
					count[arr[l]]++;
					power += (long) count[arr[l]] * count[arr[l]] * arr[l];
				}
			}
			
			while(r != q.r) {
				if(r < q.r) {
					r++;
					power -= (long) count[arr[r]] * count[arr[r]] * arr[r];
					count[arr[r]]++;
					power += (long) count[arr[r]] * count[arr[r]] * arr[r];
				}
				else {
					power -= (long) count[arr[r]] * count[arr[r]] * arr[r];
					count[arr[r]]--;
					power += (long) count[arr[r]] * count[arr[r]] * arr[r];
					r--;
				}
			}
			
			ans[q.idx] = power;
		}
		
		for(int i = 0; i < t; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static class Query implements Comparable<Query> {
		int idx, l, r, sqrt;
		
		public Query(int idx, int l, int r) {
			this.idx = idx;
			this.l = l;
			this.r = r;
			this.sqrt = l / s;
		}

		@Override
		public int compareTo(Query o) {
			if(this.sqrt != o.sqrt) return this.sqrt - o.sqrt;
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
