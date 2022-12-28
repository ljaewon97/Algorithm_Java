package boj.unknown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class U_26949_Kylskapstransport {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int pa = Integer.parseInt(st.nextToken());
		int ka = Integer.parseInt(st.nextToken());
		int pb = Integer.parseInt(st.nextToken());
		int kb = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int min = Integer.MAX_VALUE;
		int ma = 0, mb = 0;
		int a = 0;
		
		while(a*ka <= n) {
			int cn = n-a*ka;
			int b = (cn-1)/kb + 1;
			if(cn == 0) b = 0;
			
			if(pa*a+pb*b < min) {
				min = pa*a + pb*b;
				ma = a;
				mb = b;
			}
			
			a++;
		}
		
		System.out.printf("%d %d %d\n", ma, mb, pa*ma+pb*mb);
	}
}
