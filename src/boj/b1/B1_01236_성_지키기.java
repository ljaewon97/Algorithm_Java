package boj.b1;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_01236_성_지키기 {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 int N = Integer.parseInt(st.nextToken());
		 int M = Integer.parseInt(st.nextToken());
		 
		 boolean[] row = new boolean[N];
		 boolean[] col = new boolean[M];
		 
		 for(int i = 0; i < N; i++) {
			 String line = br.readLine();
			 for(int j = 0; j < M; j++) {
				 if(line.charAt(j) == 'X') {
					 row[i] = true;
					 col[j] = true;
				 }
			 }
		 }
		 
		 int r = 0, c = 0;
		 
		 for(int i = 0; i < N; i++) {
			 if(!row[i]) r++;
		 }
		 
		 for(int i = 0; i < M; i++) {
			 if(!col[i]) c++;
		 }
		 
		 System.out.println(Math.max(r, c));
	} 
}
