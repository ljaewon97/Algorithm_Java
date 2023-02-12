package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class G5_10827_ab {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		String aStr = st.nextToken();
		int b = Integer.parseInt(st.nextToken());
		
		long a = 0;
		int loc = 0;
		
		if(aStr.contains(".")) {
			st = new StringTokenizer(aStr, ".");
			String a1 = st.nextToken();
			String a2 = st.nextToken();
			
			a = Long.parseLong(a1) * (int) Math.pow(10, a2.length());
			a += Long.parseLong(a2);
			loc = a2.length();
		}
		else {
			a = Long.parseLong(aStr);
		}
		
		BigInteger ans = BigInteger.ONE;
		BigInteger A = BigInteger.valueOf(a);
		
		for(int i = 0; i < b; ++i) {
			ans = ans.multiply(A);
		}
		
		String str = ans.toString();
		
		if(str.length() > loc*b) {
			sb.append(str.substring(0, str.length()-loc*b));
			sb.append('.');
			sb.append(str.substring(str.length()-loc*b));
		}
		else {
			sb.append("0.");
			
			for(int i = 0; i < loc*b-str.length(); ++i) {
				sb.append('0');
			}
			
			sb.append(str);
		}
		
		if(sb.charAt(sb.length()-1) == '.') System.out.println(sb.substring(0, sb.length()-1));
		else System.out.println(sb);
	}
}
