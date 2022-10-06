package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_D3_3307_최장_증가_부분_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int ans = 1;
			int idx = -1;
			
			int[] arr = new int[N];
			int[] dp = new int[N];
			int[] path = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N; i++) {
				dp[i] = 1;
				path[i] = -1;
				
				for(int j = 0; j < i; j++) {
					if(arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
						dp[i] = dp[j] + 1;
						path[i] = j;
					}
				}
				
				if(dp[i] > ans) {
					ans = dp[i];
					idx = i;
				}
			}
			
			Stack<Integer> stack = new Stack<>();
			
			while(path[idx] != -1) {
				stack.push(idx);
				idx = path[idx];
			}
			
			stack.push(idx);
			
			sb.append(String.format("#%d %d\n", t, ans));
			
			while(!stack.isEmpty()) {
				sb.append(arr[stack.pop()]).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
