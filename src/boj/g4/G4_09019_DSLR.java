package boj.g4;

import java.util.ArrayDeque;
import java.util.Queue;

public class G4_09019_DSLR {
	static Reader in = new Reader();
	static char[] cmds = {'D', 'S', 'L', 'R'};
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		while(T-- > 0) {
			int A = in.nextInt();
			int B = in.nextInt();
			
			sb.append(bfs(A, B)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static String bfs(int A, int B) {
		Queue<Integer> queue = new ArrayDeque<>();
		Node[] visited = new Node[10000];
		queue.add(A);
		visited[A] = new Node(-1, ' ');
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(cur == B) break;
			
			int[] DSLR = {2 * cur % 10000, cur == 0 ? 9999 : cur-1, funcL(cur), funcR(cur)};
			
			for(int i = 0; i < 4; i++) {
				int next = DSLR[i];
				char cmd = cmds[i];
				
				if(visited[next] == null) {
					visited[next] = new Node(cur, cmd);
					queue.add(next);
				}
			}
		}
		
		StringBuilder ret = new StringBuilder();
		int cur = B;
		
		while(visited[cur].prev != -1) {
			ret.append(visited[cur].cmd);
			cur = visited[cur].prev;
		}
		
		return ret.reverse().toString();
	}
	
	static int funcL(int n) {
		int t = 10 * n;
		return t % 10000 + t / 10000;
	}
	
	static int funcR(int n) {
		int t1 = n / 10;
		int t2 = n % 10;
		return t2 * 1000 + t1;
	}
	
	static class Node {
		int prev;
		char cmd;
		
		public Node(int prev, char cmd) {
			this.prev = prev;
			this.cmd = cmd;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 17;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while ((c = read()) > 32);
			return n;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}
