package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_16397_탈출 {
	static boolean[] visited;
	static int N, T, G;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		
		visited = new boolean[100000];
		
		int ans = bfs();
		
		System.out.println(ans == -1 ? "ANG" : ans);
	}
	
	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(N, 0));
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(cur.x == G) return cur.t;
			if(cur.t == T) continue;
			
			int nx = cur.x+1;
			if(nx <= 99999 && !visited[nx]) {
				queue.add(new Node(nx, cur.t+1));
				visited[nx] = true;
			}
			
			if(cur.x < 50000) {
				nx = convert(cur.x);
				if(!visited[nx]) {
					queue.add(new Node(nx, cur.t+1));
					visited[nx] = true;
				}
			}
		}
		
		return -1;
	}
	
	static int convert(int x) {
		x <<= 1;
		int p = 10000;
		
		while(p > 0) {
			if(x / p != 0) {
				x -= p;
				break;
			}
			
			p /= 10;
		}
		
		return x;
	}
	
	static class Node {
		int x, t;
		
		public Node(int x, int t) {
			this.x = x;
			this.t = t;
		}
	}
}
