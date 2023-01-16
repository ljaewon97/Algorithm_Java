package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S3_02659_십자카드_문제 {
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static List<Integer> list = new ArrayList<>();
	static int[] num = {1,2,3,4,5,6,7,8,9};
	static int[] result = new int[4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		solve(0);
		
		int[] nums = new int[4];
		
		for(int i = 0; i < 4; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < 4; i++) {
			int x = 0;
			
			for(int j = 0; j < 4; j++) {
				x = x*10 + nums[(i+j)%4];
			}
			
			pq.add(x);
		}
		
		int t = pq.poll();
		
		for(int i = 1; i <= list.size(); i++) {
			if(list.get(i) == t) {
				System.out.println(i);
				return;
			}
		}
	}
	
	static void solve(int nth) {
		if(nth == 4) {
			for(int i = 0; i < 4; i++) {
				int x = 0;
				
				for(int j = 0; j < 4; j++) {
					x = x*10 + result[(i+j)%4];
				}
				
				pq.add(x);
			}
			
			list.add(pq.poll());
			pq.clear();
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			result[nth] = i;
			solve(nth+1);
		}
	}
}
