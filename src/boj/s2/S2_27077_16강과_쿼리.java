package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_27077_16강과_쿼리 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[] goalArr = new int[4];
		int[] lossArr = new int[4];
		int[] pointArr = new int[4];
		int[] pointOriginal = new int[4];
		int[] scoreArr = new int[4];
		
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			int goal = Integer.parseInt(st.nextToken());
			int loss = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			int idx = i > 1 ? 5-i : i;
			
			goalArr[idx] = goal;
			lossArr[idx] = loss;
			pointOriginal[idx] = pointArr[idx] = point;
		}
		
		Team[] H = new Team[4];
		
		for(int i = 0; i < 4; i++) {
			H[i] = new Team(i, goalArr[i], lossArr[i], pointArr[i]);
		}
		
		Arrays.sort(H);
		sb.append(check(H) ? "cry\n" : "unhappy\n");
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			int cur = teamNo(br.readLine());
			goalArr[cur]++;
			lossArr[(cur+2)%4]++;
			scoreArr[cur]++;
			
			for(int i = 0; i < 4; i++) {
				int j = (i+2)%4;
				if(scoreArr[i] > scoreArr[j]) pointArr[i] = pointOriginal[i] + 3;
				else if(scoreArr[i] == scoreArr[j]) pointArr[i] = pointOriginal[i] + 1;
				else pointArr[i] = pointOriginal[i];
			}
			
			H = new Team[4];
			
			for(int i = 0; i < 4; i++) {
				H[i] = new Team(i, goalArr[i], lossArr[i], pointArr[i]);
			}
			
			Arrays.sort(H);
			sb.append(check(H) ? "cry\n" : "unhappy\n");
		}
		
		System.out.println(sb);
	}
	
	static int teamNo(String str) {
		if(str.equals("korea")) return 0;
		else if(str.equals("uruguay")) return 1;
		else if(str.equals("portugal")) return 2;
		else return 3;
	}
	
	static boolean check(Team[] H) {
		for(int i = 0; i < 2; i++) {
			if(H[i].no == 0) return true;
		}
		
		return false;
	}
	
	static class Team implements Comparable<Team> {
		int no, goal, loss, point;
		
		public Team(int no, int goal, int loss, int point) {
			this.no = no;
			this.goal = goal;
			this.loss = loss;
			this.point = point;
		}

		public int compareTo(Team o) {
			if(this.point != o.point) return o.point - this.point;
			if(this.goal-this.loss != o.goal-o.loss) return (o.goal-o.loss) - (this.goal-this.loss);
			if(this.goal != o.goal) return o.goal - this.goal;
			return o.no - this.no;
		}

		@Override
		public String toString() {
			return "Team [no=" + no + "]";
		}
		
		
	}
}
