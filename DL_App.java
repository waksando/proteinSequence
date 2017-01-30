import java.util.Scanner;

public class DL_App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        DL_Linked_List protein;
        FileManipulation fileManipulation=new FileManipulation();
        //fileManipulation.setFilename("C:\\Users\\sandooyea\\yr2\\lab\\src\\uniprot_sprot.fasta");
        fileManipulation.setFilename("C:\\Users\\sandooyea\\yr2\\lab\\src\\testFile.txt");
        protein=fileManipulation.ReadFile();
        System.out.println("Total number of nodes: "+protein.countNode());


        //protein.display();

        System.out.println("1. Display sequence of specific SwissProt ID: ");
        System.out.println("2. Output to FASTA format file, list of sequences with a minimum length specified by user: ");
        System.out.println("3. Output to a CSV file a list of SwissProt ID and their organism (OS) sorted by organism then SwissProt ID ");
        System.out.println("0. Exit");
        System.out.println("Input choice: ");
        choice = input.nextInt();
        System.out.println("");

        while(choice != 0){
            switch(choice){
                case 1:{String buffer = input.nextLine();
                    System.out.println("Input SwissProt ID: ");

                    String spid = input.nextLine();
                    protein.displayById(spid);
                }
                break;
                case 2:{System.out.println("Input minimum length desired of sequences: ");
                    int mlen = input.nextInt();
                    //outputFasta(mlen);

                }
                break;
                case 3:{//outputCSV();
                    MergeSort mergeSort=new MergeSort();
                    DL_Linked_List OS_Sorted;
                    OS_Sorted=mergeSort.Sort(protein,1);
                    OS_Sorted.DisplayAll();
                }
                break;
            }
            System.out.println("1. Display sequence of specific SwissProt ID: ");
            System.out.println("2. Output to FASTA format file, list of sequences with a minimum lenght specified by user: ");
            System.out.println("3. Output to a CSV file a list of SwissProt ID and their organism (OS) sorted by organism then SwissProt ID ");
            System.out.println("0. Exit");
            System.out.println("Input choice: ");
            choice = input.nextInt();
        }
    }

    public static void outputFasta(int mlen){

    }

    public static void outputCSV(){

    }

}
