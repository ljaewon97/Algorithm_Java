package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_22252_정보_상인_호석 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Map<String, PriorityQueue<Integer>> map = new HashMap<>();
		
		int Q = Integer.parseInt(br.readLine());
		long ans = 0;
		
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			
			if(type == 1) {
				String name = st.nextToken();
				if(!map.containsKey(name)) map.put(name, new PriorityQueue<>(Collections.reverseOrder()));
				PriorityQueue<Integer> pq = map.get(name);
				int n = Integer.parseInt(st.nextToken());
				while(n-- > 0) {
					pq.add(Integer.parseInt(st.nextToken()));
				}
			}
			else {
				String name = st.nextToken();
				int n = Integer.parseInt(st.nextToken());
				if(!map.containsKey(name)) continue;
				PriorityQueue<Integer> pq = map.get(name);
				int i = Math.min(pq.size(), n);
				while(i-- > 0) {
					ans += pq.poll();
				}
			}
		}
		
		System.out.println(ans);
	}
}
