import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S5_NumberCard_10815 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Set<Integer> set = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			if(set.contains(Integer.parseInt(st.nextToken()))) {
				sb.append("1 ");
			}
			else {
				sb.append("0 ");
			}
		}
		
		System.out.println(sb);
	}
}
