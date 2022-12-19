package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S4_09017_크로스_컨트리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			int[] input = new int[N];
			int[] count = new int[201];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
				count[input[i]]++;
			}
			
			Team[] teams = new Team[201];
			boolean[] valid = new boolean[201];
			
			for(int i = 0; i < 201; i++) {
				teams[i] = new Team(i);
				if(count[i] == 6) valid[i] = true;
			}
			
			int score = 1;
			
			for(int i = 0; i < N; i++) {
				if(valid[input[i]]) {
					teams[input[i]].recs[teams[input[i]].cnt++] = score++;
				}
			}
			
			PriorityQueue<Team> cands = new PriorityQueue<>();
			
			for(int i = 0; i < 201; i++) {
				if(valid[i]) {
					teams[i].add();
					cands.add(teams[i]);
				}
			}
			
			sb.append(cands.poll().no).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static class Team implements Comparable<Team> {
		int no, cnt, sum;
		int[] recs;
		
		public Team(int no) {
			this.no = no;
			recs = new int[6];
		}
		
		void add() {
			for(int i = 0; i < 4; i++) {
				this.sum += this.recs[i];
			}
		}
		
		public int compareTo(Team o) {
			if(this.sum != o.sum) return this.sum - o.sum;
			return this.recs[4] - o.recs[4];
		}
	}
}
