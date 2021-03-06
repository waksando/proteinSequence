public class InsertionSort {

    private protein[] arr_protein;

    public protein[] Sort(Linked_List list){
        arr_protein=list.ListToArray(list);

        protein[] sorted_list;
        sorted_list=Insertion_Sort(arr_protein);

        return sorted_list;
    }
    public protein[] Insertion_Sort(protein[] ArrPro) {

        for(int i = 1;i<=(ArrPro.length-1);i++){

            protein key = ArrPro[i];
            int j = i-1;
            while((j>=0) && ((ArrPro[j].getOsID()).compareToIgnoreCase(key.getOsID())>0)){
                ArrPro[j+1]=ArrPro[j];
                j=j-1;
            }
            ArrPro[j+1]=key;
        }
        return ArrPro;
    }
}