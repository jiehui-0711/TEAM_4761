package test;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        int a[] ={1,2,2,5};
        String b[] = {"oath","eat"};
       
        Solution so = new Solution();
        ArrayList<List<Integer>> arr = new ArrayList<>();
        arr.isEmpty();
        so. maximumElementAfterDecrementingAndRearranging(a);
    }
}
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
       int n[]=new int [arr.length+1];
       for(int i=0;i<arr.length;i++){
           n[Math.min(arr[i],arr.length)]++;
       } 
       int ans=1;
       for(int i=1;i<=arr.length;i++){
           ans=Math.min(ans+n[i],i);
       }
       return(ans);
    }
}