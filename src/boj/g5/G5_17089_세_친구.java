package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_17089_세_친구 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int ans = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B);
			graph[B].add(A);
		}
		
		for(int i = 1; i <= N; i++) {
			if(graph[i].size() >= 2) {
				for(int j = 0; j < graph[i].size(); j++) {
					for(int k = j+1; k < graph[i].size(); k++) {
						int b = graph[i].get(j);
						int c = graph[i].get(k);
						if(b > i && c > i) {
							boolean flag = false;
							for(int x: graph[b]) {
								if(x == c) {
									flag = true;
									break;
								}
							}
							
							if(flag) ans = Math.min(ans, graph[i].size()+graph[b].size()+graph[c].size()-6);
						}
					}
				}
			}
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
}
