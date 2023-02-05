package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_27375_금공강_사수 {
	static Lecture[] lectures;
	static boolean[][] visited;
	static int n, nn, k, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		lectures = new Lecture[n];
		visited = new boolean[6][11];
		
		for(int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(w == 5) continue;
			
			lectures[nn++] = new Lecture(w, s, e);
		}
		
		solve(0, 0, 0);
		
		System.out.println(ans);
	}
	
	static void solve(int nth, int start, int cnt) {
		if(cnt == k) {
			++ans;
			return;
		}
		
		if(nth == nn) return;
		
		for(int i = start; i < nn; ++i) {
			if(can(i)) {
				visit(i);
				solve(nth+1, i+1, cnt+lectures[i].c);
				unvisit(i);
			}
		}
	}
	
	static boolean can(int no) {
		Lecture l = lectures[no];
		
		for(int i = l.s; i <= l.e; ++i) {
			if(visited[l.w][i]) return false;
		}
		
		return true;
	}
	
	static void visit(int no) {
		Lecture l = lectures[no];
		
		for(int i = l.s; i <= l.e; ++i) {
			visited[l.w][i] = true;
		}
	}
	
	static void unvisit(int no) {
		Lecture l = lectures[no];
		
		for(int i = l.s; i <= l.e; ++i) {
			visited[l.w][i] = false;
		}
	}
	
	static class Lecture {
		int w, s, e, c;
		
		public Lecture(int w, int s, int e) {
			this.w = w;
			this.s = s;
			this.e = e;
			this.c = e-s+1;
		}
	}
}
