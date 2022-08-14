package boj.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class P4_16989_Baaaaaaaaaduk2_Hard {
	static Reader in = new Reader();
	static int[][] map, codes;
	static boolean[][] visited;
	static Map<Integer, Integer> cand1 = new HashMap<>();
	static Map<Integer, Integer> cand2 = new HashMap<>();
	static int N, M;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		// for debug
		// long start = System.nanoTime();
		
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N][M];
		codes = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		int code = 2;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 2 && codes[i][j] == 0) {
					int around = 0;
					
					for(int k = 0; k < 4; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						
						if(isIn(nr, nc) && map[nr][nc] == 0) {
							around++;
						}
					}
					
					if(around > 2) continue;
					
					bfs(i, j, code);
					code++;
				}
			}
		}
		
		List<Entry<Integer, Integer>> entryList1 = new ArrayList<>(cand1.entrySet());
		List<Entry<Integer, Integer>> entryList2 = new ArrayList<>(cand2.entrySet());
		
		Collections.sort(entryList1, new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		
		Collections.sort(entryList2, new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		
		int ans1 = 0, idx = 0;
		for(Entry<Integer, Integer> entry: entryList1) {
			if(idx == 2) break;
			
			ans1 += entry.getValue();
			idx++;
		}
		
		int ans2 = 0;
		idx = 0;
		for(Entry<Integer, Integer> entry: entryList2) {
			if(idx == 1) break;
			
			ans2 += entry.getValue();
			idx++;
		}
		
		System.out.println(Math.max(ans1, ans2));
		
		// for debug
		// long end = System.nanoTime();
		// System.out.println((1.0) * (end - start) / 1000000000 + "s");
	}
	
	static void bfs(int sr, int sc, int code) {
		Deque<int[]> deque = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		visited = new boolean[N][M];
		
		deque.add(new int[] {sr, sc});
		codes[sr][sc] = 1;
		int cnt0 = 0, cnt2 = 1;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && map[nr][nc] == 2 && codes[nr][nc] == 0) {
					deque.add(new int[] {nr, nc});
					cnt2++;
					codes[nr][nc] = 1;
				}
				else if(isIn(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc] && codes[nr][nc] == 0) {
					cnt0++;
					codes[nr][nc] = code;
					visited[nr][nc] = true;
					list.add(code);
				}
				else if(isIn(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc] && codes[nr][nc] != code) {
					cnt0++;
					visited[nr][nc] = true;
					if(!cand1.containsKey(codes[nr][nc]) && !cand2.containsKey(codes[nr][nc])) {
						list.add(code);
					}
					else {
						list.add(codes[nr][nc]);
					}
				}
			}
		}
		
		if(cnt0 == 1) {
			int curCode = list.get(0);
			if(cand1.containsKey(curCode)) {
				cand1.put(curCode, cand1.get(curCode) + cnt2);
			}
			else if(cand2.containsKey(curCode)) {
				cand2.put(curCode, cand2.get(curCode) + cnt2);
			}
			else {
				cand1.put(curCode, cnt2);
			}
		}
		else if(cnt0 == 2) {
			int code1 = list.get(0);
			int code2 = list.get(1);
			
			if(code1 == code2) {
				if(cand2.containsKey(code1)) {
					cand2.put(code1, cand2.get(code1) + cnt2);
				}
				else {
					cand2.put(code1, cnt2);
				}
			}
			else {
				int c1 = code1 != code ? (cand1.containsKey(code1) ? cand1.get(code1) : 0) : 0;
				int c2 = code2 != code ? (cand1.containsKey(code2) ? cand1.get(code2) : 0) : 0;
				
				cand2.put(code, c1 + c2 + cnt2);
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
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
