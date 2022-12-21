package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class G5_18112_이진수_게임 {
	static int L, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		L = str2Int(br.readLine());
		K = str2Int(br.readLine());
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[1<<11 + 1];
		queue.add(new Node(L, 0));
		visited[L] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(cur.n == K) return cur.cnt;
			
			if(cur.n > 1) {
				int bit = 1;
				
				while(true) {
					if(bit*2 > cur.n) break;
					
					int nn = cur.n ^ bit;
					if(!visited[nn]) {
						queue.add(new Node(nn, cur.cnt+1));
						visited[nn] = true;
					}
					
					bit <<= 1;
				}
			}
			
			if(cur.n < 1<<11) {
				int nn = cur.n + 1;
				if(!visited[nn]) {
					queue.add(new Node(nn, cur.cnt+1));
					visited[nn] = true;
				}
			}
			
			if(cur.n > 0) {
				int nn = cur.n - 1;
				if(!visited[nn]) {
					queue.add(new Node(nn, cur.cnt+1));
					visited[nn] = true;
				}
			}
		}
		
		return 0;
	}
	
	static int str2Int(String str) {
		int ret = 0, n = 1;
		
		for(int i = str.length()-1; i >= 0; i--) {
			if(str.charAt(i) == '1') ret += n;
			n <<= 1;
		}
		
		return ret;
	}
	
	static class Node {
		int n, cnt;
		
		public Node(int n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}
	}
}
