package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class TopologySort {
	static List<Integer>[] graph;
	static int[] indegree;
	static int V, E;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[V+1];
		indegree = new int[V+1];
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			indegree[b]++;
		}
		
		List<Integer> list = topologySort();
		
		if(list.size() == V) {
			System.out.println(list);
		}
		else {
			System.out.println("cycle");
		}
	}
	
	static List<Integer> topologySort() {
		List<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		
		// 진입 차수가 0인 정점 큐에 넣기
		for(int i = 1; i <= V; i++) {
			if(indegree[i] == 0) queue.add(i);
		}
		
		// BFS
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			list.add(cur);
			
			for(int next: graph[cur]) {
				if(--indegree[next] == 0) queue.add(next);
			}
		}
		
		return list;
	}
}
