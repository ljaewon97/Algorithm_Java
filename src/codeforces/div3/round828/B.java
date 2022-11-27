package codeforces.div3.round828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			long[] sum = new long[2];
			int[] count = new int[2];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int num = Integer.parseInt(st.nextToken());
				if(num % 2 == 0) {
					sum[0] += num;
					count[0]++;
				}
				else {
					sum[1] += num;
					count[1]++;
				}
			}
			
			while(q-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				if(a == 0) {
					if(x % 2 == 0) {
						sum[0] += count[0] * x;
					}
					else {
						sum[1] += sum[0] + count[0] * x;
						sum[0] = 0;
						count[1] += count[0];
						count[0] = 0;
					}
					
					long ans = sum[0] + sum[1];
					sb.append(ans).append("\n");
				}
				else {
					if(x % 2 == 0) {
						sum[1] += count[1] * x;
					}
					else {
						sum[0] += sum[1] + count[1] * x;
						sum[1] = 0;
						count[0] += count[1];
						count[1] = 0;
					}
					
					long ans = sum[0] + sum[1];
					sb.append(ans).append("\n");
				}
			}
		}
		
		System.out.println(sb);
	}
}
