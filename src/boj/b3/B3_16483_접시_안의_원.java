package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_16483_접시_안의_원 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		double T = Double.parseDouble(br.readLine());
		System.out.println(Math.round(Math.pow(T / 2, 2)));
	}
}
