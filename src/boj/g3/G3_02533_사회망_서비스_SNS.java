package boj.g3;

import java.util.ArrayList;
import java.util.List;

public class G3_02533_사회망_서비스_SNS {
	static Reader in = new Reader();
	static List<Integer>[] tree;
	static int[][] dp;
	static boolean[] visited;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		tree = new ArrayList[N+1];
		dp = new int[N+1][2];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			
			tree[u].add(v);
			tree[v].add(u);
		}
		
		dfs(1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	static void dfs(int node) {
		visited[node] = true;
		dp[node][1] = 1;
		
		for(int next: tree[node]) {
			if(!visited[next]) {
				dfs(next);
				dp[node][0] += dp[next][1];
				dp[node][1] += Math.min(dp[next][0], dp[next][1]);
			}
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
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
