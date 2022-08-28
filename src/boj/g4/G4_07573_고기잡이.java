package boj.g4;

public class G4_07573_고기잡이 {
	static Reader in = new Reader();
	static int[][] fishes;
	static int N, I, M, ans;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		I = in.nextInt() / 2;
		M = in.nextInt();
		
		fishes = new int[M][2];
		
		for(int i = 0; i < M; i++) {
			fishes[i][0] = in.nextInt() - 1;
			fishes[i][1] = in.nextInt() - 1;
		}
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 1; k < I; k++) {
					if(fishes[j][0] < fishes[i][0] || fishes[j][0] > fishes[i][0] + k || fishes[i][1] < fishes[j][1] || fishes[i][1] > fishes[j][1] + I - k) continue;
					fishnet(fishes[i][0], fishes[j][1], k, I-k);
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void fishnet(int r, int c, int dr, int dc) {
		int cnt = 0;
		
		for(int i = 0; i < M; i++) {
			int fr = fishes[i][0];
			int fc = fishes[i][1];
			
			if(r <= fr && fr <= r + dr && c <= fc && fc <= c + dc) cnt++;
		}
		
		ans = Math.max(ans, cnt);
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