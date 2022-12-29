package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_05355_화성_수학 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			double N = Double.parseDouble(st.nextToken());
			
			while(st.hasMoreTokens()) {
				char op = st.nextToken().charAt(0);
				
				if(op == '@') N *= 3;
				else if(op == '%') N += 5;
				else N -= 7;
			}
			
			sb.append(String.format("%.2f\n", N));
		}
		
		System.out.println(sb);
	}
}
