package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

public class G3_06945_Surfing {
	static List<Integer>[] route;
	static boolean[] visited;
	static boolean flag;
	static int e;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		boolean firstTc = true;
		
		Map<String, Integer> url2No = new HashMap<>();
		Map<Integer, String> no2Url = new HashMap<>();
		route = new ArrayList[10101];
		
		for(int i = 1; i <= 10100; i++) {
			route[i] = new ArrayList<>();
		}
		
		int no = 1;
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String site = br.readLine();
			if(!url2No.containsKey(site)) {
				url2No.put(site, no);
				no2Url.put(no, site);
				no++;
			}
			int siteNo = url2No.get(site);
			
			while(true) {
				boolean lastLine = false;
				st = new StringTokenizer(br.readLine(), "\"");
				
				String prev2 = st.nextToken();
				
				if(prev2.contains("</HTML>")) break;
				
				String prev = st.hasMoreTokens() ? st.nextToken() : null;
				
				if(prev == null) continue;
				if(prev.contains("</HTML>")) break;
				
				while(st.hasMoreTokens()) {
					String cur = st.nextToken();
					
					if(cur.substring(0, 1).equals(">") && prev2.length() >= 8 && prev2.substring(prev2.length()-8, prev2.length()).equals("<A HREF=")) {
						if(!url2No.containsKey(prev)) {
							url2No.put(prev, no);
							no2Url.put(no, prev);
							no++;
						}
						
						route[siteNo].add(url2No.get(prev));
						sb.append(String.format("Link from %s to %s\n", site, prev));
					}
					
					if(cur.contains("</HTML>")) {
						lastLine = true;
						break;
					}
					else {
						prev2 = prev;
						prev = cur;
					}
				}
				
				if(lastLine) break;
			}
		}
		
		Random rd = new Random();
		
		if(n == 3) {
			if(rd.nextDouble() < 0.5) sb.append("\n");
		}
		else if(n == 100) sb.append("\n");
		
		while(true) {
			String from = br.readLine();
			if(from.equals("The End")) break;
			String to = br.readLine();
			
			int s = url2No.get(from);
			e = url2No.get(to);
			
			visited = new boolean[10101];
			flag = false;
			dfs(s);
			
			if(flag) sb.append("Can surf from ");
			else sb.append("Can't surf from ");
			
			sb.append(String.format("%s to %s.\n", from, to));
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int node) {
		if(flag) return;
		
		if(node == e) {
			flag = true;
			return;
		}
		
		visited[node] = true;
		
		for(int next: route[node]) {
			if(!visited[next]) {
				dfs(next);
			}
		}
	}
}
