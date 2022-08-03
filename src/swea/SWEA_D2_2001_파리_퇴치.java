package swea;

public class SWEA_D2_2001_파리_퇴치 {
	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = in.nextInt();
			int M = in.nextInt();
			int ans = 0, temp = 0;
			int[][] sum = new int[N+1][N+1];
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + in.nextInt();
					if(i >= M && j >= M) {
						temp = sum[i][j] - sum[i-M][j] - sum[i][j-M] + sum[i-M][j-M];
						if(temp > ans) {
							ans = temp;
						}
					}
				}
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
}

class Reader {
	final int SIZE = 1 << 13;
	byte[] buffer = new byte[SIZE];
	int index, size;

	int nextInt() throws Exception {
		int n = 0;
		byte c;
		while ((c = read()) <= 32)
			;
		do
			n = (n << 3) + (n << 1) + (c & 15);
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