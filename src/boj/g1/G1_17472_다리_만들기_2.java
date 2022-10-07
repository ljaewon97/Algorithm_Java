package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_17472_다리_만들기_2 {
	static int[][] map, bridges;
	static int[] parent;
	static boolean[][] visited;
	static int N, M, code = 1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 1) bfs(i, j);
			}
		}
		
		bridges = new int[code][code];
		parent = new int[code];
		
		for(int i = 0; i < code; i++) {
			Arrays.fill(bridges[i], Integer.MAX_VALUE);
			parent[i] = i;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0) buildBridge(i, j);
			}
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i = 1; i < code; i++) {
			for(int j = i+1; j < code; j++) {
				if(bridges[i][j] != Integer.MAX_VALUE) {
					pq.add(new Edge(i, j, bridges[i][j]));
				}
			}
		}
		
		int edgeCnt = 0;
		int sum = 0;
		
		while(!pq.isEmpty()) {
			if(edgeCnt == code - 2) break;
			
			Edge edge = pq.poll();
			
			int pa = find(edge.a);
			int pb = find(edge.b);
			
			if(pa == pb) continue;
			
			parent[pa] = pb;
			edgeCnt++;
			sum += edge.c;
		}
		
		if(edgeCnt != code - 2 || sum == 0) sum = -1;
		System.out.println(sum);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	static void buildBridge(int sr, int sc) {
		int num = map[sr][sc];
		
		for(int i = 0; i < 4; i++) {
			int nr = sr + dr[i];
			int nc = sc + dc[i];
			
			if(!isIn(nr, nc) || map[nr][nc] != 0) continue;
			
			int d = 1;
			
			while(true) {
				nr += dr[i];
				nc += dc[i];
				
				if(!isIn(nr, nc)) break;
				
				if(map[nr][nc] == 0) d++;
				else if(map[nr][nc] > num && d > 1) {
					bridges[num][map[nr][nc]] = Math.min(bridges[num][map[nr][nc]], d);
					break;
				}
				else break;
			}
		}
	}
	
	static void bfs(int sr, int sc) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc));
		visited[sr][sc] = true;
		map[sr][sc] = code;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					queue.add(new Point(nr, nc));
					visited[nr][nc] = true;
					map[nr][nc] = code;
				}
			}
		}
		
		code++;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Edge implements Comparable<Edge> {
		int a, b, c;
		
		public Edge(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public int compareTo(Edge o) {
			return Integer.compare(this.c, o.c);
		}
	}
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
