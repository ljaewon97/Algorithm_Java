package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class G4_04143_Bridges_and_Tunnels {
	static Map<String, Integer> map;
	static int[] parent, depth;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			
			map = new HashMap<>();
			parent = new int[2*N];
			depth = new int[2*N];
			int no = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				int na = -1;
				int nb = -1;
				
				if(!map.containsKey(a)) {
					parent[no] = no;
					depth[no] = 1;
					na = no;
					map.put(a, no++);
				}
				else {
					na = find(map.get(a));
				}
				
				if(!map.containsKey(b)) {
					parent[no] = no;
					depth[no] = 1;
					nb = no;
					map.put(b, no++);
				}
				else {
					nb = find(map.get(b));
				}
				
				if(na == nb) sb.append(depth[na]).append("\n");
				else {
					parent[na] = nb;
					depth[nb] += depth[na];
					sb.append(depth[nb]).append("\n");
				}
			}
		}
		
		System.out.println(sb);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}
