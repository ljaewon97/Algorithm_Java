package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_03079_입국심사 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		long l = 1;
		long r = 1000000000000000000L;
		
		while(l < r) {
			boolean flag = true;
			long mid = (l + r) / 2;
			long sum = 0;
			
			for(int i = 0; i < N; i++) {
				sum += mid / arr[i];
				if(sum >= M) {
					flag = false;
					r = mid;
					break;
				}
			}
			
			if(flag) l = mid + 1;
		}
		
		System.out.println(r);
	}
}
