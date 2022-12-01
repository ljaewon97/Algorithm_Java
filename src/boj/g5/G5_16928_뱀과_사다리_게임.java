package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_16928_뱀과_사다리_게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<Integer, Integer> move = new HashMap<>();
		
		for(int i = 0; i < N+M; i++) {
			st = new StringTokenizer(br.readLine());
			move.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		queue.add(new Node(1, 0));
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(cur.loc == 100) {
				System.out.println(cur.d);
				return;
			}
			
			for(int i = 1; i <= 6; i++) {
				int next = cur.loc + i;
				
				if(next > 100 || visited[next]) continue;
				
				visited[next] = true;
				if(move.containsKey(next)) {
					next = move.get(next);
					visited[next] = true;
				}
				queue.add(new Node(next, cur.d+1));
			}
		}
	}
	
	static class Node {
		int loc, d;
		
		public Node(int loc, int d) {
			this.loc = loc;
			this.d = d;
		}
	}
}
