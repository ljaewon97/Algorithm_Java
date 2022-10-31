package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class G4_01034_램프 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> count = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			String lamps = br.readLine();
			
			if(!count.containsKey(lamps)) count.put(lamps, 1);
			else count.put(lamps, count.get(lamps)+1);
		}
		
		int K = Integer.parseInt(br.readLine());
		int ans = 0;
		
		for(Entry<String, Integer> entry: count.entrySet()) {
			String lamps = entry.getKey();
			int zeroes = 0;
			
			for(int i = 0; i < M; i++) {
				if(lamps.charAt(i) == '0') zeroes++;
			}
			
			if(K >= zeroes && (K-zeroes) % 2 == 0) ans = Math.max(ans, entry.getValue());
		}
		
		System.out.println(ans);
	}
}
