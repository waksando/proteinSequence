
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Linked_List {
    private String []HT= new String[size()];
    private String []SEQ= new String[size()];
    private int middle=size()/2;//middle position of hash table


    private Node head;
    private Node tail;

    public Node getHead(){
        return head;
    }



    public protein[] ListToArray(Linked_List list){
        protein[] arr_protein;
        Node ori_head=list.getHead();
        Node current=ori_head;
        arr_protein=new protein[list.countNode()];
        int k=0;
        while (current!=null){
            arr_protein[k]=(current.getProtein());
            current=current.getNext();
            k++;
        }
        return arr_protein;
    }
    public void insertAtEnd(String s, String o, String seq) {
        Node newNode = new Node(new protein(s,o,seq));

        if(head == null){
            head = newNode;
            tail = head;
        }
        else{
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    public int countNode() {
        int count = 0;
        Node current = head;
        if(head == null){
            return count;
        }
        else{
            count++;
            while(current.getNext() != null){
                current=current.getNext();
                count++;
            }
            return count;
        }
    }


    public void SequentialSearch(String spid){
        //final long startTime = System.nanoTime();

        Node current = head; // start at first node
        while (current!=null){
            if (current.getProtein().getSpid().equals(spid)==true){
                // found & displayed
                current.getProtein().displayNode();
                break;
            }
            current=current.getNext();
        }
        if (current==null){
            System.out.println("Not found");
        }

/*        final long duration = System.nanoTime() - startTime;
        long millis= TimeUnit.MILLISECONDS.convert(duration,TimeUnit.NANOSECONDS);
        System.out.println("Duration: "+millis+"ms");*/
    }

    public void BinarySearch(String spid,protein[] LinkArray) { //searching
        //final long startTime = System.nanoTime();
        int low = 0;
        int high = LinkArray.length;

        while(low<=high){
            int mid = low+(high-low)/2;

            int decisionParameter = (LinkArray[mid].getSpid()).compareTo(spid);
            if(LinkArray[mid].getSpid().equals(spid)){
                System.out.println("Id Found");
                LinkArray[mid].displayNode();
                System.out.println("");
                break;
            }
            else if (decisionParameter < 0){//Value is higher than mid
                low = mid+1;
            }
            else{//Value is lower than mid
                high = mid-1;
            }
        }
/*        final long duration = System.nanoTime() - startTime;
        long millis= TimeUnit.MILLISECONDS.convert(duration,TimeUnit.NANOSECONDS);
        System.out.println("Duration: "+millis+"ms");*/
    }


    public int prime(int k){
        BigInteger v=new BigInteger((String.valueOf(k)));
        return Integer.parseInt(v.nextProbablePrime().toString());
    }
    public int size(){
        return prime(countNode());
    }
    public int Hash(char V[], int size){
        int SIZE = V.length;
        int H = V[0];
        for(int j=1; j<SIZE; j++){
            H = (((H*32) + V[j])%size);
        }
        return H;
    }
    public void DHashInsert(String val, String seq){
        char [] V = val.toCharArray();
        int H1 = Hash(V, size());
        int H2 = middle - Hash(V, middle);
        while(HT[H1]!=null){
            H1 = (H1+H2)%size();
        }
        HT[H1] = val;
        SEQ[H1]=seq;
    }

    public void DHFill(){
        //final long startTime = System.nanoTime();
        Node current = head;							//start at first node
        String id;
        String seq;
        while(current!=null){
            id = current.getProtein().getSpid();		//retrieving the id
            seq= current.getProtein().getSequence();	//retrieving the sequence
            DHashInsert(id, seq);						//filling the HashTable
            current = current.getNext();				//next node
        }
/*        final long duration = System.nanoTime() - startTime;
        long millis= TimeUnit.MILLISECONDS.convert(duration,TimeUnit.NANOSECONDS);
        System.out.println("Duration: "+millis+"ms");*/
    }

    public void DHashSearch(String val){
        //final long startTime = System.nanoTime();
        char [] V = val.toCharArray();

        int H1=Hash(V,size());
        int H2 = middle - Hash(V, middle);
        while(HT[H1]!=null){
            if(HT[H1].equals(val)){
                System.out.println("SwissProtID:\t"+val);
                System.out.println("Sequence:");
                System.out.print("\t\t\t\t");
                for (int i=0;i<SEQ[H1].length();i++){
                    System.out.print(SEQ[H1].charAt(i));
                    if ((i%60==0)&&(i!=0)){
                        System.out.println();
                        System.out.print("\t\t\t\t");
                    }
                }
                System.out.println();
/*                final long duration = System.nanoTime() - startTime;
                long millis= TimeUnit.MILLISECONDS.convert(duration,TimeUnit.NANOSECONDS);
                System.out.println("Duration: "+millis+"ms");*/
                return;
            }
            H1 = (H1+H2)%size();
        }
        System.out.println("Not Found");
/*        final long duration = System.nanoTime() - startTime;
        long millis= TimeUnit.MILLISECONDS.convert(duration,TimeUnit.NANOSECONDS);
        System.out.println("Duration: "+millis+"ms");*/
    }
}
