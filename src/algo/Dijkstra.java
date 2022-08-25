package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int V = Integer.parseInt(br.readLine());
		
		int[][] graph = new int[V][V];
		
		for(int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < V; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int start = 0, end = V-1;
		
		int[] dist = new int[V];
		boolean[] visited = new boolean[V];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		dist[start] = 0;
		pq.add(new int[] {start, dist[start]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cur_node = cur[0];
			int cur_dist = cur[1];
			
			visited[cur_node] = true;
			
			for(int next = 0; next < V; next++) {
				int temp = cur_dist + graph[cur_node][next];
				if(!visited[next] && graph[cur_node][next] > 0 && temp < dist[next]) {
					dist[next] = temp;
					pq.add(new int[] {next, dist[next]});
				}
			}
		}
		
		System.out.println(dist[end]);
	}
}

/*

============= 인접행렬 테스트케이스 

5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

output==> 8


6
0 3 5 0 0 0
0 0 2 6 0 0
0 1 0 4 6 0
0 0 0 0 2 3
3 0 0 0 0 6
0 0 0 0 0 0

output==> 12


10
0 4 6 0 0 0 0 0 0 0
0 0 0 9 8 0 0 0 0 0
0 3 0 0 2 3 0 0 0 0
0 0 0 0 0 0 6 0 0 0
0 0 0 2 0 1 3 7 0 0 
0 0 0 0 0 0 0 4 8 0
0 0 0 0 0 0 0 0 0 13
0 0 0 0 0 0 0 0 0 9
0 0 0 0 0 0 0 0 0 4
0 0 0 0 0 0 0 0 0 0


output ==> 21

============= 인접리스트 테스트케이스 
10 17
0 1 4
0 2 6
1 3 9
1 4 8
2 1 3
2 4 2
2 5 3
3 6 6
4 3 2
4 5 1
4 6 3
4 7 7
5 7 4
5 8 8
6 9 13
7 9 9
8 9 4

output ==> 21

*/
