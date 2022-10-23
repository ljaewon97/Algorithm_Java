package codeforces.div2.round830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class D1_Balance_Easy {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Set<Long> set = new HashSet<>();
		Map<Long, Long> memo = new HashMap<>();
		
		set.add(0L);
		
		int q = Integer.parseInt(br.readLine());

		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			
			if(c == '+') {
				set.add(Long.parseLong(st.nextToken()));
				
			}
			else {
				long k = Long.parseLong(st.nextToken());
				long i = memo.containsKey(k) ? memo.get(k) : 1;
				
				while(true) {
					if(!set.contains(k*i)) {
						sb.append(k*i).append("\n");
						memo.put(k, i);
						break;
					}
					
					i++;
				}
			}
		}
		
		System.out.println(sb);
	}

}
