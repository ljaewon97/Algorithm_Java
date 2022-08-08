package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_03584_가장_가까운_공통_조상 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			List<Integer>[] tree = new ArrayList[N+1];
			int[] parent = new int[N+1];
			
			for(int i = 1; i <= N; i++) {
				tree[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				tree[a].add(b);
				parent[b] = a;
			}
			
			int root = 0;
			for(int i = 1; i <= N; i++) {
				if(parent[i] == 0) {
					root = i;
					break;
				}
			}

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int[] depth = new int[N+1];
			Deque<int[]> deque = new LinkedList<>();
			deque.add(new int[] {root, 0});
			
			while(!deque.isEmpty()) {
				if(depth[a] != 0 && depth[b] != 0) {
					break;
				}
				
				int[] cur = deque.poll();
				int node = cur[0];
				int d = cur[1];
				
				for(int child: tree[node]) {
					deque.add(new int[] {child, d+1});
					depth[child] = d+1;
				}
			}
			
			while(depth[a] != depth[b]) {
				if(depth[a] > depth[b]) {
					a = parent[a];
				}
				else {
					b = parent[b];
				}
			}
			
			while(a != b) {
				a = parent[a];
				b = parent[b];
			}
			
			sb.append(a).append("\n");
		}
		
		System.out.println(sb);
	}
}
