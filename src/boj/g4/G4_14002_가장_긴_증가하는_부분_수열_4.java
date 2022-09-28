package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class G4_14002_가장_긴_증가하는_부분_수열_4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N][2];
		dp[0][0] = -1;
		dp[0][1] = 1;
		
		for(int i = 1; i < N; i++) {
			int idx = -1;
			int max = 0;
			
			
			for(int j = i-1; j >= 0; j--) {
				if(arr[j] < arr[i] && dp[j][1] > max) {
					max = dp[j][1];
					idx = j;
				}
			}
			
			if(idx == -1) {
				dp[i][0] = -1;
				dp[i][1] = 1;
			}
			else {
				dp[i][0] = idx;
				dp[i][1] = max + 1;
			}
		}
		
		int max = 0;
		int idx = -1;
		
		for(int i = 0; i < N; i++) {
			if(dp[i][1] > max) {
				max = dp[i][1];
				idx = i;
			}
		}
		
		sb.append(max).append("\n");
		
		Stack<Integer> stack = new Stack<>();
		stack.push(idx);
		
		while(true) {
			int i = dp[stack.peek()][0];
			
			if(i == -1) break;
			else stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			sb.append(arr[stack.pop()]).append(" ");
		}
		
		System.out.println(sb);
	}
}
