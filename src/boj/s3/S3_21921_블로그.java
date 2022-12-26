package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_21921_블로그 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int max = 0, cnt = 1;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < X; i++) {
			max += arr[i];
		}
		
		int cur = max;
		
		for(int i = 0; i < N-X; i++) {
			cur += arr[i+X] - arr[i];
			if(cur > max) {
				max = cur;
				cnt = 1;
			}
			else if(cur == max) cnt++;
		}
		
		if(max == 0) System.out.println("SAD");
		else System.out.println(max + "\n" + cnt);
	}
}
