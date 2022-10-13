package boj.p5;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class P5_01708_볼록_껍질 {
	static Reader in = new Reader();
	static final double e = 0.000000001;
	
	public static void main(String[] args) throws Exception {
		long N = in.nextLong();
		
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if(o1.y != o2.y) return Long.compare(o1.y, o2.y);
				return Long.compare(o1.x, o2.x);
			}
		});
		
		for(int i = 0; i < N; i++) {
			pq.add(new Point(in.nextLong(), in.nextLong()));
		}
		
		Point start = pq.poll();
		PriorityQueue<Point> pq2 = new PriorityQueue<>();
		
		for(Point point: pq) {
			point.len = calcDist(start, point);
			Vector vec = new Vector(point.x-start.x, point.y-start.y);
			point.cos = vec.x / vec.length();
			pq2.add(point);
		}
		
		Stack<Point> stack = new Stack<>();
		stack.push(start);
		Point second = start;
		stack.push(pq2.poll());
		
		while(!pq2.isEmpty()) {
			Point cur = pq2.poll();
			
			Vector a = new Vector(stack.peek().x-second.x, stack.peek().y-second.y);
			Vector b = new Vector(cur.x-stack.peek().x, cur.y-stack.peek().y);
			
			long cross = a.x*b.y-a.y*b.x;
			
			if(cross > 0) {
				second = stack.peek();
				stack.push(cur);
			}
			else if(cross == 0) {
				stack.pop();
				stack.push(cur);
			}
			else {
				stack.pop();
				Point temp = stack.pop();
				second = stack.peek();
				stack.push(temp);
				pq2.add(cur);
			}
		}
		
		System.out.println(stack.size());
	}
	
	static double calcDist(Point a, Point b) {
		return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}
	
	static class Point implements Comparable<Point> {
		long x, y;
		double cos, len;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if(Math.abs(this.cos - o.cos) <= e) return Double.compare(this.len, o.len);
			return Double.compare(o.cos, this.cos);
		}
	}
	
	static class Vector {
		long x, y;
		
		public Vector(long x, long y) {
			this.x = x;
			this.y = y;
		}
		
		double length() {
			return Math.sqrt(x*x+y*y);
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

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