package boj.g1;

public class G1_11505_구간_곱_구하기 {
	static StringBuilder sb = new StringBuilder();
	static Reader in = new Reader();
	static int N, M, K;
	static final int mod = 1000000007;
	static int[] tree, arr;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		tree = new int[4*N];
		arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		buildTree(1, 0, N-1);
		
		for(int i = 0; i < M+K; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			if(a == 1) {
				update(b-1, c, 1, 0, N-1);
			}
			else {
				sb.append(query(b-1, c-1, 1, 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static long buildTree(int node, int nodeLeft, int nodeRight) throws Exception {
		if(nodeLeft == nodeRight) {
			return tree[node] = arr[nodeLeft];
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		long leftValue = buildTree(node*2, nodeLeft, mid);
		long rightValue = buildTree(node*2+1, mid+1, nodeRight);
		
		return tree[node] = (int) (((leftValue % mod) * (rightValue % mod)) % mod);
	}
	
	static long query(int left, int right, int node, int nodeLeft, int nodeRight) {
		if(nodeRight < left || right < nodeLeft) {
			return 1;
		}
		
		if(left <= nodeLeft && nodeRight <= right) {
			return tree[node];
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return ((query(left, right, node*2, nodeLeft, mid) % mod) * (query(left, right, node*2+1, mid+1, nodeRight) % mod)) % mod;
	}
	
	static long update(int idx, int newValue, int node, int nodeLeft, int nodeRight) {
		if(nodeRight < idx || idx < nodeLeft) {
			return tree[node];
		}
		
		if(nodeLeft == nodeRight) {
			return tree[node] = newValue;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		long leftValue = update(idx, newValue, node*2, nodeLeft, mid);
		long rightValue = update(idx, newValue, node*2+1, mid+1, nodeRight);
		
		return tree[node] = (int) (((leftValue % mod) * (rightValue % mod)) % mod);
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
			do
				n = (n << 3) + (n << 1) + (c & 15);
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
