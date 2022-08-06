package boj.g1;

public class G1_10868_최솟값 {
	static Reader in = new Reader();
	static int[] arr, mintree;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		
		arr = new int[N];
		mintree = new int[4*N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		buildMinTree(1, 0, N-1);
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			
			sb.append(minQuery(a, b, 1, 0, N-1)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int buildMinTree(int node, int nodeLeft, int nodeRight) {
		if(nodeLeft == nodeRight) {
			return mintree[node] = arr[nodeLeft];
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		int leftValue = buildMinTree(2*node, nodeLeft, mid);
		int rightValue = buildMinTree(2*node+1, mid+1, nodeRight);
		
		return mintree[node] = Math.min(leftValue, rightValue);
	}
	
	static int minQuery(int left, int right, int node, int nodeLeft, int nodeRight) {
		if(nodeRight < left || right < nodeLeft) {
			return 1000000001;
		}
		
		if(left <= nodeLeft && nodeRight <= right) {
			return mintree[node];
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		
		return Math.min(minQuery(left, right, 2*node, nodeLeft, mid), minQuery(left, right, 2*node+1, mid+1, nodeRight));
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
