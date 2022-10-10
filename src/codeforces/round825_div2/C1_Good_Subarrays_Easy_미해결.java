package codeforces.round825_div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class C1_Good_Subarrays_Easy_미해결 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = 1;
			for(int i = 1; i < n; i++) {
				int temp = arr[i] - 1;
				int len = 1;
				
				while(temp-- > 0) {
					if(i-len < 0) break;
					temp = Math.min(temp, arr[i-len] - 1);
					len++;
				}
				
				ans += len;
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}