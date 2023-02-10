package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class G4_02044_windowns {
	static List<Window> windows = new ArrayList<>();
	static char[][] screen, ans;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		screen = new char[N][];
		ans = new char[N][M];
		
		for(int i = 0; i < N; ++i) {
			screen[i] = br.readLine().toCharArray();
		}
		
		boolean start = false;
		int idx = 0;
		
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				char pixel = screen[i][j];
				
				if(pixel == '+') {
					if(start) {
						start = false;
						
						String cand = new String(screen[i], idx, j-idx+1);
						st = new StringTokenizer(cand, "|");
						
						if(st.countTokens() == 1) continue;
						
						int l = st.nextToken().length()-1;
						String title = st.nextToken();
						int r = st.nextToken().length()-1;
						
						int down = -1;
						
						for(int k = i+1; k < N; ++k) {
							if(screen[k][j] == '+') {
								down = k;
								break;
							}
						}
						
						int h = down-i+1;
						
						windows.add(new Window(l, r, h, title));
					}
					else {
						start = true;
						idx = j;
					}
				}
			}
		}
		
		Collections.sort(windows);
		
		for(int i = 0; i < N; ++i) {
			Arrays.fill(ans[i], '.');
		}
		
		idx = 0;
		
		for(Window window: windows) {
			printWindow(idx++, window);
		}
		
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				sb.append(ans[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void printWindow(int i, Window window) {
		int wl = window.l;
		int wr = window.r;
		int wh = window.h;
		int tlength = window.title.length();
		
		ans[i][i] = '+';
		for(int c = i+1; c < i+1+wl; ++c) {
			ans[i][c] = '-';
		}
		ans[i][i+wl+1] = ans[i][i+wl+1+tlength+1] = '|';
		for(int c = i+wl+2; c < i+wl+2+tlength; ++c) {
			ans[i][c] = window.title.charAt(c-i-wl-2);
		}
		for(int c = i+wl+tlength+3; c < i+wl+tlength+3+wr; ++c) {
			ans[i][c] = '-';
		}
		ans[i][i+wl+tlength+3+wr] = '+';
		
		int lv = i;
		int rv = i+wl+tlength+3+wr;
		
		for(int r = i+1; r < i+1+wh-2; ++r) {
			ans[r][lv] = ans[r][rv] = '|';
		}
		
		int bt = i+wh-1;
		int len = tlength+wl+wr+4;
		
		ans[bt][i] = ans[bt][i+len-1] = '+';
		for(int c = i+1; c < i+len-1; ++c) {
			ans[bt][c] = '-';
		}
		
		// clear inside
		for(int r = i+1; r < i+1+wh-2; ++r) {
			for(int c = i+1; c < i+1+len-2; ++c) {
				ans[r][c] = '.';
			}
		}
	}
	
	static class Window implements Comparable<Window> {
		int l, r, h;
		String title;
		
		public Window(int l, int r, int h, String title) {
			this.l = l;
			this.r = r;
			this.h = h;
			this.title = title;
		}

		@Override
		public int compareTo(Window o) {
			return this.title.compareTo(o.title);
		}
	}
}
