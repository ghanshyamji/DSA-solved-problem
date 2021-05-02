/*############################################################################
						 K Largest Elements
	
	You are given with an integer k and an array of integers that contain 
	numbers in random order. Write a program to find k largest numbers from 
	given array. You need to save them in an array and return it.
	Time complexity should be O(nlogk) and space complexity should be not 
	more than O(k).

				completed -- true
#############################################################################*/	
import java.util.ArrayList;
public class KLargestElements{

	public static void downHipifyMax(int[] arr, int end, int element){
		//here end is the last element  when index from 1 of the arr and element is wherre i should start.
		// this will hipify element upto reach to down.
		// System.out.print(Arrays.toString(arr)+" end "+ end + " "+element);
		if(arr.length<2||end-element<1){
		// System.out.println(Arrays.toString(arr));

			return;
		}
		int child =0, child2 = 0, swap = 0;
		while(element<end){
				// System.out.print("/"+element+"/");
				child = element*2+1;
				child2 = element*2+2;
				if(	child>=end || (child2>=end && arr[child]<arr[element]) || 
					(arr[child]<arr[element] && arr[child2]<arr[element])) break;
				if(child2>=end){
					swap = arr[child];
					arr[child] = arr[element];
					arr[element] = swap;
					break;
				}
				child = arr[child2]<arr[child]?child:child2;

				swap = arr[child];
				arr[child] = arr[element];
				arr[element] = swap;
				element = child;
			}
		// System.out.println(Arrays.toString(arr));

	}
	public static ArrayList<Integer> kLargest(int arr[], int k) {
		if(arr.length<1||k<1) return null ;
		ArrayList<Integer> retList = new ArrayList<Integer>();
		if(arr.length<2){
			retList.add(arr[0]);
			return retList;
		}

		// lets apply heap order property first using  down hipify the arr.
		int i = 1, swap = 0, len = arr.length;
		for(i = arr.length/2 -1 ;i>=0;i--){
			downHipifyMax(arr, len, i);
		}
		// extracting greater elements from the arr and to maintain heap property use down hipify.
		for(i=arr.length-1;i>len-k-1;i--){
			retList.add(arr[0]);
			swap = arr[i];
			arr[i] = arr[0];
			arr[0] = swap;
			downHipifyMax(arr,i,0);
		}
		return retList;
	}

	public static void main(String[] args) {
		int[] arr = {2,453,4,43,423,32,32,4676,76,8,67,6,5,243,23,5,1,78};
		// int[] arr = {8,7,6,5,4,3,2,1};
		// int[] arr = {453,4,47};
		
		System.out.println(kLargest(arr,5));		
	}
}