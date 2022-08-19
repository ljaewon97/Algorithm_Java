package boj.p2;

import java.util.Arrays;
import java.util.Comparator;

public class P2_13547_수열과_쿼리_5 {
	static Reader in = new Reader();
	static int[] counter;
	static int[][] queries;
	static int[] arr, ans;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		arr = new int[N+1];
		int rootN = (int) Math.sqrt(N);
		int max = 0;
		
		for(int i = 1; i <= N; i++) {
			arr[i] = in.nextInt();
			max = Math.max(max, arr[i]);
		}
		
		M = in.nextInt();
		queries = new int[M][3];
		ans = new int[M];
		
		for(int i = 0; i < M; i++) {
			queries[i][0] = in.nextInt();
			queries[i][1] = in.nextInt();
			queries[i][2] = i;
		}
		
		Arrays.sort(queries, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				int i1 = o1[0] / rootN;
				int i2 = o2[0] / rootN;
				return i1 == i2 ? o1[1] - o2[1] : i1 - i2;
			}
		});
		
		counter = new int[max+1];
		int l = queries[0][0];
		int r = queries[0][1];
		int idx = queries[0][2];
		int cnt = 0;
		
		for(int i = l; i <= r; i++) {
			if(counter[arr[i]] == 0) cnt++;
			counter[arr[i]]++;
		}
		ans[idx] = cnt;

		for(int i = 1; i < M; i++) {
			int nl = queries[i][0];
			int nr = queries[i][1];
			idx = queries[i][2];
			
			while(nr != r) {
				if(nr > r) {
					r++;
					if(counter[arr[r]] == 0) cnt++;
					counter[arr[r]]++;
				}
				else {
					if(counter[arr[r]] == 1) cnt--;
					counter[arr[r]] = Math.max(0, counter[arr[r]]-1);
					r--;
				}
			}
			
			while(nl != l) {
				if(nl > l) {
					if(counter[arr[l]] == 1) cnt--;
					counter[arr[l]] = Math.max(0, counter[arr[l]]-1);
					l++;
				}
				else {
					l--;
					if(counter[arr[l]] == 0) cnt++;
					counter[arr[l]]++;
				}
			}

			ans[idx] = cnt;
		}
		
		for(int i = 0; i < M; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		System.out.println(sb);
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
