package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class P5_04181_Convex_Hull {
	static final double e = 0.000000001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Point> points = new PriorityQueue<>(new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if(o1.x != o2.x) return Long.compare(o1.x, o2.x);
				return Long.compare(o1.y, o2.y);
			}
		});
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(st.nextToken().charAt(0) == 'N') continue;
			
			points.add(new Point(x, y));
		}
		
		sb.append(points.size()).append("\n");
		
		Point start = points.poll();
		sb.append(String.format("%d %d\n", start.x, start.y));
		
		double max = 0;
		double cos = 0;
		
		for(Point point: points) {
			point.len = calcDist(start, point);
			Vector vec = new Vector(point.x-start.x, point.y-start.y);
			point.cos = -vec.y / vec.length();
			
			if(point.len >= max) {
				max = point.len;
				cos = point.cos;
			}
		}
		
		List<Point> ans1 = new ArrayList<>();
		List<Point> ans2 = new ArrayList<>();
		
		for(Point point: points) {
			if(point.cos > cos) ans1.add(point);
			else ans2.add(point);
		}
		
		Collections.sort(ans1, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if(Math.abs(o1.cos - o2.cos) <= e) return Double.compare(o1.len, o2.len);
				return Double.compare(o2.cos, o1.cos);
			}
		});
		
		Collections.sort(ans2, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if(Math.abs(o1.cos - o2.cos) <= e) return Double.compare(o2.len, o1.len);
				return Double.compare(o2.cos, o1.cos);
			}
		});
		
		for(Point point: ans1) {
			sb.append(String.format("%d %d\n", point.x, point.y));
		}
		
		for(Point point: ans2) {
			sb.append(String.format("%d %d\n", point.x, point.y));
		}
		
		System.out.println(sb);
	}
	
	static double calcDist(Point a, Point b) {
		return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}
	
	static class Point {
		long x, y;
		double cos, len;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
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
}
