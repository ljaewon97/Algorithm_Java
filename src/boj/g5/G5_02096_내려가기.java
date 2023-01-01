package boj.g5;

public class G5_02096_내려가기 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		int[][][] dp = new int[N][3][2];
		
		for(int i = 0; i < 3; i++) {
			dp[0][i][0] = dp[0][i][1] = in.nextInt();
		}
		
		for(int i = 1; i < N; i++) {
			int num = in.nextInt();
			dp[i][0][0] = Math.min(dp[i-1][0][0], dp[i-1][1][0]) + num;
			dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][1][1]) + num;
			
			num = in.nextInt();
			dp[i][1][0] = Math.min(dp[i-1][0][0], Math.min(dp[i-1][1][0], dp[i-1][2][0])) + num;
			dp[i][1][1] = Math.max(dp[i-1][0][1], Math.max(dp[i-1][1][1], dp[i-1][2][1])) + num;
			
			num = in.nextInt();
			dp[i][2][0] = Math.min(dp[i-1][1][0], dp[i-1][2][0]) + num;
			dp[i][2][1] = Math.max(dp[i-1][1][1], dp[i-1][2][1]) + num;
		}
		
		int max = Math.max(dp[N-1][0][1], Math.max(dp[N-1][1][1], dp[N-1][2][1]));
		int min = Math.min(dp[N-1][0][0], Math.min(dp[N-1][1][0], dp[N-1][2][0]));
		
		System.out.printf("%d %d\n", max, min);
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
