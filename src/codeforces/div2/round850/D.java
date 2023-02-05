package codeforces.div2.round850;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class D {
	static Queue<Integer>[] giveT;
	static Queue<Integer>[] needT;
	static boolean[] fin;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringBuilder tc = new StringBuilder();
			int cnt = 0;
			
			int m = Integer.parseInt(br.readLine());
			
			giveT = new LinkedList[3];
			needT = new LinkedList[3];
			
			for(int i = 0; i < 3; ++i) {
				giveT[i] = new LinkedList<>();
				needT[i] = new LinkedList<>();
			}
			
			Player[] players = new Player[m+1];
			fin = new boolean[m+1];
			
			for(int no = 1; no <= m; ++no) {
				String str = br.readLine();
				int w = 0, i = 0, n = 0;
				
				for(int j = 0; j < 3; ++j) {
					if(str.charAt(j) == 'w') w++;
					else if(str.charAt(j) == 'i') i++;
					else n++;
				}
				
				if(w == i && i == n) continue;
				
				players[no] = new Player(no, w, i, n);
				
				for(int j = 0; j < 3; ++j) {
					if(players[no].s[j] >= 2) {
						giveT[j].add(no);
					}
				}
				
				for(int j = 0; j < 3; ++j) {
					if(players[no].s[j] == 0) {
						needT[j].add(no);
					}
				}
			}
			
			while(true) {
				boolean flag = true;
				
				for(int i = 0; i < 3; ++i) {
					while(!needT[i].isEmpty()) {
						int no = needT[i].poll();
						
						if(fin[no]) continue;
						
						Player p = players[no];
						int give = -1;
						
						for(int j = 0; j < 3; ++j) {
							if(p.s[j] >= 2) give = j;
						}
						
						if(!giveT[i].isEmpty()) {
							flag = false;
							int gno = giveT[i].poll();
							
							Player gp = players[gno];
							
							p.s[i]++;
							gp.s[i]--;
							p.s[give]--;
							gp.s[give]++;
							
							addToTable(p);
							addToTable(gp);
							
							tc.append(p.no).append(" ").append(convert(give)).append(" ").append(gp.no).append(" ").append(convert(i)).append("\n");
							cnt++;
						}
					}
				}
				
				if(flag) break;
			}
			
			sb.append(cnt).append("\n");
			if(cnt != 0) sb.append(tc);
		}
		
		System.out.println(sb);
	}
	
	static void addToTable(Player p) {
		if(p.s[0] == p.s[1] && p.s[1] == p.s[2]) {
			fin[p.no] = true;
			return;
		}
		
		for(int j = 0; j < 3; ++j) {
			if(p.s[j] >= 2) {
				giveT[j].add(p.no);
			}
		}
		
		for(int j = 0; j < 3; ++j) {
			if(p.s[j] == 0) {
				needT[j].add(p.no);
			}
		}
	}
	
	static char convert(int x) {
		if(x == 0) return 'w';
		else if(x == 1) return 'i';
		else return 'n';
	}
	
	static class Player {
		int no;
		int[] s;
		
		public Player(int no, int w, int i, int n) {
			this.no = no;
			s = new int[] {w, i, n};
		}
	}
}
