package boj.g1;

public class G1_21611_마법사_상어와_블리자드 {
	static Reader in = new Reader();
	static int[][] magic, input;
	static int[] map, ans;
	static int N, M;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {-1,0,1,0};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		magic = new int[4][N/2];
		input = new int[N][N];
		map = new int[N*N];
		ans = new int[4];
		
		initMagic();
		
		for(int r = 0; r < N; ++r) {
			for(int c = 0; c < N; ++c) {
				input[r][c] = in.nextInt();
			}
		}
		
		initMap();
		
		while(M-- > 0) {
			int d = convertDir(in.nextInt());
			int s = in.nextInt();
			
			ice(d, s);
			move();
			
			while(check()) {
				move();
			}
			
			change();
		}
		
		System.out.println(ans[1] + 2*ans[2] + 3*ans[3]);
	}
	
	static void initMagic() {
		magic[0][0] = 1;
		
		for(int i = 1; i < N/2; ++i) {
			magic[0][i] = magic[0][i-1] + 9 + 8*(i-1);
		}
		
		for(int i = 0; i < N/2; ++i) {
			for(int j = 1; j < 4; ++j) {
				magic[j][i] = magic[j-1][i] + 2*(i+1);
			}
		}
	}
	
	static void initMap() {
		int r = N/2, c = N/2, d = 0, no = 1;
		
		for(int i = 1; i < N; ++i) {
			for(int j = 0; j < i; ++j) {
				r += dr[d];
				c += dc[d];
				map[no++] = input[r][c];
			}
			
			d = (d+1)%4;
			
			for(int j = 0; j < i; ++j) {
				r += dr[d];
				c += dc[d];
				map[no++] = input[r][c];
			}
			
			d = (d+1)%4;
		}
		
		for(int j = 0; j < N-1; ++j) {
			r += dr[d];
			c += dc[d];
			map[no++] = input[r][c];
		}
	}
	
	static int convertDir(int d) {
		if(d == 1) return 3;
		else if(d == 2) return 1;
		else if(d == 3) return 0;
		else return 2;
	}
	
	static void ice(int d, int s) {
		for(int i = 0; i < s; ++i) {
			map[magic[d][i]] = 0;
		}
	}
	
	static void move() {
		int NN = N*N;
		int cnt = 0;
		
		for(int i = 1; i < NN; ++i) {
			if(map[i] == 0) ++cnt;
			else {
				map[i-cnt] = map[i];
				if(cnt != 0) map[i] = 0;
			}
		}
	}
	
	static boolean check() {
		int NN = N*N;
		boolean ret = false;
		
		int prev = -1, cnt = 0, i = 1;
		
		while(i < NN) {
			if(map[i] == 0) break;
			
			if(map[i] == prev) {
				++cnt;
			}
			else {
				if(cnt >= 4) {
					ret = true;
					ans[prev] += cnt;
					for(int j = i-1; j >= i-cnt; --j) {
						map[j] = 0;
					}
				}
				
				cnt = 1;
			}
			
			prev = map[i];
			++i;
		}
		
		if(cnt >= 4) {
			ret = true;
			ans[prev] += cnt;
			for(int j = i-1; j >= i-cnt; --j) {
				map[j] = 0;
			}
		}
		
		return ret;
	}
	
	static void change() {
		int NN = N*N;
		int[] nmap = new int[NN];
		int prev = -1, cnt = 0, i = 1, idx = 1;
		
		while(i < NN) {
			if(map[i] == 0) break;
			
			if(map[i] == prev) {
				++cnt;
			}
			else {
				if(cnt > 0 && 2*idx <= NN-1) {
					nmap[2*idx-1] = cnt;
					nmap[2*idx] = prev;
					idx++;
				}
				
				cnt = 1;
			}
			
			prev = map[i];
			++i;
		}
		
		if(cnt > 0 && 2*idx <= NN-1) {
			nmap[2*idx-1] = cnt;
			nmap[2*idx] = prev;
			idx++;
		}
		
		map = nmap;
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
