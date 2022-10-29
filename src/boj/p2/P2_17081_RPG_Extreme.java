package boj.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class P2_17081_RPG_Extreme {
	static Hero hero;
	static Map<Integer, Item> items;
	static Map<Integer, Monster> monsters;
	static char[][] map;
	static int N, M, hr, hc, turn, ending;
	static String killedBy;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		items = new HashMap<>();
		monsters = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N+1][M+1];
		
		int icnt = 0, mcnt = 0;
		
		for(int r = 1; r <= N; r++) {
			String line = br.readLine();
			for(int c = 1; c <= M; c++) {
				map[r][c] = line.charAt(c-1);
				if(map[r][c] == '@') {
					hero = new Hero(r, c);
					hr = r;
					hc = c;
					map[r][c] = '.';
				}
				else if(map[r][c] == 'B') icnt++;
				else if(map[r][c] == '&' || map[r][c] == 'M') mcnt++;
			}
		}
		
		char[] dirs = br.readLine().toCharArray();
		
		while(mcnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			String S = st.nextToken();
			int W = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			boolean isBoss = (map[R][C] == 'M');
			
			monsters.put(101*R+C, new Monster(S, W, A, H, E, isBoss));
		}
		
		while(icnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			char T = st.nextToken().charAt(0);
			String S = st.nextToken();
			
			items.put(101*R+C, new Item(T, S));
		}
		
		for(char dir: dirs) {
			move(dir);
			turn++;
			
			if(ending > 0) break;
		}
		
		if(ending != 2) map[hr][hc] = '@';
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= M; c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		
		sb.append(String.format("Passed Turns : %d\n", turn));
		sb.append(String.format("LV : %d\n", hero.lv));
		sb.append(String.format("HP : %d/%d\n", hero.hp, hero.maxhp));
		sb.append(String.format("ATT : %d+%d\n", hero.att, hero.watt));
		sb.append(String.format("DEF : %d+%d\n", hero.def, hero.ddef));
		sb.append(String.format("EXP : %d/%d\n", hero.exp, hero.maxexp));
		
		if(ending == 1) sb.append("YOU WIN!\n");
		else if(ending == 2) {
			sb.append("YOU HAVE BEEN KILLED BY ");
			sb.append(killedBy);
			sb.append("..\n");
		}
		else sb.append("Press any key to continue.\n");
		
		System.out.println(sb);
	}
	
	static void move(char dir) {
		int d = 0;
		
		if(dir == 'D') d = 1;
		else if(dir == 'L') d = 2;
		else if(dir == 'R') d = 3;
		
		int nr = hr + dr[d];
		int nc = hc + dc[d];
		
		if(!isIn(nr, nc) || map[nr][nc] == '#') arrive(hr, hc);
		else arrive(nr, nc);
	}
	
	static void arrive(int r, int c) {
		hr = r;
		hc = c;
		
		if(map[hr][hc] == 'B') {
			map[hr][hc] = '.';
			Item item = items.get(101*hr+hc);
			
			if(item.type == 'O') {
				if(hero.o.size() < 4 && !hero.o.contains(item.o)) {
					hero.o.add(item.o);
				}
			}
			else if(item.type == 'W') {
				hero.watt = item.abil;
			}
			else {
				hero.ddef = item.abil;
			}
		}
		else if(map[hr][hc] == '^') {
			if(hero.o.contains("DX")) hero.hp--;
			else hero.hp -= 5;
			
			if(hero.hp <= 0) {
				if(hero.o.contains("RE")) {
					hero.revive();
				}
				else {
					ending = 2;
					killedBy = "SPIKE TRAP";
				}
			}
		}
		else if(map[hr][hc] == '&' || map[hr][hc] == 'M') {
			Monster monster = monsters.get(101*hr+hc);
			
			if(monster.isBoss && hero.o.contains("HU")) hero.hp = hero.maxhp;
			int hhp = hero.hp;
			int mhp = monster.hp;
			int fatt = hero.o.contains("CO") ? (hero.o.contains("DX") ? 3 : 2) : 1;
			int hatt = hero.att + hero.watt;
			int hdef = hero.def + hero.ddef;
			
			int t = 0;
			while(hhp > 0 && mhp > 0) {
				if(t % 2 == 0) {
					if(t == 0) mhp -= Math.max(1, fatt*hatt-monster.def);
					else mhp -= Math.max(1, hatt-monster.def);
				}
				else {
					if(t == 1 && monster.isBoss && hero.o.contains("HU")) hhp -= 0;
					else hhp -= Math.max(1, monster.att-hdef);
				}
				t++;
			}
			
			if(mhp <= 0) {
				map[hr][hc] = '.';
				hero.hp = hhp;
				if(hero.o.contains("HR")) hero.hp = Math.min(hero.hp+3, hero.maxhp);
				int exp = monster.exp;
				if(hero.o.contains("EX")) exp = (int) (1.2*exp);
				hero.exp += exp;
				if(hero.exp >= hero.maxexp) hero.levelUp();
				if(monster.isBoss) ending = 1;
			}
			else {
				if(hero.o.contains("RE")) {
					hero.revive();
				}
				else {
					hero.hp = 0;
					ending = 2;
					killedBy = monster.name;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 1 <= r && r <= N && 1 <= c && c <= M;
	}
	
	static class Hero {
		int sr, sc, lv, hp, maxhp, att, watt, def, ddef, exp, maxexp;
		Set<String> o;
		
		public Hero(int sr, int sc) {
			this.sr = sr;
			this.sc = sc;
			o = new HashSet<>();
			lv = 1;
			maxexp = 5;
			maxhp = hp = 20;
			att = def = 2;
		}
		
		void levelUp() {
			lv++;
			exp = 0;
			maxexp += 5;
			maxhp += 5;
			hp = maxhp;
			att += 2;
			def += 2;
		}
		
		void revive() {
			o.remove("RE");
			hr = sr;
			hc = sc;
			hp = maxhp;
		}
	}
	
	static class Monster {
		String name;
		int att, def, hp, maxhp, exp;
		boolean isBoss;
		
		public Monster(String name, int att, int def, int maxhp, int exp, boolean isBoss) {
			this.name = name;
			this.att = att;
			this.def = def;
			this.maxhp = this.hp = maxhp;
			this.exp = exp;
			this.isBoss = isBoss;
		}
	}
	
	static class Item {
		char type;
		String o;
		int abil;
		
		public Item(char type, String x) {
			this.type = type;
			if(type == 'W' || type == 'A') this.abil = Integer.parseInt(x);
			else this.o = x;
		}
	}
}
