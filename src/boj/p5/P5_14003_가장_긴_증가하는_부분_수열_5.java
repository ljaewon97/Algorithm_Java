package boj.p5;

import java.util.Stack;

public class P5_14003_가장_긴_증가하는_부분_수열_5 {
	static Reader in = new Reader();
	static int[][] C;
	static int[] arr, path;
	static int N, size;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		arr = new int[N];
		C = new int[N][2];
		path = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			path[i] = -1;
			int l = 0;
			int r = size - 1;
			
			while(l <= r) {
				int mid = (l + r) >> 1;
				
				if(C[mid][0] < arr[i]) l = mid + 1;
				else if(C[mid][0] > arr[i]) r = mid - 1;
				else {
					l = -1;
					break;
				}
			}
			
			if(l == -1) continue;
			
			C[l][0] = arr[i];
			C[l][1] = i;
			path[i] = l > 0 ? C[l-1][1] : -1;
			if(l == size) size++;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(size).append("\n");
		
		Stack<Integer> stack = new Stack<>();
		int idx = C[size-1][1];
		
		while(path[idx] != -1) {
			stack.push(arr[idx]);
			idx = path[idx];
		}
		
		stack.push(arr[idx]);
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(sb);
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
