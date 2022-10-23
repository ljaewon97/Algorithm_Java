package boj.s2;

public class S2_14430_자원_캐기 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		int M = in.nextInt();
		
		int[][] dp = new int[N+1][M+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(in.nextInt() == 1) dp[i][j]++;
			}
		}
		
		System.out.println(dp[N][M]);
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
