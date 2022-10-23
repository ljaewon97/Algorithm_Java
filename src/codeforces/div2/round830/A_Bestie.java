package codeforces.div2.round830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_Bestie {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			int[] arr = new int[n+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			if(n == 1) {
				if(arr[1] == 1) {
					sb.append("0\n");
					continue;
				}
				else {
					sb.append("1\n");
					continue;
				}
			}
			
			int g = arr[1];
			for(int i = 2; i <= n; i++) {
				g = gcd(g, arr[i]);
			}
			
			if(g == 1) {
				sb.append("0\n");
				continue;
			}
			
			int t1 = arr[n];
			arr[n] = gcd(arr[n], n);
			
			int gcd1 = arr[1];
			for(int i = 2; i <= n; i++) {
				gcd1 = gcd(gcd1, arr[i]);
			}
			
			if(gcd1 == 1) {
				sb.append("1\n");
				continue;
			}
			
			arr[n] = t1;
			arr[n-1] = gcd(arr[n-1], n-1);
			
			int gcd2 = arr[1];
			for(int i = 2; i <= n; i++) {
				gcd2 = gcd(gcd2, arr[i]);
			}
			
			if(gcd2 == 1) {
				sb.append("2\n");
			}
			else {
				sb.append("3\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int gcd(int a, int b) {
		while(b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		
		return a;
	}
}
