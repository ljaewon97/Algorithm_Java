package boj.p4;

public class P4_10999_구간_합_구하기_2 {
	static Reader in = new Reader();
	static long[] tree, arr, lazy;
	static int N, M, K;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		tree = new long[8*N+1];
		lazy = new long[8*N+1];
		arr = new long[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextLong();
		}
		
		buildTree(1, 0, N-1);
		
		for(int i = 0; i < M+K; i++) {
			int a = in.nextInt();
			int b = in.nextInt() - 1;
			int c = in.nextInt() - 1;
			if(a == 1) {
				long d = in.nextLong();
				update(b, c, d, 1, 0, N-1);
			}
			else {
				sb.append(query(b, c, 1, 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static long buildTree(int node, int nodeLeft, int nodeRight) {
		if(nodeLeft == nodeRight) {
			return tree[node] = arr[nodeLeft];
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		long leftValue = buildTree(2*node, nodeLeft, mid);
		long rightValue = buildTree(2*node+1, mid+1, nodeRight);
		
		return tree[node] = leftValue + rightValue;
	}
	
	static long query(int left, int right, int node, int nodeLeft, int nodeRight) {
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
	
	static long update(int idxLeft, int idxRight, long newValue, int node, int nodeLeft, int nodeRight) {
		propagation(node, nodeLeft, nodeRight);
		
		if(nodeRight < idxLeft || idxRight < nodeLeft) {
			return tree[node];
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		
		if(idxLeft <= nodeLeft && nodeRight <= idxRight) {
			lazy[2*node] += newValue;
			lazy[2*node+1] += newValue;
			return tree[node] += (nodeRight - nodeLeft + 1) * newValue;
		}
		
		long leftValue = update(idxLeft, idxRight, newValue, 2*node, nodeLeft, mid);
		long rightValue = update(idxLeft, idxRight, newValue, 2*node+1, mid+1, nodeRight);
		
		return tree[node] = leftValue + rightValue;
	}
	
	static void propagation(int node, int nodeLeft, int nodeRight) {
		if(lazy[node] != 0) {
			tree[node] += lazy[node] * (nodeRight - nodeLeft + 1);
			lazy[2*node] += lazy[node];
			lazy[2*node+1] += lazy[node];
		}
		else {
			return;
		}
		
		lazy[node] = 0;
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int idx, size;
		
		boolean isNumber(byte b) {
			return 47 < b && b < 58;
		}
		
		byte read() throws Exception {
			if(idx == size) {
				size = System.in.read(buffer, idx = 0, SIZE);
				if(size < 0) {
					buffer[0] = -1;
				}
			}
			return buffer[idx++];
		}
		
		int nextInt() throws Exception {
			int n = 0;
			byte b;
			boolean neg = false;
			while((b = read()) <= 32);
			if(b == '-') {
				neg = true;
				b = read();
			}
			do n = (n << 3) + (n << 1) + (b & 15);
			while(isNumber(b = read()));
			if(neg) return -n;
			return n;
		}
		
		long nextLong() throws Exception {
			long n = 0;
			byte b;
			boolean neg = false;
			while((b = read()) <= 32);
			if(b == '-') {
				neg = true;
				b = read();
			}
			do n = (n << 3) + (n << 1) + (b & 15);
			while(isNumber(b = read()));
			if(neg) return -n;
			return n;
		}
	}
}