package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_27466_그래서_대회_이름_뭐로_하죠 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String S = br.readLine();
		
		boolean flag = false;
		int last = -1;
		
		for(int i = N-1; i >= 0; --i) {
			if(!isVowel(S.charAt(i))) flag = true;
			
			if(flag && S.charAt(i) == 'A') {
				last = i;
				break;
			}
		}
		
		if(!flag) {
			System.out.println("NO");
			return;
		}
		
		int idx = -1;
		
		for(int i = last+1; i < N; ++i) {
			if(!isVowel(S.charAt(i))) {
				idx = i;
				break;
			}
		}
		
		if(idx == -1) {
			System.out.println("NO");
			return;
		}
		
		for(int i = 0; i < last; ++i) {
			if(S.charAt(i) == 'A') {
				if(i+3 >= M) {
					System.out.println("YES");
					sb.append(S.substring(i+3-M, i+1)).append("A").append(S.charAt(idx));
					System.out.println(sb);
					return;
				}
			}
		}
		
		System.out.println("NO");
	}
	
	static boolean isVowel(char c) {
		return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}
}
