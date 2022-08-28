package boj.g3;

public class G3_15684_사다리_조작 {
	static Reader in = new Reader();
	static int[][] ladders;
	static int N, M, H, ans = Integer.MAX_VALUE;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		H = in.nextInt();
		
		ladders = new int[H+1][N+1];
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			ladders[a][b] = b+1;
			ladders[a][b+1] = b;
		}
		
		if(countOddLadder() > 3) {
			System.out.println(-1);
			return;
		}
		
		recur(0, 0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static int countOddLadder() {
		int odd = 0;
		
		for(int i = 1; i < N; i++) {
			int cnt = 0;
			
			for(int j = 1; j <= H; j++) {
				if(ladders[j][i] == i+1) cnt++;
			}
			
			if(cnt % 2 == 1) odd++;
		}
		
		return odd;
	}
	
	static void recur(int nth, int start) {
		if(check()) {
			ans = Math.min(ans, nth);
			return;
		}
		
		if(nth == 3) return;
		
		for(int i = 1; i < N; i++) {
			for(int j = 1; j <= H; j++) {
				if((H+1)*i+j <= start) continue;
				
				if(ladders[j][i] == 0 && ladders[j][i+1] == 0) {
					ladders[j][i] = i+1;
					ladders[j][i+1] = i;
					recur(nth+1, (H+1)*i+j+1);
					ladders[j][i] = 0;
					ladders[j][i+1] = 0;
				}
			}
		}
	}
	
	static boolean check() {
		for(int i = 1; i <= N; i++) {
			if(!play(i)) return false;
		}
		
		return true;
	}
	
	static boolean play(int i) {
		int res = i;
		
		for(int a = 1; a <= H; a++) {
			if(ladders[a][res] != 0) {
				res = ladders[a][res];
			}
		}
		
		return res == i;
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if (neg)
				return -n;
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
