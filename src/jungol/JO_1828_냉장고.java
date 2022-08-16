package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JO_1828_냉장고 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] chems = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			chems[i][0] = Integer.parseInt(st.nextToken());
			chems[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(chems, (o1, o2) -> o1[0] - o2[0]);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(chems[0][1]);
		int ans = 1;
		
		for(int i = 1; i < N; i++) {
			if(chems[i][0] > pq.peek()) {
				ans++;
				pq.clear();
				pq.add(chems[i][1]);
			}
			else {
				pq.add(chems[i][1]);
			}
		}
		
		System.out.println(ans);
	}
}
