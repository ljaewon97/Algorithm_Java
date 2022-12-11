package boj.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D4_26220_겨울_숲의_썰매_트랙 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder track = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		if(N == 3) {
			System.out.println("4\n" + 
					"1 1\n" + 
					"RDLU");
			return;
		}
		
		if(N == 5) {
			System.out.println("16\n" + 
					"1 2\n" + 
					"RDRDRDLDLULULURU");
			return;
		}
		
		track.append("R");
		
		for(int i = 0; i < (N-3)/2; i++) {
			track.append("URDR");
		}
		
		track.append("DR");
		
		if(N % 4 == 1) {
			for(int i = 0; i < N/4; i++) {
				for(int j = 0; j < (N-3)/2-1; j++) {
					track.append("DLDR");
				}
				
				track.append("DLDL");
				
				for(int j = 0; j < (N-3)/2-1; j++) {
					track.append("ULUR");
				}
				
				track.append("ULUL");
			}
			
			track.append("U");
			
			sb.append(track.length()).append("\n");
			sb.append("2 1\n");
			sb.append(track);
		}
		else {
			for(int i = 0; i < N/4; i++) {
				for(int j = 0; j < (N-3)/2-1; j++) {
					track.append("DLDR");
				}
				
				track.append("DLDL");
				
				if(i == N/4-1) continue;
				
				for(int j = 0; j < (N-3)/2-1; j++) {
					track.append("ULUR");
				}
				
				track.append("ULUL");
			}
			
			track.append("ULURU");
			
			for(int i = 0; i < N/4; i++) {
				track.append("LULDLULU");
				
				if(i == N/4-1) continue;
				
				track.append("RURDRURU");
			}
			
			sb.append(track.length()).append("\n");
			sb.append("2 1\n");
			sb.append(track);
		}
		
		System.out.println(sb);
	}
}
