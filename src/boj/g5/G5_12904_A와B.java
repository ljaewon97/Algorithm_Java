package boj.g5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_12904_A와B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] S = br.readLine().toCharArray();
		char[] T = br.readLine().toCharArray();
		int l = 0, r = T.length - 1, dir = 0;
		
		while(S.length != (Math.abs(r - l) + 1)) {
			if(T[r] == 'A') {
				r += dir == 0 ? -1 : 1;
			}
			else if(T[r] == 'B') {
				r += dir == 0 ? -1 : 1;
				dir = (dir + 1) % 2;
				int temp = l;
				l = r;
				r = temp;
			}
		}
		
		int ans = 1;
		int d = dir == 0 ? 1 : -1;
		for(int i = l, j = 0; i != r + d; i += d, j++) {
			if(S[j] != T[i]) {
				ans = 0;
				break;
			}
		}
		
		System.out.println(ans);
	}
}
