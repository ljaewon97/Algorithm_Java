package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3_27495_만다라트_만들기 {
	static String[][] arr;
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		arr = new String[9][9];
		
		for(int i = 0; i < 9; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; ++j) {
				arr[i][j] = st.nextToken();
			}
		}
		
		Goal[] goals = new Goal[8];
		
		for(int i = 0; i < 8; ++i) {
			goals[i] = new Goal(4+3*dr[i], 4+3*dc[i]);
		}
		
		Arrays.sort(goals);
		
		for(int i = 0; i < 8; ++i) {
			Goal goal = goals[i];
			sb.append('#').append(i+1).append(". ").append(goal.str).append('\n');
			
			String[] details = getDetails(goal.r, goal.c);
			
			for(int j = 0; j < 8; ++j) {
				sb.append('#').append(i+1).append('-').append(j+1).append(". ").append(details[j]).append('\n');
			}
		}
		
		System.out.println(sb);
	}
	
	static String[] getDetails(int r, int c) {
		String[] ret = new String[8];
		
		for(int i = 0; i < 8; ++i) {
			ret[i] = arr[r+dr[i]][c+dc[i]];
		}
		
		Arrays.sort(ret);
		
		return ret;
	}
	
	static class Goal implements Comparable<Goal> {
		String str;
		int r, c;
		
		public Goal(int r, int c) {
			this.str = arr[r][c];
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Goal o) {
			return str.compareTo(o.str);
		}
	}
}
