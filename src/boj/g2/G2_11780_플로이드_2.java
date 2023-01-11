package boj.g2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class G2_11780_플로이드_2 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		final int INF = 1000000000;
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		int[][] fw = new int[n+1][n+1];
		List<Integer>[][] route = new ArrayList[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			Arrays.fill(fw[i], INF);
			fw[i][i] = 0;
			for(int j = 1; j <= n; j++) {
				route[i][j] = new ArrayList<>();
			}
		}
		
		while(m-- > 0) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			fw[a][b] = Math.min(fw[a][b], c);
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(fw[i][k] + fw[k][j] < fw[i][j]) {
						fw[i][j] = fw[i][k] + fw[k][j];
						route[i][j] = new ArrayList<>();
						
						for(int city: route[i][k]) {
							route[i][j].add(city);
						}
						
						route[i][j].add(k);
						
						for(int city: route[k][j]) {
							route[i][j].add(city);
						}
					}
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(fw[i][j] == INF) sb.append("0 ");
				else sb.append(fw[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j || fw[i][j] == INF) sb.append("0\n");
				else {
					sb.append(route[i][j].size()+2).append(" ");
					sb.append(i).append(" ");
					
					for(int city: route[i][j]) {
						sb.append(city).append(" ");
					}
					
					sb.append(j).append("\n");
				}
			}
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
