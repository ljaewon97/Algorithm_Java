package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_14567_선수과목_Prerequisite {
	static List<Integer>[] graph;
	static int[] in, ans;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		in = new int[N+1];
		ans = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B);
			in[B]++;
		}
		
		ts();
		
		for(int i = 1; i <= N; i++) {
			sb.append(ans[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static void ts() {
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			if(in[i] == 0) {
				queue.add(i);
				ans[i] = 1;
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int next: graph[cur]) {
				if(--in[next] == 0) {
					queue.add(next);
					ans[next] = ans[cur] + 1;
				}
			}
		}
	}
}
