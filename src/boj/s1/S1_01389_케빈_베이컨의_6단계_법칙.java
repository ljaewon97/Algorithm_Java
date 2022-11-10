package boj.s1;

import java.util.Arrays;

public class S1_01389_케빈_베이컨의_6단계_법칙 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		int M = in.nextInt();
		
		int[][] dp = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], 1000000);
			dp[i][i] = 0;
		}
		
		while(M-- > 0) {
			int a = in.nextInt();
			int b = in.nextInt();
			dp[a][b] = dp[b][a] = 1;
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int ans = 0;
		
		for(int i = 1; i <= N; i++) {
			int sum = 0;
			for(int j = 1; j <= N; j++) {
				sum += dp[i][j];
			}
			if(sum < min) {
				min = sum;
				ans = i;
			}
		}
		
		System.out.println(ans);
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
