package boj.g1;

import java.util.LinkedList;
import java.util.Queue;

public class G1_17114_하이퍼_토마토 {
	static Reader in = new Reader();
	static Queue<Point> queue = new LinkedList<>();
	static int[][][][][][][][][][][] stor;
	static int M, N, O, P, Q, R, S, T, U, V, W, cnt;
	
	public static void main(String[] args) throws Exception {
		M = in.nextInt();
		N = in.nextInt();
		O = in.nextInt();
		P = in.nextInt();
		Q = in.nextInt();
		R = in.nextInt();
		S = in.nextInt();
		T = in.nextInt();
		U = in.nextInt();
		V = in.nextInt();
		W = in.nextInt();
		
		stor = new int[W][V][U][T][S][R][Q][P][O][N][M];
		
		for(int w = 0; w < W; w++) {
			for(int v = 0; v < V; v++) {
				for(int u = 0; u < U; u++) {
					for(int t = 0; t < T; t++) {
						for(int s = 0; s < S; s++) {
							for(int r = 0; r < R; r++) {
								for(int q = 0; q < Q; q++) {
									for(int p = 0; p < P; p++) {
										for(int o = 0; o < O; o++) {
											for(int n = 0; n < N; n++) {
												for(int m = 0; m < M; m++) {
													int type = in.nextInt();
													stor[w][v][u][t][s][r][q][p][o][n][m] = type;
													
													if(type == 0) cnt++;
													else if(type == 1) queue.add(new Point(m, n, o, p, q, r, s, t, u, v, w, 1));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		if(cnt == 0) {
			System.out.println(0);
			return;
		}
		
		int res = bfs();
		
		if(cnt != 0) System.out.println(-1);
		else System.out.println(res-1);
	}
	
	static int bfs() {
		int ret = 0;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			ret = Math.max(ret, p.d);
			
			int nm = p.m - 1;
			if(nm >= 0 && stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][nm] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][nm] = p.d + 1;
				queue.add(new Point(nm, p.n, p.o, p.p, p.q, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			nm = p.m + 1;
			if(nm < M && stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][nm] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][nm] = p.d + 1;
				queue.add(new Point(nm, p.n, p.o, p.p, p.q, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			int nn = p.n - 1;
			if(nn >= 0 && stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][nn][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][nn][p.m] = p.d + 1;
				queue.add(new Point(p.m, nn, p.o, p.p, p.q, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			nn = p.n + 1;
			if(nn < N && stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][nn][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][nn][p.m] = p.d + 1;
				queue.add(new Point(p.m, nn, p.o, p.p, p.q, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			int no = p.o - 1;
			if(no >= 0 && stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][no][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][no][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, no, p.p, p.q, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			no = p.o + 1;
			if(no < O && stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][no][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][p.p][no][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, no, p.p, p.q, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			int np = p.p - 1;
			if(np >= 0 && stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][np][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][np][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, np, p.q, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			np = p.p + 1;
			if(np < P && stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][np][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][p.q][np][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, np, p.q, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			int nq = p.q - 1;
			if(nq >= 0 && stor[p.w][p.v][p.u][p.t][p.s][p.r][nq][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][nq][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, nq, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			nq = p.q + 1;
			if(nq < Q && stor[p.w][p.v][p.u][p.t][p.s][p.r][nq][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][p.r][nq][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, nq, p.r, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			int nr = p.r - 1;
			if(nr >= 0 && stor[p.w][p.v][p.u][p.t][p.s][nr][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][nr][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, nr, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			nr = p.r + 1;
			if(nr < R && stor[p.w][p.v][p.u][p.t][p.s][nr][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][p.s][nr][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, nr, p.s, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			int ns = p.s - 1;
			if(ns >= 0 && stor[p.w][p.v][p.u][p.t][ns][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][ns][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, ns, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			ns = p.s + 1;
			if(ns < S && stor[p.w][p.v][p.u][p.t][ns][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][p.t][ns][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, ns, p.t, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			int nt = p.t - 1;
			if(nt >= 0 && stor[p.w][p.v][p.u][nt][p.s][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][nt][p.s][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, p.s, nt, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			nt = p.t + 1;
			if(nt < T && stor[p.w][p.v][p.u][nt][p.s][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][p.u][nt][p.s][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, p.s, nt, p.u, p.v, p.w, p.d+1));
				cnt--;
			}
			
			int nu = p.u - 1;
			if(nu >= 0 && stor[p.w][p.v][nu][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][nu][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, p.s, p.t, nu, p.v, p.w, p.d+1));
				cnt--;
			}
			
			nu = p.u + 1;
			if(nu < U && stor[p.w][p.v][nu][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][p.v][nu][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, p.s, p.t, nu, p.v, p.w, p.d+1));
				cnt--;
			}
			
			int nv = p.v - 1;
			if(nv >= 0 && stor[p.w][nv][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][nv][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, p.s, p.t, p.u, nv, p.w, p.d+1));
				cnt--;
			}
			
			nv = p.v + 1;
			if(nv < V && stor[p.w][nv][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[p.w][nv][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, p.s, p.t, p.u, nv, p.w, p.d+1));
				cnt--;
			}
			
			int nw = p.w - 1;
			if(nw >= 0 && stor[nw][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[nw][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, p.s, p.t, p.u, p.v, nw, p.d+1));
				cnt--;
			}
			
			nw = p.w + 1;
			if(nw < W && stor[nw][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] == 0) {
				stor[nw][p.v][p.u][p.t][p.s][p.r][p.q][p.p][p.o][p.n][p.m] = p.d + 1;
				queue.add(new Point(p.m, p.n, p.o, p.p, p.q, p.r, p.s, p.t, p.u, p.v, nw, p.d+1));
				cnt--;
			}
		}
		
		return ret;
	}
	
	static class Point {
		int m, n, o, p, q, r, s, t, u, v, w, d;

		public Point(int m, int n, int o, int p, int q, int r, int s, int t, int u, int v, int w, int d) {
			this.m = m;
			this.n = n;
			this.o = o;
			this.p = p;
			this.q = q;
			this.r = r;
			this.s = s;
			this.t = t;
			this.u = u;
			this.v = v;
			this.w = w;
			this.d = d;
		}
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