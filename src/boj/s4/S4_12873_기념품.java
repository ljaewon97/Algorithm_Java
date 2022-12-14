package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class S4_12873_기념품 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			queue.add(i);
		}
		
		int stage = 1;
		
		while(queue.size() != 1) {
			int step = (int) (((long) stage * stage * stage - 1) % queue.size());
			
			for(int i = 0; i < step; i++) {
				queue.add(queue.poll());
			}
			
			queue.poll();
			stage++;
		}
		
		System.out.println(queue.poll());
	}
}
