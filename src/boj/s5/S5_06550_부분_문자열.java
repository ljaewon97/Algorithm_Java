package boj.s5;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_06550_부분_문자열 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			try {
				st = new StringTokenizer(br.readLine());
			}
			catch(Exception e) {
				break;
			}
			
			String s = st.nextToken();
			String t = st.nextToken();
			int i = 0;
			boolean sub = false;
			
			for(int j = 0; j < t.length(); j++) {
				if(t.charAt(j) == s.charAt(i)) {
					i++;
				}
				
				if(i == s.length()) {
					sub = true;
					break;
				}
			}
			
			if(sub) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
	}
}
