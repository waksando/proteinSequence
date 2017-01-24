/**
 * Created by erosennin on 1/23/2017.
 */


import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.lang.*;




public class FileManipulation {
    private String FILENAME;
    private LinkedList<String> sequence=new LinkedList<String>();
    private LinkedList<String> id=new LinkedList<String>();
    private LinkedList<String> os=new LinkedList<String>();


    private boolean firstRead=false;


    public void setFilename(String Filename){
        this.FILENAME=Filename;
    }
    public void ReadFile() {

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

            long count=0;
            int start,end;
            String str="";
            while (fr.hasNextLine()) {
                sCurrentLine=fr.nextLine();
                Matcher matcher2 = p2.matcher(sCurrentLine);
                Matcher matcher3 = p3.matcher(sCurrentLine);
                Matcher matcher4 = p4.matcher(sCurrentLine);

                //id extract
                if (sCurrentLine.charAt(0)=='>'){
                    count++;
                    id.add(sCurrentLine.substring(4,10));
                }

                //os extract
                if ((sCurrentLine.charAt(0) == '>')&& (matcher2.find())) {
                    start=matcher2.start()+3;
                    if (matcher3.find()) {
                        end=matcher3.start()-1;
                        os.add(sCurrentLine.substring(start, end));
                    }
                    else if (matcher4.find()) {
                        end=matcher4.start()-1;
                        os.add(sCurrentLine.substring(start, end));
                    }
                }

                //reading sequence
                if (sCurrentLine.charAt(0)!='>'){
                    firstRead=true;
                    str+=sCurrentLine;
                    if (!fr.hasNextLine()){
                        sequence.add(str);
                    }
                }
                else if (firstRead){
                    sequence.add(str);
                    str="";
                }
            }
            System.out.println(count);
            System.out.println("ID: "+id.size()+" OS: "+os.size()+" Sequence:"+sequence.size());
/*            for (int i=0;i<sequence.size();i++){
                System.out.println("ID: "+id.get(i)+" OS: "+os.get(i)+" Sequence:"+sequence.get(i));
            }*/

        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();

        }

        final long duration = System.nanoTime() - startTime;
        System.out.println("Duration: "+duration);

    }
}
