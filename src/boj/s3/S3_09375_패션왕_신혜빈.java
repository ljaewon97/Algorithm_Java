package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class S3_09375_패션왕_신혜빈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			Map<String, Integer> map = new HashMap<>();
			
			while(n-- > 0) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String cloth = st.nextToken();
				
				if(!map.containsKey(cloth)) map.put(cloth, 0);
				map.put(cloth, map.get(cloth)+1);
			}
			
			long ans = 1;
			
			for(Entry<String, Integer> e: map.entrySet()) {
				ans *= e.getValue()+1;
			}
			
			sb.append(ans-1).append("\n");
		}
		
		System.out.println(sb);
	}
}
