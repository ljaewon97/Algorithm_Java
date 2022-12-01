package boj.p4;

import java.util.ArrayList;
import java.util.List;

public class P4_11375_열혈강호 {
	static Reader in = new Reader();
	static List<Integer>[] works;
	static int[] match;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		int M = in.nextInt();
		
		works = new ArrayList[N+1];
		match = new int[M+1];
		
		for(int i = 1; i <= N; i++) {
			works[i] = new ArrayList<>();
			
			int n = in.nextInt();
			
			while(n-- > 0) {
				works[i].add(in.nextInt());
			}
		}
		
		int ans = 0;
		
		for(int i = 1; i <= N; i++) {
			visited = new boolean[M+1];
			if(dfs(i)) ans++;
		}
		
		System.out.println(ans);
	}
	
	static boolean dfs(int empl) {
		for(int work: works[empl]) {
			if(visited[work]) continue;
			visited[work] = true;
			
			if(match[work] == 0 || dfs(match[work])) {
				match[work] = empl;
				return true;
			}
		}
		
		return false;
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
