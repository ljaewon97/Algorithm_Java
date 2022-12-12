package boj.s1;

import java.util.ArrayList;
import java.util.List;

public class S1_15900_나무_탈출 {
	static Reader in = new Reader();
	static List<Integer>[] tree;
	static int[] depth;
	static boolean[] visited;
	static long sum;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		tree = new ArrayList[N+1];
		depth = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		dfs(1, 0);
		
		System.out.println(sum % 2 == 1 ? "Yes" : "No");
	}
	
	static void dfs(int node, int dep) {
		visited[node] = true;
		depth[node] = dep;
		int cnt = 0;
		
		for(int next: tree[node]) {
			if(!visited[next]) {
				dfs(next, dep+1);
				cnt++;
			}
		}
		
		if(cnt == 0) sum += dep;
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
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
