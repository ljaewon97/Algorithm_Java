package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_02470_두_용액 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int l = 0, r = N-1;
		int la = arr[l], ra = arr[r];
		int cur = la + ra;
		int min = Math.abs(cur);
		
		while(l+1 < r) {
			if(cur > 0) {
				cur = arr[l] + arr[--r];
				if(Math.abs(cur) < min) {
					min = Math.abs(cur);
					la = arr[l];
					ra = arr[r];
				}
			}
			else if(cur == 0) {
				break;
			}
			else {
				cur = arr[++l] + arr[r];
				if(Math.abs(cur) < min) {
					min = Math.abs(cur);
					la = arr[l];
					ra = arr[r];
				}
			}
		}
		
		System.out.println(la + " " + ra);
	}
}
