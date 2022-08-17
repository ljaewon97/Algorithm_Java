package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_15658_연산자_끼워넣기_2 {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int N, operCnt;
	static int[] nums;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] opers = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			opers[i] = Integer.parseInt(st.nextToken());
			operCnt += opers[i];
		}
		
		recur(1, nums[0], opers[0], opers[1], opers[2], opers[3]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void recur(int idx, int res, int plus, int minus, int multi, int divide) {
		if((plus + minus + multi + divide) == operCnt - N + 1) {
			if(res > max) {
				max = res;
			}
			if(res < min) {
				min = res;
			}
			return;
		}
		
		int cur = nums[idx];
		
		if(plus != 0) {
			recur(idx+1, res+cur, plus-1, minus, multi, divide);
		}
		if(minus != 0) {
			recur(idx+1, res-cur, plus, minus-1, multi, divide);
		}
		if(multi != 0) {
			recur(idx+1, res*cur, plus, minus, multi-1, divide);
		}
		if(divide != 0) {
			recur(idx+1, res/cur, plus, minus, multi, divide-1);
		}
	}
}
