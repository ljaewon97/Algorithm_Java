package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_01907_탄소_화합물 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "+=");
		
		int[][] arr = new int[3][3];
		
		for(int i = 0; i < 3; ++i) {
			String mol = st.nextToken();
			for(int j = 0; j < mol.length(); ++j) {
				if(mol.charAt(j) >= 65) {
					if(j == mol.length()-1 || mol.charAt(j+1) >= 65) {
						if(mol.charAt(j) == 'C') arr[i][0]++;
						else if(mol.charAt(j) == 'H') arr[i][1]++;
						else if(mol.charAt(j) == 'O') arr[i][2]++;
					}
					else {
						int cnt = mol.charAt(j+1)-'0';
						if(j+2 < mol.length() && mol.charAt(j+2) == '0') cnt = 10;
						
						if(mol.charAt(j) == 'C') arr[i][0] += cnt;
						else if(mol.charAt(j) == 'H') arr[i][1] += cnt;
						else if(mol.charAt(j) == 'O') arr[i][2] += cnt;
					}
				}
			}
		}
		
		for(int i = 1; i <= 10; ++i) {
			for(int j = 1; j <= 10; ++j) {
				for(int k = 1; k <= 10; ++k) {
					if(arr[0][0]*i + arr[1][0]*j != arr[2][0]*k) continue;
					if(arr[0][1]*i + arr[1][1]*j != arr[2][1]*k) continue;
					if(arr[0][2]*i + arr[1][2]*j != arr[2][2]*k) continue;
					
					System.out.println(i + " " + j + " " + k);
					return;
				}
			}
		}
	}
}
