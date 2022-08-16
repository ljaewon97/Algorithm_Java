package boj.g5;

import java.util.Arrays;
import java.util.PriorityQueue;

public class G5_19598_최소_회의실_개수 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = in.nextInt();
		
		int[][] meetings = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			meetings[i][0] = in.nextInt();
			meetings[i][1] = in.nextInt();
		}
		
		Arrays.sort(meetings, (o1, o2) -> o1[0] - o2[0]);
		
		int ans = 1;
		pq.add(meetings[0][1]);
		
		for(int i = 1; i < N; i++) {
			if(pq.peek() > meetings[i][0]) {
				ans++;
				pq.add(meetings[i][1]);
			}
			else {
				pq.poll();
				pq.add(meetings[i][1]);
			}
		}
		
		System.out.println(ans);
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
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
