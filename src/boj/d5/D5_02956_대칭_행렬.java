package boj.d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D5_02956_대칭_행렬 {
	static char[][] ans;
	static int[] count = new int[26];
	static int[] col;
	static int N, K, P;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int odd = 0;
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int alpha = st.nextToken().charAt(0)-'A';
			int n = Integer.parseInt(st.nextToken());
			count[alpha] = n;
			if(n % 2 == 1) odd++;
		}
		
		if(odd > N) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		
		P = Integer.parseInt(br.readLine());
		
		ans = new char[N+1][P+1];
		col = new int[P+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < P; i++) {
			col[i] = Integer.parseInt(st.nextToken());
		}
		
		col[P] = N+1;
		int colIdx = 0;
		
		for(int i = 1; i <= N; i++) {
			odd = 0;
			int x = 0, oddx = 0, a = 0;
			
			for(int j = 25; j >= 0; j--) {
				if(count[j] % 2 == 1) {
					odd++;
					oddx = j;
				}
				if(count[j] != 0) x = j;
			}
			
			if(odd < N+1-i) a = x;
			else a = oddx;
			count[a]--;
			
			boolean validCol = i == col[colIdx];
			if(validCol) ans[i][colIdx] = (char) (65+a);
			
			a = 0;
			int idx = i+1;
			int pos = colIdx + (validCol ? 1 : 0);
			
			while(idx <= N) {
				for(int j = 0; j < 26; j++) {
					if(count[j] > 1) {
						a = j;
						break;
					}
				}
				
				if(validCol) ans[idx][colIdx] = (char) (65+a);
				if(idx == col[pos]) ans[i][pos++] = (char) (65+a);
				
				int nidx = col[pos];
				
				if(validCol) nidx = idx+1;
				nidx = Math.min(nidx, idx+count[a]/2);
				
				count[a] -= (nidx-idx)*2;
				
				idx = nidx;
			}
			
			if(validCol) colIdx++;
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < P; j++) {
				sb.append(ans[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
