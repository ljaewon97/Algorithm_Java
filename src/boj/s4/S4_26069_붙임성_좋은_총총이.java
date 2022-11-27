package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S4_26069_붙임성_좋은_총총이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Set<String> set = new HashSet<>();
		set.add("ChongChong");
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			
			if(set.contains(a) || set.contains(b)) {
				set.add(a);
				set.add(b);
			}
		}
		
		System.out.println(set.size());
	}
}
