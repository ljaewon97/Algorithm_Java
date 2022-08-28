package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_20055_컨베이어_벨트_위의_로봇 {
	static int[][] belt;
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new int[2*N+1][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= 2*N; i++) {
			belt[i][0] = Integer.parseInt(st.nextToken());
		}
		
		int step = 1;
		
		while(true) {
			rotate();
			move();
			putRobot();
			if(check()) break;
			step++;
		}
		
		System.out.println(step);
	}
	
	static void rotate() {
		int[] temp = belt[2*N];
		
		for(int i = 2*N; i > 1; i--) {
			belt[i] = belt[i-1];
		}
		
		belt[1] = temp;
		
		belt[N][1] = 0;
	}
	
	static void move() {
		for(int i = N; i > 1; i--) {
			if(belt[i-1][1] == 1 && belt[i][0] >= 1 && belt[i][1] == 0) {
				belt[i-1][1] = 0;
				belt[i][1] = 1;
				belt[i][0]--;
				
				belt[N][1] = 0;
			}
		}
	}
	
	static void putRobot() {
		if(belt[1][0] != 0) {
			belt[1][1] = 1;
			belt[1][0]--;
		}
	}
	
	static boolean check() {
		int cnt = 0;
		
		for(int i = 1; i <= 2*N; i++) {
			if(belt[i][0] == 0) cnt++;
		}
		
		return cnt >= K ? true : false;
	}
}
