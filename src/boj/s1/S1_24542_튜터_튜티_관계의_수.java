package boj.s1;

public class S1_24542_튜터_튜티_관계의_수 {
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
		
		while(M-- > 0) {
			int u = in.nextInt();
			int v = in.nextInt();
			
			union(u, v);
		}
		
		long ans = 1;
		final int MOD = 1000000007;
		
		for(int i = 1; i <= N; i++) {
			ans = (ans * depth[i]) % MOD;
		}
		
		System.out.println(ans);
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
			depth[p1] = 1;
		}
		else {
			parent[p2] = p1;
			depth[p1] += depth[p2];
			depth[p2] = 1;
		}
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
