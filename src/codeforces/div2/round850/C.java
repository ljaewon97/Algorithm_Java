package codeforces.div2.round850;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class C {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());

			PriorityQueue<Integer> pq = new PriorityQueue<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; ++i) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
			
			long cnt = 0;
			int health = 1;
			
			while(!pq.isEmpty()) {
				int cur = pq.poll();
				
				if(cur >= health) {
					cnt += cur - health;
					health++;
				}
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
}
