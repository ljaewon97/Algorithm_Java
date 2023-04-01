package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2_27522_카트라이더_드리프트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Record[] records = new Record[8];
		int red = 0, blue = 0;
		
		for(int i = 0; i < 8; ++i) {
			st = new StringTokenizer(br.readLine());
			String rec = st.nextToken();
			char team = st.nextToken().charAt(0);
			st = new StringTokenizer(rec, ":");
			records[i] = new Record(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), team);
		}
		
		Arrays.sort(records);
		
		int[] points = {10, 8, 6, 5, 4, 3, 2, 1, 0};
		
		for(int i = 0; i < 8; ++i) {
			if(records[i].team == 'R') red += points[i];
			else blue += points[i];
		}
		
		if(red == blue) System.out.println(records[0].team == 'R' ? "Red" : "Blue");
		else System.out.println(red < blue ? "Blue" : "Red");
	}
	
	static class Record implements Comparable<Record> {
		int milli;
		char team;
		
		public Record(int m, int s, int milli, char team) {
			this.milli = m * 60 * 1000 + s * 1000 + milli;
			this.team = team;
		}

		@Override
		public int compareTo(Record o) {
			return this.milli - o.milli;
		}
	}
}
