/**
 * Created by erosennin on 2/2/2017.
 */

public class QuickSort {
    private protein[] array;


    public protein[] Sort(Linked_List list) {

        array=list.ListToArray(list);
        protein[] sorted_list;
        int length = array.length;
        sorted_list = quicksort(0, length - 1);
        return sorted_list;
    }

    private protein[] quicksort(int low, int high) {
        //i is leftmost element
        int i = low;
        int j = high;


        protein pivot = array[low + (high - low) / 2];

        while (i <= j) {
            while (array[i].getOsID().compareTo(pivot.getOsID()) < 0) {
                i++; //finding element greater than pivot
            }
            while (array[j].getOsID().compareTo(pivot.getOsID()) > 0) {
                j--; //finding element less than pivot
            }

            if (i <= j) {
                //swapping the element if the left side element is greater than pivot
                protein temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                i++;
                j--;

            }


        }
        //recursion to sort left side of pivot
        if (low < j) quicksort(low, j);
        //recursion to sort right side of pivot
        if (i < high) quicksort(i, high);

        return array;
    }

}



