package boj.g1;

public class G1_17435_합성함수와_쿼리 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();

		int m = in.nextInt();
		
		int L = (int) Math.ceil(Math.log(500000)/Math.log(2));
		int[][] spt = new int[m+1][L+1];
		
		for(int i = 1; i <= m; i++) {
			spt[i][0] = in.nextInt();
		}
		
		for(int i = 1; i <= L; i++) {
			for(int j = 1; j <= m; j++) {
				spt[j][i] = spt[spt[j][i-1]][i-1];
			}
		}
		
		int Q = in.nextInt();
		
		while(Q-- > 0) {
			int n = in.nextInt();
			int x = in.nextInt();
			
			for(int i = L; i >= 0; i--) {
				if((n & (1<<i)) != 0) {
					x = spt[x][i];
				}
			}
			
			sb.append(x).append("\n");
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
