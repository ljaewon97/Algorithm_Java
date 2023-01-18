package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_22251_빌런_호석 {
	static int[][] digits, dist;
	static int[] led;
	static int N, K, P, X;
	
	public static void main(String[] args) throws IOException {
		digits = new int[10][];
		dist = new int[10][10];
		
		digits[0] = new int[] {1,1,1,1,1,1,0};
		digits[1] = new int[] {0,0,0,0,1,1,0};
		digits[2] = new int[] {1,0,1,1,0,1,1};
		digits[3] = new int[] {1,0,0,1,1,1,1};
		digits[4] = new int[] {0,1,0,0,1,1,1};
		digits[5] = new int[] {1,1,0,1,1,0,1};
		digits[6] = new int[] {1,1,1,1,1,0,1};
		digits[7] = new int[] {1,0,0,0,1,1,0};
		digits[8] = new int[] {1,1,1,1,1,1,1};
		digits[9] = new int[] {1,1,0,1,1,1,1};
		
		for(int i = 0; i < 10; i++) {
			for(int j = i+1; j < 10; j++) {
				int d = 0;
				
				for(int k = 0; k < 7; k++) {
					d += Math.abs(digits[i][k]-digits[j][k]);
				}
				
				dist[i][j] = dist[j][i] = d;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		led = new int[K];
		int idx = 0;
		
		int ans = 0;
		
		while(X > 0) {
			led[idx++] = X % 10;
			X /= 10;
		}
		
		for(int i = 1; i <= N; i++) {
			int n = i;
			int cnt = 0;
			
			for(int j = 0; j < K; j++) {
				cnt += dist[led[j]][n%10];
				n /= 10;
			}
			
			if(cnt <= P) ans++;
		}
		
		System.out.println(Math.max(ans-1, 0));
	}
}
