package swea;

public class SWEA_D4_3289_서로소_집합 {
	static Reader in = new Reader();
	static int[] parent, depth;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		for(int t = 1; t <= T; t++) {
			sb.append(String.format("#%d ", t));
			
			int n = in.nextInt();
			int m = in.nextInt();
			
			parent = new int[n+1];
			depth = new int[n+1];
			
			for(int i = 1; i <= n; i++) {
				parent[i] = i;
				depth[i] = 1;
			}
			
			for(int i = 0; i < m; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				int c = in.nextInt();
				
				if(a == 0) {
					union(b, c);
				}
				else {
					if(find(b) == find(c)) sb.append("1");
					else sb.append("0");
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
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
	
	static int find(int node) {
		if(parent[node] == node) return node;
		
		return parent[node] = find(parent[node]);
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int idx, size;

		boolean isNumber(byte b) {
			return 47 < b && b < 58;
		}

		byte read() throws Exception {
			if (idx == size) {
				size = System.in.read(buffer, idx = 0, SIZE);
				if (size < 0) {
					buffer[0] = -1;
				}
			}
			return buffer[idx++];
		}

		int nextInt() throws Exception {
			int n = 0;
			byte b;
			while ((b = read()) <= 32);
			do n = (n << 3) + (n << 1) + (b & 15);
			while (isNumber(b = read()));
			return n;
		}
	}
}
