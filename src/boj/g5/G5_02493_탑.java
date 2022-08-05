package boj.g5;

import java.util.Stack;

public class G5_02493_탑 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int N = in.nextInt();
		int[] tower = new int[N];
		Stack<Integer[]> stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			tower[i] = in.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			while(!stack.isEmpty()) {
				if(stack.peek()[1] > tower[i]) {
					sb.append(stack.peek()[0] + 1).append(" ");
					break;
				}
				else {
					stack.pop();
				}
			}
			
			if(stack.isEmpty()) {
				sb.append("0 ");
			}
			
			stack.push(new Integer[] {i, tower[i]});
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
