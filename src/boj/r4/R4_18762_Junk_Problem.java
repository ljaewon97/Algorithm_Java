package boj.r4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class R4_18762_Junk_Problem {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int S = (int) Math.floor(Math.sqrt(0.5*n)); // size of S
		int q = 0;
		
		if(n == 1) {
			System.out.println(0);
			return;
		}
		else if(n < 8) {
			System.out.println(1);
			System.out.println(1);
			return;
		}
		else if(n < 18) {
			System.out.println(2);
			System.out.println(1 + " " + 2);
			return;
		}
		
		while(true) {
			if(1<<q > S) break;
			++q;
		}
		
		if((1<<q) == 2*S) --q;
		
		// primitive polynomials
		Poly[] pp = new Poly[13];
		int[] ppn = {0,0,7,11,19,37,67,131,285,529,1033,2053,4179};
		
		for(int i = 0; i < 13; ++i) {
			pp[i] = intToPoly(ppn[i]);
		}
		
		Poly gfMax = intToPoly(1<<q);
		
		for(int i = 1; i <= S; ++i) {
			Poly pi = intToPoly(i);
			
			Poly a = pi.multiply(gfMax);
			Poly b = pi.multiply(pi).multiply(pi).mod(pp[q]);
			
			sb.append(a.add(b).intValue()).append(' ');
		}
		
		System.out.println(S);
		System.out.println(sb);
	}
	
	static Poly intToPoly(int x) {
		int[] coeff = new int[100];
		int len = 0;
		
		if(x == 0) return new Poly(new int[] {0});
		
		while(x > 0) {
			coeff[len++] = x%2;
			x /= 2;
		}
		
		return new Poly(Arrays.copyOf(coeff, len));
	}
	
	static class Poly {
		int[] coeff;
		
		public Poly(int[] coeff) {
			this.coeff = coeff;
		}
		
		Poly add(Poly o) {
			int[] ret = new int[Math.max(this.coeff.length, o.coeff.length)];
			
			for(int i = 0; i < this.coeff.length; ++i) {
				ret[i] = this.coeff[i];
			}
			
			for(int i = 0; i < o.coeff.length; ++i) {
				ret[i] ^= o.coeff[i];
			}
			
			return new Poly(ret);
		}
		
		Poly multiply(Poly o) {
			int[] ret = new int[this.coeff.length+o.coeff.length-1];
			
			for(int i = 0; i < this.coeff.length; ++i) {
				for(int j = 0; j < o.coeff.length; ++j) {
					ret[i+j] ^= this.coeff[i] & o.coeff[j];
				}
			}
			
			return new Poly(ret);
		}
		
		Poly mod(Poly o) {
			Poly ret = new Poly(this.coeff);
			
			while(ret.degree() >= o.degree()) {
				int shift = ret.degree() - o.degree();
				int[] termCoeff = new int[shift+1];
				termCoeff[shift] = 1;
				Poly term = new Poly(termCoeff);
				
				Poly quotientTerm = ret.coeff[ret.degree()] == 1 ? term : new Poly(new int[] {0});
				Poly modulusTerm = o.multiply(quotientTerm);
				
				ret = ret.add(modulusTerm);
			}
			
			return ret;
		}
		
		int intValue() {
			int ret = 0;
			int pow = 1;
			
			for(int i = 0; i < this.coeff.length; ++i) {
				if(this.coeff[i] == 1) ret += pow;
				pow <<= 1;
			}
			
			return ret;
		}
		
		int degree() {
			for(int i = coeff.length-1; i >= 0; --i) {
				if(coeff[i] == 1) return i;
			}
			
			return -1;
		}

		@Override
		public String toString() {
			return Arrays.toString(coeff);
		}
	}
}
