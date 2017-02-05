/**
 * Created by erosennin on 1/29/2017.
 */

public class MergeSort {
    private protein[] arr_protein;
    private String choice="";

    public protein[] Sort(Linked_List list,String choice){
        this.choice=choice;
        arr_protein=list.ListToArray(list);

        protein[] sorted_list;
        sorted_list=Merge_Sort(arr_protein);

        return sorted_list;
    }
    public protein[] Merge_Sort(protein[] list){

        if (list.length<=1){
            return list;
        }
        int length=list.length;
        int m_length=length/2;

        protein[] half1;
        protein[] half2;
        if (length%2==0){
            half1=new protein[m_length];
            half2=new protein[m_length];
        }
        else {
            half1=new protein[m_length];
            half2=new protein[m_length+1];
        }
        for (int i=0;i<m_length;i++){
            half1[i]=list[i];
        }
        int x=m_length;
        for (int i=0;i<half2.length;i++){
            half2[i]=list[x];
            ++x;
        }

        protein[] list1=Merge_Sort(half1);
        protein[] list2=Merge_Sort(half2);

        protein[] merged_list=MergeList(list1,list2);

        return merged_list;
    }

    public protein[] MergeList(protein[] list1,protein[] list2){
        int size=list1.length+list2.length;
        protein[] result=new protein[size];
        String data1="";
        String data2="";
        int i=0;
        int j=0;
        int k=0;
        while ((i<list1.length)&&(j<list2.length)){
            if (choice=="osid") {
                data1 = list1[i].getOsID();
                data2 = list2[j].getOsID();
            }
            else {
                data1=list1[i].getSpid();
                data2=list2[j].getSpid();
            }
            if (data1.compareToIgnoreCase(data2)<0){
                result[k]=(list1[i]);
                i++;
            }
            else {
                result[k]=(list2[j]);
                j++;
            }
            k++;
        }
        for (int z=i;z<list1.length;z++){
            result[k]=list1[z];
            k++;
        }
        for (int z=j;z<list2.length;z++){
            result[k]=list2[z];
            k++;
        }
        return result;
    }
}
