package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_20529_가장_가까운_세_사람의_심리적_거리 {
	static int[][] distArr = new int[16][16];
	static int[] mbti, count, choosed;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		String[] MBTI = {"ISTJ", "ISFJ", "INFJ", "INTJ", "ISTP", "ISFP", "INFP", "INTP", "ESTP", "ESFP", "ENFP", "ENTP", "ESTJ", "ESFJ", "ENFJ", "ENTJ"};
		
		for(int i = 0; i < 16; i++) {
			for(int j = i+1; j < 16; j++) {
				int dist = 0;
				
				for(int k = 0; k < 4; k++) {
					if(MBTI[i].charAt(k) != MBTI[j].charAt(k)) dist++;
				}
				
				int a = convert(MBTI[i]);
				int b = convert(MBTI[j]);
				
				distArr[a][b] = distArr[b][a] = dist;
			}
		}
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			
			if(N >= 48) {
				br.readLine();
				sb.append("0\n");
				continue;
			}
			
			mbti = new int[N];
			count = new int[16];
			choosed = new int[3];
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				String cur = st.nextToken();
				int x = convert(cur);
				mbti[i] = x;
				count[x]++;
			}
			
			boolean flag = false;
			
			for(int i = 0; i < 16; i++) {
				if(count[i] >= 3) {
					flag = true;
					break;
				}
			}
			
			if(flag) {
				sb.append("0\n");
				continue;
			}
			
			comb(0, 0);
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void comb(int nth, int start) {
		if(nth == 3) {
			int dist = 0;
			
			for(int i = 0; i < 3; i++) {
				int j = (i+1)%3;
				
				dist += distArr[choosed[i]][choosed[j]];
			}
			
			ans = Math.min(ans, dist);
			return;
		}
		
		for(int i = start; i < N; i++) {
			choosed[nth] = mbti[i];
			comb(nth+1, i+1);
		}
	}
	
	static int convert(String mbti) {
		int ret = 0;
		
		if(mbti.charAt(0) == 'I') ret += 1;
		if(mbti.charAt(1) == 'N') ret += 2;
		if(mbti.charAt(2) == 'T') ret += 4;
		if(mbti.charAt(3) == 'P') ret += 8;
		
		return ret;
	}
}
