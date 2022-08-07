package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G4_12851_숨바꼭질_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int cases = 0;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N >= K) {
			System.out.println(N - K);
			System.out.println(1);
			return;
		}
		
		Deque<Integer> deque = new LinkedList<>();
		int[] dist = new int[100001];
		deque.add(N);
		
		while(!deque.isEmpty()) {
			int x = deque.poll();
			
			if(x == K) {
				cases++;
				continue;
			}
			
			if(dist[K] != 0 && dist[x] >= dist[K]) {
				continue;
			}
			
			int[] nextX = {x-1, x+1, 2*x};
			
			for(int nx: nextX) {
				if(isIn(nx) && ((dist[nx] == 0 || nx == K) || (dist[nx] != 0 && dist[nx] == dist[x] + 1))) {
					if(nx == K && dist[K] != 0) {
						deque.add(nx);
						continue;
					}
					dist[nx] = dist[x] + 1;
					deque.add(nx);
				}
			}
		}
		
		System.out.println(dist[K]);
		System.out.println(cases);
	}
	
	static boolean isIn(int n) {
		return 0 <= n && n <= 100000;
	}
}
