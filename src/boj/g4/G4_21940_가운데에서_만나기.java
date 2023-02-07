package boj.g4;

import java.util.Arrays;

public class G4_21940_가운데에서_만나기 {
	static Reader in = new Reader();
	static int[][] fw;
	static final int INF = 1000000000;
	static int N, M, K;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		
		fw = new int[N+1][N+1];
		
		for(int i = 1; i <= N; ++i) {
			Arrays.fill(fw[i], INF);
			fw[i][i] = 0;
		}
		
		while(M-- > 0) {
			int A = in.nextInt();
			int B = in.nextInt();
			int T = in.nextInt();
			
			fw[A][B] = Math.min(fw[A][B], T);
		}
		
		for(int k = 1; k <= N; ++k) {
			for(int i = 1; i <= N; ++i) {
				for(int j = 1; j <= N; ++j) {
					fw[i][j] = Math.min(fw[i][j], fw[i][k] + fw[k][j]);
				}
			}
		}
		
		K = in.nextInt();
		int[] max = new int[N+1];
		boolean[] city = new boolean[N+1];
		int min = INF;
		Arrays.fill(max, INF);
		
		while(K-- > 0) {
			city[in.nextInt()] = true;
		}
		
		for(int i = 1; i <= N; ++i) {
			boolean canVisit = true;
			int temp = 0;
			
			for(int j = 1; j <= N; ++j) {
				if(city[j]) {
					if(fw[i][j] == INF || fw[j][i] == INF) {
						canVisit = false;
						break;
					}
					
					temp = Math.max(temp, fw[i][j] + fw[j][i]);
				}
			}
			
			if(!canVisit) continue;
			
			max[i] = temp;
			min = Math.min(min, temp);
		}
		
		for(int i = 1; i <= N; ++i) {
			if(max[i] == min) sb.append(i).append(" ");
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
