package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_01322_X와_K {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		String Xstr = Integer.toBinaryString(X);
		Queue<Integer> queue = new LinkedList<>();
		
		while(K > 0) {
			queue.add(K&1);
			K>>=1;
		}
		
		long Y = 0;
		
		for(int i = Xstr.length()-1; i >= 0; --i) {
			if(Xstr.charAt(i) == '0') {
				if(!queue.isEmpty()) {
					Y += queue.poll() * (1<<(Xstr.length()-1-i));
				}
			}
		}
		
		long bit = 1<<(Xstr.length()-1);
		bit <<= 1;
		
		while(!queue.isEmpty()) {
			Y += bit * queue.poll();
			bit <<= 1;
		}
		
		System.out.println(Y);
	}
}
