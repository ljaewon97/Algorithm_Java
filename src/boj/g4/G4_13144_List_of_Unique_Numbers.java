package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_13144_List_of_Unique_Numbers {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		int[] count = new int[100001];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long ans = 0;
		int i = 0;
		
		for(int j = 0; j < N; j++) {
			count[arr[j]]++;
			if(count[arr[j]] == 2) {
				while(i < j && count[arr[j]] == 2) {
					count[arr[i]]--;
					i++;
				}
			}
			
			ans += j-i+1;
		}
		
		System.out.println(ans);
	}
}
