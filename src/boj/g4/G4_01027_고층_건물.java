package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_01027_고층_건물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		long[] b = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			b[i] = Long.parseLong(st.nextToken());
		}
		
		boolean[][] s = new boolean[N][N];
		
		for(int i = 0; i < N-1; i++) {
			for(int j = i+1; j < N; j++) {
				if(j == i+1) {
					s[i][j] = s[j][i] = true;
					continue;
				}
				
				boolean see = true;
				
				for(int k = i+1; k < j; k++) {
					if(i*b[j]+j*b[k]+k*b[i]-(b[i]*j+b[j]*k+b[k]*i) >= 0) {
						see = false;
						break;
					}
				}
				
				if(see) s[i][j] = s[j][i] = true;
			}
		}
		
		int ans = 0;
		
		for(int i = 0; i < N; i++) {
			int temp = 0;
			
			for(int j = 0; j < N; j++) {
				if(s[i][j]) temp++;
			}
			
			ans = Math.max(ans, temp);
		}
		
		System.out.println(ans);
	}
}
