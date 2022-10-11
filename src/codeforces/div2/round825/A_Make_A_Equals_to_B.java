package codeforces.div2.round825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_Make_A_Equals_to_B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			int a = 0, b = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int num = Integer.parseInt(st.nextToken());
				a += num;
				arr[i] = num;
			}
			
			int diff = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int num = Integer.parseInt(st.nextToken());
				b += num;
				
				if(arr[i] != num) diff++;
			}
			
			if(diff == 0) {
				sb.append("0\n");
			}
			else {
				sb.append(Math.min(diff, Math.abs(a-b)+1)).append("\n");
			}
			
		}
		
		System.out.println(sb);
	}
}
