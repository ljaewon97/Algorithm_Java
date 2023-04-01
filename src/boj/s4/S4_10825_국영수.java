package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S4_10825_국영수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		Score[] scores = new Score[N];
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			scores[i] = new Score(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(scores);
		
		for(int i = 0; i < N; ++i) {
			sb.append(scores[i].name).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static class Score implements Comparable<Score> {
		int a, b, c;
		String name;
		
		public Score(String name, int a, int b, int c) {
			this.name = name;
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public int compareTo(Score o) {
			if(this.a != o.a) return o.a - this.a;
			else if(this.b != o.b) return this.b - o.b;
			else if(this.c != o.c) return o.c - this.c;
			return this.name.compareTo(o.name);
		}
	}
}
