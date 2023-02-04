package algo;

public class BinarySearch {
	
	public static void main(String[] args) {
		int[] arr = {3,4};
		
		System.out.println(lowerbound(arr, 1));
		System.out.println(upperbound(arr, 4));
	}

	static int lowerbound(int[] arr, int target) {
		int l = 0;
		int r = arr.length;
		
		while(l < r) {
			int m = (l + r) >> 1;
		
			if(arr[m] >= target) r = m;
			else l = m + 1;
		}
		
		return r;
	}
	
	static int upperbound(int[] arr, int target) {
		int l = 0;
		int r = arr.length;
		
		while(l < r) {
			int m = (l + r) >> 1;
		
			if(arr[m] > target) r = m;
			else l = m + 1;
		}
		
		return r;
	}
}
