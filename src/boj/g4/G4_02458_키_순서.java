package boj.g4;

public class G4_02458_키_순서 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		final int INF = 1000000000;
		
		int N = in.nextInt();
		int M = in.nextInt();
		
		int[][] graph = new int[N+1][N+1];
		
		while(M-- > 0) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			graph[a][b] = 1;
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i != j && graph[i][j] == 0) graph[i][j] = INF;
			}
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		int ans = 0;
		
		for(int i = 1; i <= N; i++) {
			boolean[] check = new boolean[N+1];
			boolean flag = true;
			
			for(int j = 1; j <= N; j++) {
				if(graph[i][j] != INF) check[j] = true;
				if(graph[j][i] != INF) check[j] = true;
			}
			
			for(int j = 1; j <= N; j++) {
				if(!check[j]) {
					flag = false;
					break;
				}
			}
			
			if(flag) ans++;
		}
		
		System.out.println(ans);
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
