package boj.s2;

public class S2_17245_서버실 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		int[][] arr = new int[N][N];
		long sum = 0;
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = in.nextInt();
				sum += arr[i][j];
				max = Math.max(max, arr[i][j]);
			}
		}
		
		long half = (sum + 1) / 2;
		int l = 0, r = max;
		int ans = Integer.MAX_VALUE;
		
		while(l <= r) {
			int mid = (l + r) >> 1;
			
			long temp = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					temp += Math.min(arr[i][j], mid);
				}
			}
			
			if(temp >= half) {
				ans = Math.min(ans, mid);
				r = mid - 1;
			}
			else {
				l = mid + 1;
			}
		}
		
		System.out.println(ans);
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
