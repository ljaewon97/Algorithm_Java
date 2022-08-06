package boj.p3;

public class P3_01395_스위치 {
	static Reader in = new Reader();
	static int[] tree, lazy;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		
		tree = new int[4*N];
		lazy = new int[4*N];
		
		for(int i = 0; i < M; i++) {
			int O = in.nextInt();
			int S = in.nextInt() - 1;
			int T = in.nextInt() - 1;
			
			if(O == 0) {
				update(S, T, 1, 0, N-1);
			}
			else {
				sb.append(query(S, T, 1, 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		propagation(node, nodeLeft, nodeRight);
		
		if(nodeRight < left || right < nodeLeft) {
			return 0;
		}
		
		if(left <= nodeLeft && nodeRight <= right) {
			return tree[node];
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		return query(left, right, 2*node, nodeLeft, mid) + query(left, right, 2*node+1, mid+1, nodeRight);
	}
	
	static int update(int left, int right, int node, int nodeLeft, int nodeRight) {
		propagation(node, nodeLeft, nodeRight);
		
		if(nodeRight < left || right < nodeLeft) {
			return tree[node];
		}
		
		if(left <= nodeLeft && nodeRight <= right) {
			if(nodeLeft != nodeRight) {
				lazy[2*node] += 1;
				lazy[2*node+1] += 1;
			}
			
			return tree[node] = (nodeRight - nodeLeft + 1) - tree[node];
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		int leftValue = update(left, right, 2*node, nodeLeft, mid);
		int rightValue = update(left, right, 2*node+1, mid+1, nodeRight);
		
		return tree[node] = leftValue + rightValue;
	}
	
	static void propagation(int node, int nodeLeft, int nodeRight) {
		if(lazy[node] % 2 == 1) {
			tree[node] = (nodeRight - nodeLeft + 1) - tree[node];
			
			if(nodeLeft != nodeRight) {
				lazy[2*node] += 1;
				lazy[2*node+1] += 1;
			}
		}
		
		lazy[node] = 0;
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
