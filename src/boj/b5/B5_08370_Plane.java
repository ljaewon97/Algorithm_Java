package boj.b5;

import java.util.*;
import java.io.*;

public class B5_08370_Plane {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		System.out.println(Integer.parseInt(st.nextToken())*Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken())*Integer.parseInt(st.nextToken()));
	}
}
