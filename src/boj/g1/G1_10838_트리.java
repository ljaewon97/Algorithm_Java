package boj.g1;

import java.util.HashSet;
import java.util.Set;

public class G1_10838_트리 {
	static Reader in = new Reader();
	static int[] parent, visited, color;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int N = in.nextInt();
		int K = in.nextInt();
		
		parent = new int[N];
		visited = new int[N];
		color = new int[N];
		
		parent[0] = -1;
		
		while(K-- > 0) {
			int r = in.nextInt();
			
			if(r == 1) {
				int a = in.nextInt();
				int b = in.nextInt();
				int c = in.nextInt();
				
				paint(a, b, c);
			}
			else {
				int a = in.nextInt();
				int b = in.nextInt();
				
				if(r == 2) move(a, b);
				else sb.append(count(a, b)).append("\n");
			}
		}
		
		System.out.println(sb);
		
		System.out.println(lca(3, 5));
	}

	static void paint(int a, int b, int c) {
		if(a == b) return;
		int p = lca(a, b);
		
		while(a != p) {
			color[a] = c;
			a = parent[a];
		}
		
		while(b != p) {
			color[b] = c;
			b = parent[b];
		}
	}
	
	static void move(int a, int b) {
		if(a == b) return;
		parent[a] = b;
	}
	
	static int count(int a, int b) {
		if(a == b) return 0;
		Set<Integer> set = new HashSet<>();
		int p = lca(a, b);
		
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
	
	static int lca(int a, int b) {
		if(a == b) return a;
		
		visited[a] = a;
		visited[b] = b;
		int pa = parent[a];
		int pb = parent[b];
		
		int cnt = 0;
		while(pa != -1 && cnt < 1001) {
			visited[pa] = a;
			pa = parent[pa];
			cnt++;
		}
		
		cnt = 0;
		while(pb != -1 && cnt < 1001) {
			if(visited[pb] == a) return pb;
			
			pb = parent[pb];
			cnt++;
		}
		
		return 0;
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
