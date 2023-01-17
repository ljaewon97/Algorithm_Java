package boj.g4;

public class G4_01976_여행_가자 {
	static Reader in = new Reader();
	static int[] parent, depth;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		parent = new int[N+1];
		depth = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			depth[i] = 1;
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(in.nextInt() == 1 && j > i) {
					union(i, j);
				}
			}
		}
		
		int p = find(in.nextInt());
		boolean ans = true;
		
		while(M-- > 1) {
			if(find(in.nextInt()) != p) ans = false;
		}
		
		System.out.println(ans ? "YES" : "NO");
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		
		if(depth[a] > depth[b]) {
			parent[b] = a;
			depth[a] += depth[b];
		}
		else {
			parent[a] = b;
			depth[b] += depth[a];
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
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
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
