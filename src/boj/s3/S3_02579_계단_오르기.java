package boj.s3;

public class S3_02579_계단_오르기 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		int[] arr = new int[N];
		int[][] dp = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		dp[0][0] = arr[0]; dp[0][1] = arr[0];
		
		if(N == 1) {
			System.out.println(arr[0]);
			return;
		}
		
		dp[1][0] = arr[1]; dp[1][1] = arr[0] + arr[1];
		
		for(int i = 2; i < N; i++) {
			dp[i][0] = arr[i] + Math.max(dp[i-2][0], dp[i-2][1]);
			dp[i][1] = arr[i] + dp[i-1][0];
		}
		
		System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));
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
