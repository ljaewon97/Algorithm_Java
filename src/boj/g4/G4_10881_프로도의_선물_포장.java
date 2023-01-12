package boj.g4;

public class G4_10881_프로도의_선물_포장 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		while(T-- > 0) {
			int ans = Integer.MAX_VALUE;
			
			int[][] G = new int[3][2];
			
			for(int i = 0; i < 3; i++) {
				G[i][0] = in.nextInt();
				G[i][1] = in.nextInt();
			}
			
			for(int i = 0; i < 2; i++) {
				for(int j = i+1; j < 3; j++) {
					int k = 3-i-j;
					
					for(int p = 0; p < 2; p++) {
						for(int q = 0; q < 2; q++) {
							int x = G[i][p] + G[j][q];
							int y = Math.max(G[i][1-p], G[j][1-q]);
							
							for(int r = 0; r < 2; r++) {
								ans = Math.min(ans, Math.max(x, G[k][r])*(y+G[k][1-r]));
								ans = Math.min(ans, (x+G[k][r])*Math.max(y, G[k][1-r]));
							}
						}
					}
				}
			}
			
			sb.append(ans).append("\n");
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
