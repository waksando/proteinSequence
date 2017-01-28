/**
 * Created by erosennin on 1/23/2017.
 */


import java.io.*;
import java.sql.Time;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.lang.*;
import java.util.concurrent.TimeUnit;




public class FileManipulation {
    private String FILENAME;
/*    private LinkedList<String> sequence=new LinkedList<String>();
    private LinkedList<String> id=new LinkedList<String>();
    private LinkedList<String> os=new LinkedList<String>();*/
    private DL_Linked_List protein=new DL_Linked_List();
    private String id;
    private String os;
    private String sequence;




    private boolean firstRead=false;


    public void setFilename(String Filename){
        this.FILENAME=Filename;
    }
    public DL_Linked_List ReadFile() {

        final long startTime = System.nanoTime();

        Scanner fr = null;
        String stext="OS=";
        String stext2="GN=";
        String stext3="PE=";


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
                    }
                    id=(sCurrentLine.substring(4,10));
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

        final long duration = System.nanoTime() - startTime;
        long millis= TimeUnit.SECONDS.convert(duration,TimeUnit.NANOSECONDS);
        System.out.println("Duration: "+millis+"s");
        return protein;

    }
}
