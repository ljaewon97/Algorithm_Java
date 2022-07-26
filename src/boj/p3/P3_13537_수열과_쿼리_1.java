package boj.p3;

public class P3_13537_수열과_쿼리_1 {
	static Reader in = new Reader();
	static int[] arr;
	static int[][] tree;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		arr = new int[N];
		tree = new int[4*N][];
		
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
	
	static int[] buildTree(int node, int nodeLeft, int nodeRight) {
		if(nodeLeft == nodeRight) {
			return tree[node] = new int[] {arr[nodeLeft]};
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		int[] leftArr = buildTree(2*node, nodeLeft, mid);
		int[] rightArr = buildTree(2*node+1, mid+1, nodeRight);
		
		return tree[node] = merge(leftArr, rightArr);
	}
	
	static int[] merge(int[] leftArr, int[] rightArr) {
		int[] res = new int[leftArr.length + rightArr.length];
		int leftIdx = 0, rightIdx = 0, idx = 0;
		
		while(leftIdx < leftArr.length && rightIdx < rightArr.length) {
			if(leftArr[leftIdx] <= rightArr[rightIdx]) {
				res[idx++] = leftArr[leftIdx++];
			}
			else {
				res[idx++] = rightArr[rightIdx++];
			}
		}
		
		while(leftIdx < leftArr.length) {
			res[idx++] = leftArr[leftIdx++];
		}
		
		while(rightIdx < rightArr.length) {
			res[idx++] = rightArr[rightIdx++];
		}
		
		return res;
	}
	
	static int query(int left, int right, int k, int node, int nodeLeft, int nodeRight) {
		if(nodeRight < left || right < nodeLeft) {
			return 0;
		}
		
		if(left <= nodeLeft && nodeRight <= right) {
			return binarySearch(tree[node], k);
		}
		
		int mid = (nodeLeft + nodeRight) / 2;
		return query(left, right, k, 2*node, nodeLeft, mid) + query(left, right, k, 2*node+1, mid+1, nodeRight);
	}
	
	static int binarySearch(int[] arr, int value) {
		int left = 0;
		int right = arr.length - 1;
		int mid = 0;
		
		if(arr[right] < value) {
			return 0;
		}
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			if(arr[mid] < value) {
				left = mid + 1;
			}
			else if (arr[mid] > value) {
				right = mid - 1;
			}
			else {
				while(mid <= arr.length - 1 && arr[mid] == value) mid++;
				return arr.length - mid;
			}
		}
		
		while(arr[mid] < value) mid++;
		return arr.length - mid;
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
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
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
