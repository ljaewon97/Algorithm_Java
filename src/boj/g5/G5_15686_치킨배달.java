package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class G5_15686_치킨배달 {
	static int[][] map;
	static int[][] homes;
	static int[][] stores;
	static List<Integer[]> remain = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		homes = new int[100][2];
		stores = new int[13][2];
		int homeCnt = 0, storeCnt = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					homes[homeCnt][0] = i;
					homes[homeCnt][1] = j;
					homeCnt++;
				}
				else if(map[i][j] == 2) {
					stores[storeCnt][0] = i;
					stores[storeCnt][1] = j;
					storeCnt++;
				}
			}
		}
		
		homes = Arrays.copyOf(homes, homeCnt);
		stores = Arrays.copyOf(stores, storeCnt);
		int[] storeNum = new int[storeCnt];
		for(int i = 0; i < storeCnt; i++) {
			storeNum[i] = i;
		}
		boolean[] visited = new boolean[storeCnt];
		combination(storeNum, visited, 0, storeCnt, M, M);
		
		int ans = Integer.MAX_VALUE;
		for(Integer[] comb: remain) {
			int temp = calcDistance(homes, comb);
			if(temp < ans) {
				ans = temp;
			}
		}
		
		System.out.println(ans);
	}
	
	static void combination(int[] arr, boolean[] visited, int start, int n, int r, int M) {
		if(r == 0) {
			addResult(arr, visited, n, M);
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i+1, n, r-1, M);
			visited[i] = false;
		}
	}
	
	static void addResult(int[] arr, boolean[] visited, int n, int r) {
		Integer[] res = new Integer[r];
		int idx = 0;
		
		for(int i = 0; i < n; i++) {
			if(visited[i]) {
				res[idx++] = arr[i];
			}
		}
		
		remain.add(res);
	}
	
	static int calcDistance(int[][] homes, Integer[] comb) {
		int dist = 0;
		
		for(int i = 0; i < homes.length; i++) {
			int min = Integer.MAX_VALUE;
			int hr = homes[i][0];
			int hc = homes[i][1];
			for(int num: comb) {
				int temp = Math.abs(hr - stores[num][0]) + Math.abs(hc - stores[num][1]);
				if(temp < min) {
					min = temp;
				}
			}
			dist += min;
		}
		return dist;
	}
}
