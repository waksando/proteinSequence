/**
 * Created by erosennin on 1/23/2017.
 */


import java.io.*;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.lang.*;




public class FileManipulation {
    private String FILENAME;
    private Linked_List protein=new Linked_List();
    private String id;
    private String os;
    private String sequence;
    private Runtime rs;




    private boolean firstRead=false;


    public void setFilename(String Filename){
        this.FILENAME=Filename;
    }

    public Linked_List ReadFile() {

        //final long startTime = System.nanoTime();

        Scanner fr = null;
        String stext="OS=";
        String stext2="GN=";
        String stext3="PE=";
        StringBuilder idBuider=new StringBuilder();


        try {

            fr=new Scanner(new File(FILENAME));
            Pattern p2 = Pattern.compile(stext);
            Pattern p3 = Pattern.compile(stext2);
            Pattern p4 = Pattern.compile(stext3);
            String sCurrentLine;

            int start,end;
            String str="";
            while (fr.hasNextLine()) {
                sCurrentLine=fr.nextLine();
                Matcher matcher2 = p2.matcher(sCurrentLine);
                Matcher matcher3 = p3.matcher(sCurrentLine);
                Matcher matcher4 = p4.matcher(sCurrentLine);

                //id extract
                if (sCurrentLine.charAt(0)=='>'){
                    if (firstRead){
                        sequence=(str);
                        protein.insertAtEnd(id,os,sequence);
                        str="";
                        idBuider.setLength(0);
                    }
                    int idCount=4;
                    while (sCurrentLine.charAt(idCount)!='|'){
                        idBuider.append(sCurrentLine.charAt(idCount));
                        idCount++;
                    }
                    id=idBuider.toString();
                }

                //os extract
                if ((sCurrentLine.charAt(0) == '>')&& (matcher2.find())) {
                    start=matcher2.start()+3;
                    if (matcher3.find()) {
                        end=matcher3.start()-1;
                        os=(sCurrentLine.substring(start, end));
                    }
                    else if (matcher4.find()) {
                        end=matcher4.start()-1;
                        os=(sCurrentLine.substring(start, end));
                    }
                }

                //reading sequence
                if (sCurrentLine.charAt(0)!='>'){
                    firstRead=true;
                    str+=sCurrentLine;
                    if (!fr.hasNextLine()){
                        sequence=(str);
                        protein.insertAtEnd(id,os,sequence);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        fr.close();
/*        final long duration = System.nanoTime() - startTime;
        long millis= TimeUnit.SECONDS.convert(duration,TimeUnit.NANOSECONDS);
        System.out.println("Duration: "+millis+"s");*/
        return protein;
    }

    public void writeCSV(protein[] list){
        String File="Sorted.csv";
        try {
            FileWriter fileWriter=new FileWriter(File);
            for (int i=0;i<list.length;i++){
                fileWriter.append(list[i].getOs());
                fileWriter.append(',');
                fileWriter.append(list[i].getSpid());
                fileWriter.append('\n');
            }
            fileWriter.close();
            try {

                rs.getRuntime().exec("cmd /c start notepad++ C:\\Users\\sandooyea\\yr2\\lab\\Sorted.csv");
            }
            catch (Throwable e){
                System.out.println("Error");
            }

        }
        catch (Exception e){
            System.out.println("Error");
        }
    }

    public void outputFasta(Linked_List protein, int mlen){
        try{
            Formatter outfile = new Formatter("Sequence.fasta");
            Node current = protein.getHead();
            outfile.format("Minimum length of sequence: %d\nSpid \t Sequence\n",mlen);
            while(current != null){
                if(current.getProtein().getSequence().length()>=mlen){
                    outfile.format("%s \t %s\nNumber of char: %d\n",current.getProtein().getSpid(),current.getProtein().getSequence(),current.getProtein().getSequence().length());

                    current = current.getNext();
                }
                else{
                    current = current.getNext();
                }
            }
            outfile.close();
            try {

                rs.getRuntime().exec("cmd /c start notepad++ C:\\Users\\sandooyea\\yr2\\lab\\Sequence.fasta");
            }
            catch (Throwable e){
                System.out.println("Error");
            }
        }
        catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
        catch(SecurityException se){
            System.out.println("No permission");
        }
    }
}
