package boj.g1;

public class G1_02042_구간_합_구하기 {
	static Reader in = new Reader();
	static long[] tree, arr;
	static int N, M, K;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		tree = new long[8*N+1];
		arr = new long[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextLong();
		}
		
		buildTree(1, 0, N-1);
		
		for(int i = 0; i < M+K; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			long c = in.nextLong();
			
			if(a == 1) {
				update(b-1, c, 1, 0, N-1);
			}
			else {
				int C = (int) c;
				sb.append(query(b-1, C-1, 1, 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static long buildTree(int node, int nodeLeft, int nodeRight) {
		if(nodeLeft == nodeRight) {
			return tree[node] = arr[nodeLeft];
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		long leftValue = buildTree(node*2, nodeLeft, mid);
		long rightValue = buildTree(node*2+1, mid+1, nodeRight);
		
		return tree[node] = leftValue + rightValue;
	}
	
	static long query(int left, int right, int node, int nodeLeft, int nodeRight) {
		if(nodeRight < left || right < nodeLeft) {
			return 0;
		}
		
		if(left <= nodeLeft && nodeRight <= right) {
			return tree[node];
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return query(left, right, node*2, nodeLeft, mid) + query(left, right, node*2+1, mid+1, nodeRight);
	}
	
	static long update(int idx, long newValue, int node, int nodeLeft, int nodeRight) {
		if(nodeRight < idx || idx < nodeLeft) {
			return tree[node];
		}
		
		if(nodeLeft == nodeRight) {
			return tree[node] = newValue;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		long leftValue = update(idx, newValue, node*2, nodeLeft, mid);
		long rightValue = update(idx, newValue, node*2+1, mid+1, nodeRight);
		
		return tree[node] = leftValue + rightValue;
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
			if(neg) c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if(neg) return -n;
			return n;
		}
		
		long nextLong() throws Exception {
			long n = 0;
			byte c;
			while ((c = read()) <= 32);
			boolean neg = c == '-' ? true : false;
			if(neg) c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if(neg) return -n;
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