package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_19539_사과나무 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int sum = 0, spray2 = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			sum += h;
			spray2 += h / 2;
		}
		
		if(sum % 3 != 0) {
			System.out.println("NO");
			return;
		}
		
		if(spray2 >= sum/3) System.out.println("YES");
		else System.out.println("NO");
	}
}
