package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_17825_주사위_윷놀이 {
	static int[] nums = new int[10];
	static int[] scoreboard = new int[200];
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0; i < 10; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= 20; i++) {
			scoreboard[i] = 2 * i;
		}
		
		scoreboard[51] = 13; scoreboard[52] = 16; scoreboard[53] = 19;
		scoreboard[101] = 22; scoreboard[102] = 24;
		scoreboard[151] = 28; scoreboard[152] = 27; scoreboard[153] = 26;
		
		for(int i = 0; i < 4; i++) {
			scoreboard[54+i] = 25 + 5 * i;
			scoreboard[103+i] = 25 + 5 * i;
			scoreboard[154+i] = 25 + 5 * i;
		}

		recur(1, new int[] {nums[0], 0, 0, 0}, scoreboard[nums[0]]);
		
		System.out.println(ans);
	}
	
	static void recur(int nth, int[] horses, int score) {
		if(nth == 10) {
			ans = Math.max(ans, score);
			return;
		}
		
		int move = nums[nth];
		
		for(int i = 0; i < 4; i++) {
			int cur = horses[i];
			
			if(cur == 999) continue;
			if(cur == 5 || cur == 10 || cur == 15) cur *= 10;
			
			int next = cur + move;
			boolean flag = false;
			
			for(int j = 0; j < 4; j++) {
				if(horses[j] == next || checkSame(horses[j], next)) {
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				int[] copied = new int[4];
				for(int j = 0; j < 4; j++) {
					copied[j] = horses[j];
				}
				
				if(finished(next)) {
					copied[i] = 999;
					
					recur(nth+1, copied, score);
				}
				else {
					copied[i] = next;			
					
					recur(nth+1, copied, score+scoreboard[next]);
				}
			}
		}
	}
	
	static boolean finished(int i) {
		return i != 0 && scoreboard[i] == 0;
	}
	
	static boolean checkSame(int a, int b) {
		if(54 <= a && a <= 57) a -= 54;
		else if(103 <= a && a <= 106) a -= 103;
		else if(154 <= a && a <= 157) a -= 154;
		else if(a == 20) a = 3;
		else return false;
		
		if(54 <= b && b <= 57) b -= 54;
		else if(103 <= b && b <= 106) b -= 103;
		else if(154 <= b && b <= 157) b -= 154;
		else if(b == 20) b = 3;
		else return false;
		
		return a == b;
	}
}
