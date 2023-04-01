package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class S3_27922_현대모비스_입사_프로젝트 {
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Lec[] lecs = new Lec[N];
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			lecs[i] = new Lec(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		}
		
		long max1 = 0, max2 = 0, max3 = 0;
		
		Arrays.sort(lecs, new Comparator<Lec>() {
			@Override
			public int compare(Lec o1, Lec o2) {
				return Long.compare(o2.ab, o1.ab);
			}
		});
		
		for(int i = 0; i < K; ++i) {
			max1 += lecs[i].ab;
		}
		
		Arrays.sort(lecs, new Comparator<Lec>() {
			@Override
			public int compare(Lec o1, Lec o2) {
				return Long.compare(o2.bc, o1.bc);
			}
		});
		
		for(int i = 0; i < K; ++i) {
			max2 += lecs[i].bc;
		}
		
		Arrays.sort(lecs, new Comparator<Lec>() {
			@Override
			public int compare(Lec o1, Lec o2) {
				return Long.compare(o2.ca, o1.ca);
			}
		});
		
		for(int i = 0; i < K; ++i) {
			max3 += lecs[i].ca;
		}
		
		System.out.println(Math.max(max1, Math.max(max2, max3)));
	}
	
	static class Lec {
		long a, b, c;
		long ab, bc, ca;
		
		public Lec(long a, long b, long c) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.ab = a+b;
			this.bc = b+c;
			this.ca = c+a;
		}
	}
}
