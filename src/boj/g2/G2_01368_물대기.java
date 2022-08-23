package boj.g2;

public class G2_01368_물대기 {
	static Reader in = new Reader();
	static int[][] graph;
	static boolean[] visited;
	static int[] dist;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		graph = new int[N][N];
		dist = new int[N];
		visited = new boolean[N];

		for(int i = 0; i < N; i++) {
			dist[i] = in.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				graph[i][j] = in.nextInt();
			}
		}
		
		System.out.println(prim());
	}
	
	static long prim() {
		long sum = 0;
		
		while(true) {
			int node = findNode();
			
			if(node == -1) break;
			
			visited[node] = true;
			
			for(int next = 0; next < N; next++) {
				if(!visited[next] && graph[node][next] != 0 && graph[node][next] < dist[next]) {
					dist[next] = graph[node][next];
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			sum += dist[i];
		}
		
		return sum;
	}
	
	static int findNode() {
		int min = Integer.MAX_VALUE;
		int idx = -1;
		
		for(int i = 0; i < N; i++) {
			if(!visited[i] && dist[i] < min) {
				min = dist[i];
				idx = i;
			}
		}
		
		return idx;
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
