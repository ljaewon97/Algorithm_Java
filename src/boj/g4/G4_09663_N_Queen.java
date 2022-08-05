package boj.g4;

import java.util.Scanner;

public class G4_09663_N_Queen {
	static int N, ans = 0;
	static boolean[][] visited;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
	
		visited = new boolean[N][N];
		
		
		
		
		
		System.out.println(ans);
		
		sc.close();
	}
}
