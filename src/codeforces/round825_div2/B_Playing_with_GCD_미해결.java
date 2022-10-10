package codeforces.round825_div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_Playing_with_GCD_미해결 {
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
			
			if(n <= 2) {
				sb.append("YES\n");
				continue;
			}
			
			boolean flag = true;
			
			for(int i = 0; i < n-2; i++) {
				if(arr[i] != arr[i+1] && arr[i+1] != arr[i+2] && arr[i] == arr[i+2]) {
					flag = false;
					break;
				}
			}
			
			sb.append(flag ? "YES\n" : "NO\n");
		}
		
		System.out.println(sb);
	}
}
