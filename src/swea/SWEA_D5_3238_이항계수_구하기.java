package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_D5_3238_이항계수_구하기 {
	static List<Long> N;
	static List<Long> R;
	static long n, r;
	static int p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Long.parseLong(st.nextToken());
			r = Long.parseLong(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			
			N = new ArrayList<>();
			R = new ArrayList<>();
			
			calcNR();
			
			long ans = 1;
			
			for(int i = 0; i < N.size(); i++) {
				long cn = N.get(i);
				long cr = R.get(i);
				
				if(cn < cr) {
					ans = 0;
					break;
				}
				
				ans = ans * (((fact(cn) % p) * power(fact(cr) * fact(cn-cr) % p, p-2)) % p) % p;
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static long fact(long a) {
		long ret = 1;
		
		for(int i = 2; i <= a; i++) {
			ret = (i * ret) % p;
		}
		
		return ret;
	}
	
	static void calcNR() {
		while(n != 0 && r != 0) {
			N.add(n % p);
			R.add(r % p);
			n /= p;
			r /= p;
		}
	}
	
	static long power(long a, long b) {
		if(b == 0) return 1;
		if(b == 1) return a;
		if(b % 2 == 0) {
			long temp = power(a, b/2) % p;
			return temp * temp % p;
		}
		else {
			long temp = power(a, b-1) % p;
			return a * temp % p;
		}
	}
}
