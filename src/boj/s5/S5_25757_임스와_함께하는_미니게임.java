package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S5_25757_임스와_함께하는_미니게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		char type = st.nextToken().charAt(0);
		int div = type == 'Y' ? 1 : (type == 'F' ? 2 : 3);
		
		Set<String> set = new HashSet<>();
		
		while(N-- > 0) {
			set.add(br.readLine());
		}
		
		System.out.println(set.size()/div);
	}
}
