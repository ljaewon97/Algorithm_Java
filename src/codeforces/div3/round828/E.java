package codeforces.div3.round828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			long min = (long) a * b;
			long num = min * 2;
			long max = (long) c * d;
			
			if(num > max) {
				sb.append("-1 -1\n");
				continue;
			}
			
			if(c >= 2*a && d >= 2*b) {
				sb.append(String.format("%d %d\n", 2*a, 2*b));
				continue;
			}
			
			if(b < a && a <= d && a < 2*b && 2*b <= c) {
				sb.append(String.format("%d %d\n", 2*b, a));
				continue;
			}
			
			if(a < b && b <= c && b < 2*a && 2*a <= d) {
				sb.append(String.format("%d %d\n", b, 2*a));
				continue;
			}
			
			if(a < min && min <= c) {
				sb.append(String.format("%d %d\n", min, d));
				continue;
			}
			
			if(b < min && min <= d) {
				sb.append(String.format("%d %d\n", c, min));
				continue;
			}
			
			boolean ok = false;
			
			for(long j = num; j <= max; j += min) {
				long i = 0;
				
				for(i = 1; i*i < j; i++) {
					if(j % i == 0) {
						long n1 = i;
						long n2 = j / i;
						
						if(a < n1 && n1 <= c && b < n2 && n2 <= d) {
							sb.append(String.format("%d %d\n", n1, n2));
							ok = true;
							break;
						}
						else if(a < n2 && n2 <= c && b < n1 && n1 <= d) {
							sb.append(String.format("%d %d\n", n2, n1));
							ok = true;
							break;
						}
					}
				}
				
				if(i*i == j) {
					if(a < i && i <= c && b < i && i <= d) {
						sb.append(String.format("%d %d\n", i, i));
						ok = true;
					}
				}
				
				if(ok) break;
			}
			
			if(!ok) sb.append("-1 -1\n");
		}
		
		System.out.println(sb);
	}
}
