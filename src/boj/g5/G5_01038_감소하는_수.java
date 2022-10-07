package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class G5_01038_감소하는_수 {
	static Set<Long> decNum;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		decNum = new TreeSet<>();
		
		for(int i = 0; i < 10; i++) {
			recur(i, 0);
		}
		
		long ans = -1;
		int cnt = 0;
		
		for(long num: decNum) {
			if(cnt == N) {
				ans = num;
				break;
			}
			
			cnt++;
		}

		System.out.println(ans);
	}
	
	static void recur(long num, int len) {
		decNum.add(num);
		
		for(long i = num/ten(len)+1; i < 10; i++) {
			recur(i*ten(len+1)+num, len+1);
		}
	}
	
	static long ten(int n) {
		if(n == 0) return 1;
		else if(n == 1) return 10;
		else if(n == 2) return 100;
		else if(n == 3) return 1000;
		else if(n == 4) return 10000;
		else if(n == 5) return 100000;
		else if(n == 6) return 1000000;
		else if(n == 7) return 10000000;
		else if(n == 8) return 100000000;
		else if(n == 9) return 1000000000;
		else if(n == 10) return 1000000000;
		else return 10000000000L;
	}
}
