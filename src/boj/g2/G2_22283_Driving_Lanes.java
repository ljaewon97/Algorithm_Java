package boj.g2;

import java.util.Arrays;
import java.util.PriorityQueue;

public class G2_22283_Driving_Lanes {
	static Reader in = new Reader();
	static int n, m, k, r;
	static int[] st;
	static int[][] cu;
	
	public static void main(String[] args) throws Exception {
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		r = in.nextInt();
		
		st = new int[n];
		cu = new int[n-1][2];
		
		for(int i = 0; i < n; i++) {
			st[i] = in.nextInt();
		}
		
		for(int i = 0; i < n-1; i++) {
			cu[i][0] = in.nextInt();
			cu[i][1] = in.nextInt();
		}
		
		System.out.println(dijkstra());
	}
	
	static int dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		int[][] dist = new int[2*n][m+1];
		
		for(int i = 0; i < 2*n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		pq.add(new int[] {0, 1, 0});
		dist[0][1] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int cur_dest = cur[0];
			int cur_lane = cur[1];
			int cur_dist = cur[2];
			
			if(dist[cur_dest][cur_lane] < cur_dist) continue;
			if(cur_dest == 2*n-1) continue;
			
			int next_dest = cur[0] + 1;
			
			if(cur_dest % 2 == 0) {
				int len = st[cur_dest/2];
				
				for(int i = 0; i <= len; i += k) {
					int lane = cur_lane + i/k;
					int sum = cur_dist + len + i/k*r;

					if(lane > m) break;
					
					if(sum < dist[next_dest][lane]) {
						dist[next_dest][lane] = sum;
						pq.add(new int[] {next_dest, lane, sum});
					}
				}
				
				for(int i = 0; i <= len; i += k) {
					int lane = cur_lane - i/k;
					int sum = cur_dist + len + i/k*r;
					
					if(lane < 1) break;
					
					if(sum < dist[next_dest][lane]) {
						dist[next_dest][lane] = sum;
						pq.add(new int[] {next_dest, lane, sum});
					}
				}
			}
			else {
				int s = cu[(cur_dest-1)/2][0];
				int c = cu[(cur_dest-1)/2][1];
				
				int sum = cur_dist + s + c * cur_lane;
				
				if(sum < dist[next_dest][cur_lane]) {
					dist[next_dest][cur_lane] = sum;
					pq.add(new int[] {next_dest, cur_lane, sum});
				}
			}
		}
		
		return dist[2*n-1][1];
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
