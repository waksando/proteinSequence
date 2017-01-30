/**
 * Created by erosennin on 1/29/2017.
 */


public class MergeSort {
    private int choice;
    public DL_Linked_List Sort(DL_Linked_List list,int choice){
        DL_Node ori_head=list.getHead();
        this.choice=choice;

        DL_Linked_List sorted_list=new DL_Linked_List();
        sorted_list.setHead(Merge_Sort(ori_head));

        return sorted_list;
    }
    public DL_Node Merge_Sort(DL_Node node){
        DL_Node ori_head=node;

        int mlength=countDL_Node(node)/2;
        if (ori_head.getNext()==null){
            return node;
        }
        while (mlength-1>0){
            ori_head=ori_head.getNext();
            mlength--;
        }
        DL_Node new_head=ori_head.getNext();
        ori_head.setNext(null);
        ori_head=node;

        DL_Node list1=Merge_Sort(ori_head);
        DL_Node list2=Merge_Sort(new_head);

        DL_Node merged_list=MergeList(list1,list2);

        return merged_list;
    }

    public DL_Node MergeList(DL_Node list1,DL_Node list2){
        DL_Node result;
        DL_Node next;
        String data1="",data2="";

        if (list1==null){
            return list2;
        }
        if (list2==null){
            return list1;
        }
        if (choice==1){
            data1=list1.getSpid();
            data2=list2.getSpid();
        }
        else if (choice==2){
            data1=list1.getOs();
            data2=list2.getOs();
        }
        if (data1.compareTo(data2)>0){
            result=list2;
            next=MergeList(list1,list2.getNext());
            result.setNext(next);
            return result;
        }
        else {
            result=list1;
            result.setNext(MergeList(list1.getNext(),list2));
            return result;
        }
    }

    public int countDL_Node(DL_Node node){
        int count=0;
        DL_Node current=node;
        while (current!=null){
            count++;
            current=current.getNext();
        }
        //System.out.println(count);
        return count;
    }
/*    public void DisplayAll(DL_Node sorted_list){
        int length=countDL_Node(sorted_list);
        System.out.println("count: "+length);
        DL_Node current=sorted_list;
        for (int i=0;i<length;i++){
            current.displayDL_Node();
            current=current.getNext();
        }
    }*/
}
