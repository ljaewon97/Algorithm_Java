package codeforces.div3.round828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int[] convert = new int[51];
			String s = br.readLine();
			boolean ok = true;
			for(int i = 0; i < n; i++) {
				int temp = s.charAt(i) - 'a' + 1;
				if(convert[arr[i]] == 0) convert[arr[i]] = temp;
				else {
					if(convert[arr[i]] != temp) {
						ok = false;
						break;
					}
				}
			}
			sb.append(ok ? "YES" : "NO").append("\n");
		}
		
		System.out.println(sb);
	}
}
