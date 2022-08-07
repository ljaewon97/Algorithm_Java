package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G5_13549_숨바꼭질_3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N >= K) {
			System.out.println(N - K);
			return;
		}
		
		boolean[] visited = new boolean[100001];
		Deque<int[]> deque = new LinkedList<>();
		
		deque.add(new int[] {N, 0});
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int x = cur[0];
			int dist = cur[1];
			
			if(x == K) {
				System.out.println(dist);
				break;
			}
			
			int[] nextX = {2*x, x-1, x+1};
			
			for(int nx: nextX) {
				if(isIn(nx) && !visited[nx]) {
					visited[nx] = true;
					
					if(nx == 2*x) {
						deque.add(new int[] {nx, dist});
					}
					else {
						deque.add(new int[] {nx, dist+1});
					}
				}
			}
		}
	}
	
	static boolean isIn(int n) {
		return 0 <= n && n <= 100000;
	}
}
