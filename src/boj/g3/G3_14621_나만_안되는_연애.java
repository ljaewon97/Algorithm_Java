package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G3_14621_나만_안되는_연애 {
	static int[][] edges;
	static int[] parent, depth, type;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new int[M][3];
		type = new int[N+1];
		parent = new int[N+1];
		depth = new int[N+1];
		int cnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			type[i] = st.nextToken().equals("M") ? 1 : 0;
			parent[i] = i;
			depth[i] = 1;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(type[a] + type[b] == 1) {
				edges[cnt][0] = a;
				edges[cnt][1] = b;
				edges[cnt][2] = c;
				cnt++;
			}
		}
		
		edges = Arrays.copyOf(edges, cnt);
		Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
		
		if(cnt < N-1) {
			System.out.println(-1);
			return;
		}
		
		int edgeCnt = 0;
		int ans = 0;
		
		for(int i = 0; i < edges.length; i++) {
			if(edgeCnt == N-1) break;
			
			if(union(edges[i][0], edges[i][1])) {
				edgeCnt++;
				ans += edges[i][2];
			}
		}
		
		System.out.println(edgeCnt < N-1 ? -1 : ans);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	static boolean union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		
		if(p1 == p2) return false;
		
		if(depth[p1] < depth[p2]) { 
			parent[p1] = p2;
			depth[p2] += depth[p1];
		}
		else {
			parent[p2] = p1;
			depth[p1] += depth[p2];
		}
		
		return true;
	}
}
