package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S3_26215_눈_치우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> snow = new PriorityQueue<>(Collections.reverseOrder());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			snow.add(Integer.parseInt(st.nextToken()));
		}
		
		int ans = 0;
		
		while(!snow.isEmpty()) {
			if(snow.size() >= 2) {
				int x = snow.poll();
				int y = snow.poll();
				if(x > 1) snow.add(x-1);
				if(y > 1) snow.add(y-1);
			}
			else {
				int x = snow.poll();
				if(x > 1) snow.add(x-1);
			}
			
			ans++;
			
			if(ans > 1440) break;
		}
		
		System.out.println(ans > 1440 ? -1 : ans);
	}
}
