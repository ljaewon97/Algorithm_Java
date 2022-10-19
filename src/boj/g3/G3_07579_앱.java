package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_07579_앱 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] bytes = new int[N];
		int[] costs = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			bytes[i] = Integer.parseInt(st.nextToken());
		}
		
		int maxCost = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
			maxCost += costs[i];
		}
		
		int[] dp = new int[maxCost+1];
		
		for(int i = 0; i < N; i++) {
			for(int j = maxCost; j >= costs[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j-costs[i]]+bytes[i]);
			}
		}
		
		for(int i = 0; i <= maxCost; i++) {
			if(dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}
}
