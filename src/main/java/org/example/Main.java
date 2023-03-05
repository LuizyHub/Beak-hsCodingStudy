package org.example;

public class Main {
    private static int binarySearch(int[] a, int key){
        return binarySearch0(a,0,a.length,key);
    }
    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            if (low == high && a[low] == key)
                return low;
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else //midVal == key
                high = mid;
        }
        return -(low+1);  // key not found.
    }
    public static void main(String[] args) {
        String s = "add 3,5";
        System.out.println(s.charAt(','));
//        String ss = s.substring(3,s.charAt(':'));
//        System.out.println(Integer.parseInt(s.substring(3,s.charAt(','))));
        System.out.println(Integer.parseInt("123a"));
        int[] arr = {0,0,0,0,2,2,2,4,4,5,5,6,8,9,9};
        for (int i = -1; i < 10; i++) {
            System.out.println(i + ":" + binarySearch(arr,i));

        }
    }
}