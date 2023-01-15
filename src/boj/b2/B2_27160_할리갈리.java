package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class B2_27160_할리갈리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<>();
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String fruit = st.nextToken();
			int n = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(fruit)) map.put(fruit, n);
			else map.put(fruit, map.get(fruit)+n);
		}
		
		boolean ans = false;
		
		for(Entry<String, Integer> e: map.entrySet()) {
			if(e.getValue() == 5) {
				ans = true;
				break;
			}
		}
		
		System.out.println(ans ? "YES" : "NO");
	}
}
