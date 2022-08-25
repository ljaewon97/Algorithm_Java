package boj.p1;

import java.util.Arrays;
import java.util.Comparator;

public class P1_13548_수열과_쿼리_6 {
	static Reader in = new Reader();
	static int[][] queries;
	static int[] arr, counter, maxCnt, ans;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		int max = 0;
		
		arr = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = in.nextInt();
			max = Math.max(max, arr[i]);
		}
		
		M = in.nextInt();
		
		queries = new int[M][3];
		ans = new int[M];
		
		for(int i = 0; i < M; i++) {
			queries[i][0] = i;
			queries[i][1] = in.nextInt();
			queries[i][2] = in.nextInt();
		}
		
		int rootN = (int) Math.sqrt(N);
		
		Arrays.sort(queries, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				int l1 = o1[1] / rootN;
				int l2 = o2[1] / rootN;
				
				return l1 == l2 ? o1[2] - o2[2] : l1 - l2;
			}
		});
		
		counter = new int[max+1];
		maxCnt = new int[N+1];
		maxCnt[0] = N;
		
		int idx = queries[0][0];
		int l = queries[0][1];
		int r = queries[0][2];
		
		for(int i = l; i <= r; i++) {
			counter[arr[i]]++;
		}
		
		int freq = 0;
		
		for(int i = 1; i <= max; i++) {
			maxCnt[0]--;
			maxCnt[counter[i]]++;
			freq = Math.max(freq, counter[i]);
		}
		
		ans[idx] = freq;
		
		for(int i = 1; i < M; i++) {
			idx = queries[i][0];
			int nl = queries[i][1];
			int nr = queries[i][2];
			
			while(nr != r) {
				if(nr > r) {
					r++;
					if(counter[arr[r]] == freq) freq++;
					maxCnt[counter[arr[r]]]--;
					counter[arr[r]]++;
					maxCnt[counter[arr[r]]]++;
				}
				else {
					maxCnt[counter[arr[r]]]--;
					if(counter[arr[r]] == freq && maxCnt[counter[arr[r]]] == 0) freq--;
					counter[arr[r]]--;
					maxCnt[counter[arr[r]]]++;
					r--;
				}
			}
			
			while(nl != l) {
				if(nl > l) {
					maxCnt[counter[arr[l]]]--;
					if(counter[arr[l]] == freq && maxCnt[counter[arr[l]]] == 0) freq--;
					counter[arr[l]]--;
					maxCnt[counter[arr[l]]]++;
					l++;
				}
				else {
					l--;
					if(counter[arr[l]] == freq) freq++;
					maxCnt[counter[arr[l]]]--;
					counter[arr[l]]++;
					maxCnt[counter[arr[l]]]++;
				}
			}

			ans[idx] = freq;
		}
		
		for(int i = 0; i < M; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		System.out.println(sb);
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
