import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by erosennin on 1/23/2017.
 */



public class test {
    public static void main(String[] args) {
        FileManipulation fm=new FileManipulation();
        fm.setFilename("C:\\Users\\sandooyea\\yr2\\lab\\src\\uniprot_sprot.fasta");
        fm.ReadFile();
    }
}

