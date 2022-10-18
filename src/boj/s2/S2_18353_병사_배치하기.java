package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_18353_병사_배치하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] C = new int[N];
		int size = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = N-1; i >= 0; i--) {
			int l = 0;
			int r = size-1;
			
			while(l <= r) {
				int mid = (l + r) >> 1;
				
				if(C[mid] < arr[i]) l = mid+1;
				else if(C[mid] > arr[i]) r = mid-1;
				else {
					l = -1;
					break;
				}
			}
			
			if(l == -1) continue;
			
			C[l] = arr[i];
			if(l == size) size++;
		}
		
		System.out.println(N-size);
	}
}
