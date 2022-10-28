package boj.p5;

import java.util.PriorityQueue;
import java.util.Stack;

public class P5_02699_격자점_컨벡스헐 {
	static Reader in = new Reader();
	static Point start;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int P = in.nextInt();
		
		while(P-- > 0) {
			int N = in.nextInt();
			
			Point[] points = new Point[N];
			
			for(int i = 0; i < N; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				
				points[i] = new Point(x, y);
				
				if(i == 0) start = points[0];
				else {
					if(y > start.y) start = points[i];
					else if(y == start.y && x < start.x) start = points[i];
				}
			}
			
			PriorityQueue<Point> pq = new PriorityQueue<>();
			
			for(int i = 0; i < N; i++) {
				if(points[i] == start) continue;
				pq.add(points[i]);
			}
			
			Stack<Point> stack = new Stack<>();
			stack.push(start);
			stack.push(pq.poll());
			
			Point peek2 = start;
			
			while(!pq.isEmpty()) {
				Point cur = pq.poll();
				int ccw = ccw(peek2, stack.peek(), cur);
				
				if(ccw > 0) {
					stack.pop();
					Point temp = stack.pop();
					peek2 = stack.peek();
					stack.push(temp);
					pq.add(cur);
				}
				else if(ccw == 0) {
					stack.pop();
					stack.push(cur);
				}
				else {
					peek2 = stack.peek();
					stack.push(cur);
				}
			}
			
			sb.append(stack.size()).append("\n");
			
			for(Point p: stack) {
				sb.append(String.format("%d %d\n", p.x, p.y));
			}
		}
		
		System.out.println(sb);
	}
	
	static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int compareTo(Point o) {
			int ccw = ccw(start, this, o);
			return ccw > 0 ? 1 : ccw < 0 ? -1 : dist(start, this)-dist(start, o);
		}
	}
	
	static int ccw(Point a, Point b, Point c) {
		long res = a.x*b.y + b.x*c.y + c.x*a.y - b.x*a.y - c.x*b.y - a.x*c.y;
		return res > 0 ? 1 : (res == 0 ? 0 : -1);
	}
	
	static int dist(Point a, Point b) {
		return (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y); 
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
			return neg ? -n : n;
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
