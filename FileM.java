/**
 * Created by erosennin on 1/23/2017.
 */


import java.io.*;
import java.util.LinkedList;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.lang.*;



public class FileM {
    private static final String FILENAME = "C:\\Users\\sandooyea\\yr2\\lab\\src\\uniprot_sprot.fasta";
    private static LinkedList<String> sequence=new LinkedList<String>();
    private static boolean firstRead=false;

    public static void main(String[] args) {

        final long startTime = System.nanoTime();

        BufferedReader br = null;
        FileReader fr = null;
        String RegEx="^sp|([A-Z][A-Z|0-9]{4}[0-9])\\|";
        String stext="OS";
        String stext2="GN";


        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            Pattern p1 = Pattern.compile(RegEx);
            Pattern p2 = Pattern.compile(stext);
            Pattern p3 = Pattern.compile(stext2);
            String sCurrentLine;

            long count=0;
            int test=0;
            int start,end;
            String str="";
            while ((sCurrentLine = br.readLine()) != null) {
                Matcher matcher = p1.matcher(sCurrentLine);
                Matcher matcher2 = p2.matcher(sCurrentLine);
                Matcher matcher3 = p3.matcher(sCurrentLine);
                //System.out.println(sCurrentLine);
                test++;
                if (test==30){
                    break;
                }
                //id extract
/*                while(matcher.find()) {
                    *//*System.out.println("Group: " + matcher.group(1) + " : "
                            + matcher.start() + " - " + matcher.end());*//*
                    count++;
                }*/
                //os extract
/*                while ((matcher2.find())&&(matcher3.find())){
                    System.out.println("OS: "+matcher2.start()+" GN: "+matcher3.start());
                    start=matcher2.start()+3;
                    end=matcher3.start()-1;
                    if (start<end) {
                        System.out.println(sCurrentLine.substring(start, end));
                    }
                    else {
                        end=sCurrentLine.length()-1;
                        System.out.println(sCurrentLine.substring(start, end));

                    }
                }*/
                //reading sequence
                if (sCurrentLine.charAt(0)!='>'){
                    firstRead=true;
                    str+=sCurrentLine;
                }
                else if (firstRead){
                    sequence.add(str);
                    str="";
                }
            }
            System.out.println(count);
            for (String x: sequence){
                System.out.println(x);
            }

        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();

        }

        final long duration = System.nanoTime() - startTime;
        System.out.println("Duration: "+duration);

    }
}
