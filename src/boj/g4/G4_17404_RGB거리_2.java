package boj.g4;

public class G4_17404_RGB거리_2 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		int[][] arr = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			arr[i][0] = in.nextInt();
			arr[i][1] = in.nextInt();
			arr[i][2] = in.nextInt();
		}
		
		int[][] dp = new int[N][3];
		int ans = Integer.MAX_VALUE;
		
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];
		
		for(int i = 0; i < 3; i++) {
			dp[0][i] = 10000;
			
			for(int j = 1; j < N-1; j++) {
				dp[j][0] = arr[j][0] + Math.min(dp[j-1][1], dp[j-1][2]);
				dp[j][1] = arr[j][1] + Math.min(dp[j-1][0], dp[j-1][2]);
				dp[j][2] = arr[j][2] + Math.min(dp[j-1][0], dp[j-1][1]);
			}
			
			int min = arr[N-1][i] + Math.min(dp[N-2][(i+1)%3], dp[N-2][(i+2)%3]);
			ans = Math.min(min, ans);
			
			dp[0][i] = arr[0][i];
		}
		
		System.out.println(ans);
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
