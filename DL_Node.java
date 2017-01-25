public class DL_Node {
    private DL_Node next, prev;
    private String spid;
    private String os;
    private String sequence;

    public DL_Node(String s, String o, String seq){
        spid = s;
        os = o;
        sequence = seq;
        next = null;
        prev = null;
    }

    public void setPrev(DL_Node p){prev = p;}
    public DL_Node getPrev(){return prev;}
    public void setNext(DL_Node n){next = n;}
    public DL_Node getNext(){return next;}

    public void setSpid(String s){spid = s;}
    public void setOs(String o){os = o;}
    public void setSequence(String seq){sequence = seq;}

    public String getSpid(){return spid;}
    public String getOs(){return os;}
    public String getSequence(){return sequence;}

    public void displayNode(){
        System.out.println(spid+"\t "+os+"\t "+sequence);
    }
}
