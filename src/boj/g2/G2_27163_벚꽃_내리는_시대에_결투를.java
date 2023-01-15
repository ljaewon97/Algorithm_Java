package boj.g2;

import java.util.Arrays;

public class G2_27163_벚꽃_내리는_시대에_결투를 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		final int INF = -1000000001;
		
		int N = in.nextInt();
		int A = in.nextInt();
		int L = in.nextInt();
		
		int[][] aura = new int[N+1][L+1];
		int[][] route = new int[N+1][L+1];
		
		for(int i = 0; i <= N; i++) {
			Arrays.fill(aura[i], INF);
		}
		
		aura[0][L] = A;
		
		for(int i = 0; i < N; i++) {
			int a = in.nextInt();
			int l = in.nextInt();
			
			if(a == -1) {
				for(int j = l+1; j <= L; j++) {
					if(aura[i][j] > aura[i+1][j-l]) {
						aura[i+1][j-l] = aura[i][j];
						route[i+1][j-l] = j+10000;
					}
				}
			}
			else if(l == -1) {
				for(int j = 1; j <= L; j++) {
					if(aura[i][j] == INF) continue;
					int temp = Math.max(aura[i][j]-a, 0);
					
					if(temp > aura[i+1][j]) {
						aura[i+1][j] = temp;
						route[i+1][j] = j;
					}
				}
			}
			else {
				for(int j = 1; j <= L; j++) {
					if(aura[i][j] >= a && aura[i][j]-a > aura[i+1][j]) {
						aura[i+1][j] = aura[i][j]-a;
						route[i+1][j] = j;
					}
					
					if(j > l && aura[i][j] > aura[i+1][j-l]) {
						aura[i+1][j-l] = aura[i][j];
						route[i+1][j-l] = j+10000;
					}
				}
			}
		}
		
		for(int i = 1; i <= L; i++) {
			if(aura[N][i] >= 0) {
				System.out.println("YES");
				
				int col = i;
				
				for(int j = N; j > 0; j--) {
					if(route[j][col] > 10000) {
						sb.append("L");
						col = route[j][col]-10000;
					}
					else {
						sb.append("A");
						col = route[j][col];
					}
				}
				
				System.out.println(sb.reverse());
				return;
			}
		}
		
		System.out.println("NO");
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return neg ? -n : n;
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
