package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_27165_모든_곳을_안전하게 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int dice = Integer.parseInt(br.readLine());
		
		boolean flag = true;
		int cnt = 0;
		int i1 = -1, i2 = -1;
		
		for(int i = 0; i <= n; i++) {
			if(arr[i] == 1) {
				cnt++;
				if(cnt == 1) i1 = i;
				else if(cnt == 2) i2 = i;
				else {
					flag = false;
					break;
				}
			}
		}
		
		if(!flag) {
			System.out.println("NO");
			return;
		}
		
		if(cnt == 2) {
			if(Math.abs(i2-i1) == dice) {
				System.out.println("YES");
				System.out.println(i1 + " " + i2);
			}
			else {
				System.out.println("NO");
			}
		}
		else if(cnt == 1) {
			int front = i1-dice;
			int back = i1+dice;
			
			if(front >= 0 && arr[front] > 2) {
				System.out.println("YES");
				System.out.println(front + " " + i1);
			}
			else if(back <= n && arr[back] != 0) {
				System.out.println("YES");
				System.out.println(i1 + " " + back);
			}
			else {
				System.out.println("NO");
			}
		}
		else {
			for(int i = 0; i <= n-dice; i++) {
				if(arr[i] > 2 && arr[i+dice] != 0) {
					System.out.println("YES");
					System.out.println(i + " " + (i+dice));
					return;
				}
			}
			
			System.out.println("NO");
		}
	}
}
