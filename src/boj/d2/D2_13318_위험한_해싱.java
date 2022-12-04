package boj.d2;

public class D2_13318_위험한_해싱 {
	static int maxDeg;
	
	public static void main(String[] args) throws Exception {
		
		// result of findFunc()
		X[] res = {new X(1, 0), new X(1, 6189), new X(-1, 11437)};					// 29
		res = calc(res, new X[] {new X(1, 0), new X(-1, 5450), new X(1, 25999)});	// 31
		res = calc(res, new X[] {new X(1, 0), new X(1, 12066), new X(-1, 16300)});	// 37
		res = calc(res, new X[] {new X(1, 0), new X(-1, 4471), new X(1, 15566)});	// 41
		res = calc(res, new X[] {new X(1, 0), new X(-1, 11801), new X(1, 22207)});	// 43
		res = calc(res, new X[] {new X(1, 0), new X(1, 12874), new X(-1, 50582)});	// 47
		res = calc(res, new X[] {new X(1, 0), new X(1, 8080), new X(-1, 21401)});	// 53
		res = calc(res, new X[] {new X(1, 0), new X(-1, 25655), new X(1, 35436)});	// 59
		res = calc(res, new X[] {new X(1, 0), new X(-1, 25056), new X(1, 36880)});	// 61
		res = calc(res, new X[] {new X(1, 0), new X(1, 6484), new X(-1, 30660)});	// 67
		
		int[] fx = new int[maxDeg+1];
		
		for(int i = 0; i < res.length; i++) {
			fx[res[i].deg] += res[i].coeff;
		}
		
		char[] A = new char[maxDeg+1];
		char[] B = new char[maxDeg+1];
		
		for(int i = 0; i <= maxDeg; i++) {
			A[i] = 'f';
			B[i] = (char) (102 + fx[i]);
		}
		
		System.out.println(new String(A));
		System.out.println(new String(B));
	}
	
	static X[] calc(X[] a, X[] b) {
		int N = a.length * b.length;
		X[] ret = new X[N];
		int idx = 0;
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b.length; j++) {
				maxDeg = Math.max(maxDeg, a[i].deg+b[j].deg);
				ret[idx++] = new X(a[i].coeff*b[j].coeff, a[i].deg+b[j].deg);
			}
		}
		
		return ret;
	}
	
	static void findFunc() {
		final long MOD = 1000000007;
		final int limit = 60000;
		final long N = 67;
		
		long[] pow = new long[limit+1];
		pow[0] = 1;
		
		for(int i = 1; i <= limit; i++) {
			pow[i] = pow[i-1] * N % MOD;
		}
		
		int mini = limit+1, minj = limit+1;
		
		for(int i = 1; i <= limit; i++) {
			for(int j = i; j <= limit; j++) {
				if(Math.abs(pow[i] - pow[j]) == 1) {
					if(j < minj) {
						minj = j;
						mini = i;
					}
				}
			}
		}
		
		System.out.printf("N: %d\n", N);
		System.out.printf("i: %d, j: %d\n", mini, minj);
		System.out.printf("pow[i] = %d\n", pow[mini]);
		System.out.printf("pow[j] = %d\n", pow[minj]);
	}
	
	static class X {
		int coeff, deg;
		
		public X(int coeff, int deg) {
			this.coeff = coeff;
			this.deg = deg;
		}
	}
}
