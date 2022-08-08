package boj.g4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_12970_AB {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int b = -1;
		
		for(int i = 1; i <= (int) Math.sqrt(K); i++) {
			int a = K / i;
			int mod = K % i;
			if(a + i + (mod == 0 ? 0 : 1) <= N) {
				b = i;
				break;
			}
		}
		
		if(K == 0) {
			for(int i = 0; i < N - 1; i++) {
				sb.append("B");
			}
			sb.append("A");
			System.out.println(sb);
		}
		else if(b == -1) {
			System.out.println(-1);
		}
		else {
			
			int a = K / b;
			int mod = K % b;
			for(int i = 0; i < mod; i++) {
				sb.append("B");
			}
			if(mod != 0) {
				sb.append("A");
			}
			for(int i = 0; i < b - mod; i++) {
				sb.append("B");
			}
			for(int i = 0; i < a; i++) {
				sb.append("A");
			}
			for(int i = 0; i < N - a - b - (mod == 0 ? 0 : 1); i++) {
				sb.append("B");
			}
			System.out.println(sb.reverse());
		}
	}
}
