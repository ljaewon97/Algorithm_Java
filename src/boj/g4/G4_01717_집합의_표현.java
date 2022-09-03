package boj.g4;

public class G4_01717_집합의_표현 {
	static Reader in = new Reader();
	static int[] parent, depth;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		
		parent = new int[N+1];
		depth = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
			depth[i] = 1;
		}
		
		for(int i = 0; i < M; i++) {
			int cmd = in.nextInt();
			int a = in.nextInt();
			int b = in.nextInt();
			
			if(cmd == 0) {
				union(a, b);
			}
			else {
				int pa = find(a);
				int pb = find(b);
				
				sb.append(pa == pb ? "YES" : "NO").append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	static void union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		
		if(p1 == p2) return;
		
		if(depth[p1] < depth[p2]) {
			parent[p1] = p2;
			depth[p2] += depth[p1];
		}
		else {
			parent[p2] = p1;
			depth[p1] += depth[p2];
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if (neg)
				return -n;
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