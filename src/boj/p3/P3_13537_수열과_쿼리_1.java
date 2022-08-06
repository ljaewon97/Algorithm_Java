package boj.p3;

import java.util.Arrays;

public class P3_13537_수열과_쿼리_1 {
	static Reader in = new Reader();
	static int[] arr, tree;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		arr = new int[N];
		tree = new int[4*N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		buildTree(1, 0, N-1);
		
		M = in.nextInt();
		
		for(int q = 0; q < M; q++) {
			int i = in.nextInt() - 1;
			int j = in.nextInt() - 1;
			int k = in.nextInt();
			
			sb.append(query(i, j, k, 1, 0, N-1)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int buildTree(int node, int nodeLeft, int nodeRight) {
		if(nodeLeft == nodeRight) {
			return tree[node] = arr[nodeLeft];
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		int leftValue = buildTree(2*node, nodeLeft, mid);
		int rightValue = buildTree(2*node+1, mid+1, nodeRight);
		
		return tree[node] = Math.min(leftValue, rightValue);
	}
	
	static int query(int left, int right, int k, int node, int nodeLeft, int nodeRight) {
		if(nodeRight < left || right < nodeLeft) {
			return 0;
		}
		
		if(left <= nodeLeft && nodeRight <= right && tree[node] > k) {
			return nodeRight - nodeLeft + 1;
		}
		
		if(nodeLeft == nodeRight) {
			return 0;
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		
		return query(left, right, k, 2*node, nodeLeft, mid) + query(left, right, k, 2*node+1, mid+1, nodeRight);
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
