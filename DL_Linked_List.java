public class DL_Linked_List {
    private DL_Node head;
    private DL_Node tail;

    public int size;

    public void insertAtEnd(String s, String o, String seq) {
        DL_Node newNode = new DL_Node(s, o, seq);

        if(head == null){
            head = newNode;
            tail = head;
        }
        else{
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public DL_Node getHead(){
        return head;
    }
    public void setHead(DL_Node node){
        this.head=node;
    }
    public void DisplayAll(){
        DL_Node current=head;
        while (current!=null){
            current.displayNode();
            current=current.getNext();
        }
    }
    public void display()    {
        System.out.print("Spid \t OS \t\t\t\t Sequence \n");
        DL_Node current = head; // start at beginning of list
        while(current != null){ // until end of list,
            current.displayNode(); // print data
            current=current.getNext(); // move to next node
        }
        System.out.println(" ");
    }

    //searching
    public void displayById(String spid) {
        //sequential search

        DL_Node current = head; // start at first node

        while(current.getSpid().equals(spid)==false){ // while no match,
            if(current.getNext() == null){ // if end of list,
                System.out.println("Not found");// didn't find it
                break;
            }
            else{ // not end of list,
                current = current.getNext(); // go to next node
            }
        }

        // found & displayed
        current.displayNode();



        //binarysearch
/*        DL_Node left = head;
        DL_Node right = tail;
        DL_Node mid;

        while(left.getSpid().equals(right.getSpid())==false && left.getSpid().compareTo(right.getSpid())==-1){

            //mid = left+(right-left)/2; //error

            if (mid.getSpid().equals(spid)){
                mid.displayNode();
            }
            else if (mid.getSpid().compareTo(spid)==-1){
                left = mid.getNext();
            }
            else{
                right = mid.getPrev();
            }
        }*/



    }
    public int countNode(){
        int count=0;
        DL_Node current=head;
        while (current!=null){
            count++;
            current=current.getNext();
        }
        return count;
    }
}
