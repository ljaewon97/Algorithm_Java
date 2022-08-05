package boj.p4;

public class P4_16975_수열과_쿼리_21 {
	static Reader in = new Reader();
	static long[] tree, lazy;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		tree = new long[3*N];
		lazy = new long[3*N];
		
		for(int i = 1; i <= N; i++) {
			tree[i] = in.nextLong();
		}
		
		M = in.nextInt();
		for(int p = 0; p < M; p++) {
			int a = in.nextInt();
			
			if(a == 1) {
				int i = in.nextInt() - 1;
				int j = in.nextInt() - 1;
				long k = in.nextLong();
				
				update(i, j, k, 1, 0, N-1);
			}
			else {
				
			}
		}
	}
	
	static long query(int idx, int node, int nodeLeft, int nodeRight) {
		propagation(node, nodeLeft, nodeRight);
		
		if(nodeRight < idx || idx < nodeLeft) {
			return 0;
		}
		
		if(nodeLeft == idx && nodeRight == idx) {
			return tree[node];
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		long leftValue = query(idx, node*2, nodeLeft, mid);
		long rightValue = query(idx, node*2+1, mid+1, nodeRight);
		
		return leftValue + rightValue;
	}
	
	static long update(int left, int right, long newValue, int node, int nodeLeft, int nodeRight) {
		propagation(node, nodeLeft, nodeRight);
		
		if(nodeRight < left || right < nodeLeft) {
			return tree[node];
		}
		
		if(left <= nodeLeft && nodeRight <= right) {
			lazy[2*node] += newValue;
			lazy[2*node+1] += newValue;
			return tree[node] += (nodeRight - nodeLeft + 1) * newValue;
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		long leftValue = update(left, right, newValue, node*2, nodeLeft, mid);
		long rightValue = update(left, right, newValue, node*2+1, mid+1, nodeRight);
		
		return tree[node] = leftValue + rightValue;
	}
	
	static void propagation(int node, int nodeLeft, int nodeRight) {
		if(lazy[node] != 0) {
			tree[node] += lazy[node] * (nodeRight - nodeLeft + 1);
			lazy[2*node] += lazy[node];
			lazy[2*node+1] += lazy[node];
			
			lazy[node] = 0;
		}
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
			boolean neg = false;
			while ((b = read()) <= 32)
				;
			if (b == '-') {
				neg = true;
				b = read();
			}
			do
				n = (n << 3) + (n << 1) + (b & 15);
			while (isNumber(b = read()));
			if (neg)
				return -n;
			return n;
		}

		long nextLong() throws Exception {
			long n = 0;
			byte b;
			boolean neg = false;
			while ((b = read()) <= 32)
				;
			if (b == '-') {
				neg = true;
				b = read();
			}
			do
				n = (n << 3) + (n << 1) + (b & 15);
			while (isNumber(b = read()));
			if (neg)
				return -n;
			return n;
		}
	}
}