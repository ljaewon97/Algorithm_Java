package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class S3_15595_정답_비율_계산하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> count = new HashMap<>();
		Set<String> correct = new HashSet<>();
		int cnt = 0, sum = 0;
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			String id = st.nextToken();
			int res = Integer.parseInt(st.nextToken());
			st.nextToken(); st.nextToken(); st.nextToken(); st.nextToken();
			
			if(id.equals("megalusion")) continue;
			if(correct.contains(id)) continue;
			
			if(res == 4) {
				if(count.containsKey(id)) {
					cnt++;
					sum += count.get(id);
				}
				else cnt++;
				correct.add(id);
			}
			else {
				if(count.containsKey(id)) {
					count.put(id, count.get(id)+1);
				}
				else count.put(id, 1);
			}
		}
		
		if(cnt == 0) System.out.println(0);
		else System.out.println(100.0 * cnt / (cnt + sum));
	}
}
