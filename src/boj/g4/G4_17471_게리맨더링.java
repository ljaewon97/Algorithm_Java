package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_17471_게리맨더링 {
	static List<Integer>[] conn;
	static int[] pop;
	static boolean[] choosed;
	static int N, popSum, ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		pop = new int[N+1];
		conn = new ArrayList[N+1];
		choosed = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			conn[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
			popSum += pop[i];
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			while(a-- > 0) {
				int b = Integer.parseInt(st.nextToken());
				conn[i].add(b);
			}
		}
		
		solve(1, 0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void solve(int nth, int cnt) {
		if(nth == N+1) {
			bfs(cnt);
			return;
		}
		
		choosed[nth] = true;
		solve(nth+1, cnt+1);
		
		choosed[nth] = false;
		solve(nth+1, cnt);
	}
	
	static void bfs(int cnt) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		int temp = 0;
		
		for(int i = 1; i <= N; i++) {
			if(choosed[i]) {
				queue.add(i);
				visited[i] = true;
				temp++;
				break;
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int next: conn[cur]) {
				if(!visited[next] && choosed[next]) {
					queue.add(next);
					visited[next] = true;
					temp++;
				}
			}
		}
		
		if(temp != cnt) return;
		
		temp = 0;
		cnt = N - cnt;
		
		for(int i = 1; i <= N; i++) {
			if(!choosed[i]) {
				queue.add(i);
				visited[i] = true;
				temp++;
				break;
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int next: conn[cur]) {
				if(!visited[next] && !choosed[next]) {
					queue.add(next);
					visited[next] = true;
					temp++;
				}
			}
		}
		
		if(temp != cnt) return;
		
		int sum = 0;
		
		for(int i = 1; i <= N; i++) {
			if(choosed[i]) {
				sum += pop[i];
			}
		}
		
		ans = Math.min(ans, Math.abs(popSum-2*sum));
	}
}
