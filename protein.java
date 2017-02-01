
public class protein {

    private String spid;
    private String os;
    private String sequence;

    public protein(String s, String o, String seq){
        spid = s;
        os = o;
        sequence = seq;
    }

    public void setSpid(String s){spid = s;}
    public void setOs(String o){os = o;}
    public void setSequence(String seq){sequence = seq;}

    public String getSpid(){return spid;}
    public String getOs(){return os;}
    public String getSequence(){return sequence;}

    public void displayNode(){
        System.out.println("SwissProtID:\t"+spid);
        System.out.println("Organism:\t\t"+os);
        System.out.println("Sequence:");
        System.out.print("\t\t\t\t");
        for (int i=0;i<sequence.length();i++){
            System.out.print(sequence.charAt(i));
            if ((i%60==0)&&(i!=0)){
                System.out.println();
                System.out.print("\t\t\t\t");
            }
        }
        System.out.println();
    }

    public String getOsID(){
        return os+spid;
    }

}
