package boj.p5;

public class P5_19235_모노미노도미노 {
	static Reader in = new Reader();
	static int[][] green, blue;
	static int score, no = 1;
	
	public static void main(String[] args) throws Exception {
		green = new int[6][4];
		blue = new int[6][4];
		
		int N = in.nextInt();
		
		for(int i = 0; i < N; i++) {
			int t = in.nextInt() - 1;
			int x = in.nextInt();
			int y = in.nextInt();
			
			putBlockOnGreen(t, y);
			putBlockOnBlue(t, 3-x);

			no++;
		}
		
		int ans = 0;
		
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 4; c++) {
				if(blue[r][c] != 0) ans++;
				if(green[r][c] != 0) ans++;
			}
		}
		
		System.out.println(score);
		System.out.println(ans);
	}
	
	static void putBlockOnGreen(int type, int y) {
		if(type == 1) {
			int p1 = findDropRow(0, 0, y);
			int p2 = findDropRow(0, 0, y+1);
			
			int p = Math.min(p1, p2);
			
			green[p][y] = no;
			green[p][y+1] = no;
		}
		else {
			int p = findDropRow(0, 0, y);
			
			if(type == 0) {
				green[p][y] = no;
			}
			else if(type == 2) {
				green[p][y] = no;
				green[p-1][y] = no;
			}
		}
		
		while(true) {
			int row = -1;
			
			for(int r = 5; r > 1; r--) {
				if(green[r][0] != 0 && green[r][1] != 0 && green[r][2] != 0 && green[r][3] != 0) {
					green[r][0] = green[r][1] = green[r][2] = green[r][3] = 0;
					score++;
					row = r;
				}
			}
			
			for(int r = row-1; r >= 0; r--) {
				for(int c = 0; c < 4; c++) {
					if(green[r][c] != 0) {
						int temptype = 0;
						
						if(isIn(r-1, c) && green[r-1][c] == green[r][c]) temptype = 2;
						else if(isIn(r, c-1) && green[r][c-1] == green[r][c]) temptype = 1;
						else if(isIn(r, c+1) && green[r][c+1] == green[r][c]) temptype = 3;
						
						if(temptype % 2 == 1) {
							int p1 = findDropRow(0, r+1, c);
							int c2 = c + (temptype == 1 ? -1 : 1);
							int p2 = findDropRow(0, r+1, c2);
							
							int p = Math.min(p1, p2);
							
							green[p][c] = green[r][c];
							green[p][c2] = green[r][c];
							green[r][c] = 0;
							green[r][c2] = 0;
						}
						else {
							int p = findDropRow(0, r+1, c);
							
							if(temptype == 0) {
								green[p][c] = green[r][c];
								green[r][c] = 0;
							}
							else if(temptype == 2) {
								int temp = green[r][c];
								green[r][c] = 0;
								green[r-1][c] = 0;
								green[p][c] = temp;
								green[p-1][c] = temp;
							}
						}
					}
				}
			}
			
			int cnt = 0;
			
			for(int r = 0; r < 2; r++) {
				for(int c = 0; c < 4; c++) {
					if(green[r][c] != 0) {
						cnt++;
						break;
					}
				}
			}
			
			if(cnt != 0) {
				for(int r = 5; r > 5-cnt; r--) {
					for(int c = 0; c < 4; c++) {
						green[r][c] = 0;
					}
				}
				
				for(int r = 5-cnt; r >= 0; r--) {
					for(int c = 0; c < 4; c++) {
						green[r+cnt][c] = green[r][c];
						green[r][c] = 0;
					}
				}
			}
			
			if(row == -1 && cnt == 0) break;
		}
	}
	
	static void putBlockOnBlue(int type, int y) {
		if(type == 2) {
			int p1 = findDropRow(1, 0, y);
			int p2 = findDropRow(1, 0, y-1);
			
			int p = Math.min(p1, p2);
			
			blue[p][y] = no;
			blue[p][y-1] = no;
		}
		else {
			int p = findDropRow(1, 0, y);
			
			if(type == 0) {
				blue[p][y] = no;
			}
			else if(type == 1) {
				blue[p][y] = no;
				blue[p-1][y] = no;
			}
		}
		
		while(true) {
			int row = -1;
			
			for(int r = 5; r > 1; r--) {
				if(blue[r][0] != 0 && blue[r][1] != 0 && blue[r][2] != 0 && blue[r][3] != 0) {
					blue[r][0] = blue[r][1] = blue[r][2] = blue[r][3] = 0;
					score++;
					row = r;
				}
			}
			
			for(int r = row-1; r >= 0; r--) {
				for(int c = 0; c < 4; c++) {
					if(blue[r][c] != 0) {
						int temptype = 0;
						
						if(isIn(r-1, c) && blue[r-1][c] == blue[r][c]) temptype = 1;
						else if(isIn(r, c-1) && blue[r][c-1] == blue[r][c]) temptype = 2;
						else if(isIn(r, c+1) && blue[r][c+1] == blue[r][c]) temptype = 3;
						
						if(temptype >= 2) {
							int p1 = findDropRow(1, r+1, c);
							int c2 = c + (temptype == 2 ? -1 : 1);
							int p2 = findDropRow(1, r+1, c2);
							
							int p = Math.min(p1, p2);
							
							blue[p][c] = blue[r][c];
							blue[p][c2] = blue[r][c];
							blue[r][c] = 0;
							blue[r][c2] = 0;
						}
						else {
							int p = findDropRow(1, r+1, c);
							
							if(temptype == 0) {
								blue[p][c] = blue[r][c];
								blue[r][c] = 0;
							}
							else if(temptype == 1) {
								int temp = blue[r][c];
								blue[r][c] = 0;
								blue[r-1][c] = 0;
								blue[p][c] = temp;
								blue[p-1][c] = temp;
							}
						}
					}
				}
			}
			
			int cnt = 0;
			
			for(int r = 0; r < 2; r++) {
				for(int c = 0; c < 4; c++) {
					if(blue[r][c] != 0) {
						cnt++;
						break;
					}
				}
			}
			
			if(cnt != 0) {
				for(int r = 5; r > 5-cnt; r--) {
					for(int c = 0; c < 4; c++) {
						blue[r][c] = 0;
					}
				}
				
				for(int r = 5-cnt; r >= 0; r--) {
					for(int c = 0; c < 4; c++) {
						blue[r+cnt][c] = blue[r][c];
						blue[r][c] = 0;
					}
				}
			}
			
			if(row == -1 && cnt == 0) break;
		}	
	}
	
	static int findDropRow(int type, int row, int col) {
		if(type == 0) {
			for(int r = row; r < 6; r++) {
				if(green[r][col] != 0) {
					return r-1;
				}
			}
			
			return 5;
		}
		else {
			for(int r = row; r < 6; r++) {
				if(blue[r][col] != 0) {
					return r-1;
				}
			}
			
			return 5;
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 6 && 0 <= c && c < 4;
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
