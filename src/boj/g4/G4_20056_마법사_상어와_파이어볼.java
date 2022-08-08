package boj.g4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_20056_마법사_상어와_파이어볼 {
	static int N, M, K;
	static List<Fireball>[][] map;
	static int[] dr;
	static int[] dc;
	
	public static class Fireball {
		int m, d, s;
		
		public Fireball(int m, int s, int d) {
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		dr = new int[] {N-1,N-1,0,1,1,1,0,N-1};
		dc = new int[] {0,1,1,1,0,N-1,N-1,N-1};
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<Fireball>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r][c].add(new Fireball(m, s, d));
		}
		
		for(int i = 0; i < K; i++) {
			move();
			mergeDivide();
		}
		
		System.out.println(calcMassSum());
	}
	
	static void move() {
		int[][] check = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c].size() - check[r][c] > 0) {
					int iter = map[r][c].size() - check[r][c];
					for(int i = 0; i < iter; i++) {
						Fireball temp = map[r][c].remove(0);
						int nr = (r + temp.s * dr[temp.d]) % N;
						int nc = (c + temp.s * dc[temp.d]) % N;
						check[nr][nc] += 1;
						map[nr][nc].add(temp);
					}
				}
			}
		}
	}
	
	static void mergeDivide() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c].size() >= 2) {
					boolean allOdd = true, allEven = true;
					int mSum = 0, sSum = 0;
					int iter = map[r][c].size();
					for(int i = 0; i < iter; i++) {
						Fireball temp = map[r][c].remove(0);
						mSum += temp.m;
						sSum += temp.s;
						if(temp.d % 2 == 1) {
							allEven = false;
						}
						else {
							allOdd = false;
						}
					}
					
					int nm = mSum / 5;
					int ns = sSum / iter;
					
					if(nm == 0) {
						continue;
					}
					
					int dd = 0;
					if(!(allOdd || allEven)) {
						dd++;
					}
					
					for(int i = dd; i < 8; i += 2) {
						map[r][c].add(new Fireball(nm, ns, i));
					}
				}
			}
		}
	}
	
	static int calcMassSum() {
		int sum = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(!map[r][c].isEmpty()) {
					for(int i = 0; i < map[r][c].size(); i++) {
						sum += map[r][c].get(i).m;
					}
				}
			}
		}
		
		return sum;
	}
}
