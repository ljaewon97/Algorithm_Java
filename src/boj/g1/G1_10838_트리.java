package boj.g1;

import java.util.HashSet;
import java.util.Set;

public class G1_10838_트리 {
	static Reader in = new Reader();
	static int[] parent, visited, color;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int N = in.nextInt();
		int K = in.nextInt() + 1;
		
		parent = new int[N];
		visited = new int[N];
		color = new int[N];
		
		parent[0] = -1;
		
		while(K-- > 1) {
			int r = in.nextInt();
			
			if(r == 1) {
				int a = in.nextInt();
				int b = in.nextInt();
				int c = in.nextInt();
				
				paint(a, b, c, K);
			}
			else {
				int a = in.nextInt();
				int b = in.nextInt();
				
				if(r == 2) parent[a] = b;
				else sb.append(count(a, b, K)).append("\n");
			}
		}
		
		System.out.println(sb);
	}

	static void paint(int a, int b, int c, int k) {
		if(a == b) return;
		int p = lca(a, b, k);
		
		while(a != p) {
			color[a] = c;
			a = parent[a];
		}
		
		while(b != p) {
			color[b] = c;
			b = parent[b];
		}
	}
	
	static int count(int a, int b, int k) {
		if(a == b) return 0;
		Set<Integer> set = new HashSet<>();
		int p = lca(a, b, k);
		
		while(a != p) {
			set.add(color[a]);
			a = parent[a];
		}
		
		while(b != p) {
			set.add(color[b]);
			b = parent[b];
		}
		
		return set.size();
	}
	
	static int lca(int a, int b, int k) {
		if(a == b) return a;
		
		visited[a] = k;
		
		int cnt = 0;
		while(parent[a] != -1 && cnt < 1001) {
			a = parent[a];
			visited[a] = k;
			cnt++;
		}
		
		if(visited[b] == k) return b;
		
		cnt = 0;
		while(parent[b] != -1 && cnt < 1001) {
			b = parent[b];
			if(visited[b] == k) break;
			cnt++;
		}
		
		return b;
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
