package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class G5_27231_2023년이_기대되는_이유 {
	static Set<Integer> set;
	static int[] digits;
	static boolean[] plus;
	static int n, len;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			String str = br.readLine();
			
			set = new HashSet<>();
			n = Integer.parseInt(str);
			digits = new int[str.length()];
			boolean only01 = true;
			
			for(int i = 0; i < str.length(); i++) {
				digits[i] = str.charAt(i)-'0';
				if(digits[i] > 1) only01 = false;
			}
			
			if(only01) {
				sb.append("Hello, BOJ 2023!\n");
				continue;
			}
			
			if(n < 10) {
				sb.append("1\n");
				continue;
			}
			
			len = str.length()-1;
			plus = new boolean[len];
			
			subset(0);
			
			int ans = 0;
			long[] pows = new long[str.length()];
			Arrays.fill(pows, 1);
			
			while(true) {
				long temp = 0;
				
				for(int i = 0; i < str.length(); i++) {
					pows[i] *= digits[i];
					temp += pows[i];
				}
				
				if(temp > n) break;
				
				if(set.contains((int) temp)) ans++;
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void subset(int nth) {
		if(nth == len) {
			int x = 0;
			int cur = digits[0];
			
			for(int i = 0; i < len; i++) {
				if(plus[i]) {
					x += cur;
					cur = digits[i+1];
				}
				else {
					cur = cur*10 + digits[i+1];
				}
			}
			
			x += cur;
			set.add(x);
			return;
		}
		
		plus[nth] = true;
		subset(nth+1);
		plus[nth] = false;
		subset(nth+1);
	}
}
