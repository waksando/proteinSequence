

public class Node {
    private protein p;
    private Node next;
    private Node prev;

    public Node(protein P){

        p = P;
        next = null;
        prev = null;
    }

    public void setPrev(Node p){prev = p;}
    public void setNext(Node n){next = n;}
    public Node getNext(){return next;}
    public protein getProtein(){
        return p;
    }
}
