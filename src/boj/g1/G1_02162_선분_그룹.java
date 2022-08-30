package boj.g1;

public class G1_02162_선분_그룹 {
	static Reader in = new Reader();
	static int[] parent;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		int[][] lines = new int[N][4];
		parent = new int[N];
		
		for(int i = 0; i < N; i++) {
			parent[i] = i;
			
			lines[i][0] = in.nextInt();
			lines[i][1] = in.nextInt();
			lines[i][2] = in.nextInt();
			lines[i][3] = in.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				if(cross(lines[i][0], lines[i][1], lines[i][2], lines[i][3], lines[j][0], lines[j][1], lines[j][2], lines[j][3])) {
					union(i, j);
				}
			}
		}
		
		int[] count = new int[N];
		int max = 0, group = 0;
		
		for(int i = 0; i < N; i++) {
			parent[i] = find(parent[i]);
		}
		
		for(int i = 0; i < N; i++) {
			if(count[parent[i]] == 0) group++;
			count[parent[i]]++;
			max = Math.max(max, count[parent[i]]);
		}
		
		System.out.println(group);
		System.out.println(max);
	}
	
	static int find(int line) {
		if(parent[line] == line) return line;
		return parent[line] = find(parent[line]);
	}
	
	static boolean union(int l1, int l2) {
		int p1 = find(l1);
		int p2 = find(l2);
		
		if(p1 == p2) return false;
		
		if(p1 < p2) {
			parent[p2] = p1;
		}
		else {
			parent[p1] = p2;
		}
		
		return true;
	}
	
	static boolean cross(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		int d123 = CCW(x1, y1, x2, y2, x3, y3);
		int d124 = CCW(x1, y1, x2, y2, x4, y4);
		int d341 = CCW(x3, y3, x4, y4, x1, y1);
		int d342 = CCW(x3, y3, x4, y4, x2, y2);
		
		int v12 = d123 * d124;
		int v34 = d341 * d342;
		
		if(Math.max(x1, x2) < Math.min(x3, x4) || Math.max(x3, x4) < Math.min(x1, x2)) return false;
		if(Math.max(y1, y2) < Math.min(y3, y4) || Math.max(y3, y4) < Math.min(y1, y2)) return false;
		
		if((x1 == x3 && y1 == y3) || (x1 == x4 && y1 == y4)) return true;
		if((x2 == x3 && y2 == y3) || (x2 == x4 && y2 == y4)) return true;
		
		return v12 <= 0 && v34 <= 0;
	}
	
	static int CCW(int x1, int y1, int x2, int y2, int x3, int y3) {
		long value = (long) (x2 - x1) * (y3 - y1) - (long) (x3 - x1) * (y2 - y1);
		return value < 0 ? -1 : value > 0 ? 1 : 0;
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
