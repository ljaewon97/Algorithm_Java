package boj.g3;

public class G3_25341_인공_신경망 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int N = in.nextInt();
		int M = in.nextInt();
		int Q = in.nextInt();
		
		int[][] idxs = new int[M][N];
		int[][] weights = new int[M][N];
		int[] biases = new int[M];
		int[] sizes = new int[M];
		
		for(int i = 0; i < M; i++) {
			int c = in.nextInt();
			sizes[i] = c;
			
			for(int j = 0; j < c; j++) {
				idxs[i][j] = in.nextInt() - 1;
			}
			
			for(int j = 0; j < c; j++) {
				weights[i][j] = in.nextInt();
			}
			
			biases[i] = in.nextInt();
		}
		
		int sumBias = 0;
		int[] sumWeights = new int[N];
		
		for(int i = 0; i < M; i++) {
			int size = sizes[i];
			int w = in.nextInt();
			
			for(int j = 0; j < size; j++) {
				weights[i][j] *= w;
				sumWeights[idxs[i][j]] += weights[i][j];
			}
			
			sumBias += biases[i] * w;
		}
		
		sumBias += in.nextInt();
		
		for(int i = 0; i < Q; i++) {
			long res = sumBias;
			
			for(int j = 0; j < N; j++) {
				res += in.nextLong() * sumWeights[j];
			}
			
			sb.append(res).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static class Reader {
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

		long nextLong() throws Exception {
			long n = 0;
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
