import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Index {

    protected static QuickSort quickSort= new QuickSort();
    protected static FileManipulation fileManipulation=new FileManipulation();
    protected static MergeSort mergeSort=new MergeSort();
    protected static InsertionSort insertionSort=new InsertionSort();
    protected static Linked_List protein;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        int choice=1;

        System.out.println("Please Wait...");
        //fileManipulation.setFilename(args[0]);
        fileManipulation.setFilename("testFile.txt");
        protein=fileManipulation.ReadFile();
        System.out.println("Total number of nodes: "+protein.countNode());

        protein.DHFill();

        while(choice != 0){
            System.out.println("1. Display sequence of specific SwissProt ID: ");
            System.out.println("2. Output to FASTA format file, list of sequences with a minimum length specified by user: ");
            System.out.println("3. Output to a CSV file a list of SwissProt ID and their organism (OS) sorted by organism then SwissProt ID ");
            System.out.println("0. Exit");
            System.out.println("Input choice: ");
            try {
                choice = input.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Error");
                choice=1;
            }
            if (choice==0){
                break;
            }
            System.out.println("");
            switch(choice){
                case 1: {
                    int menu1 = 1;
                    while (menu1 != 0) {
                        System.out.println("1.Sequential Search");
                        System.out.println("2.Binary Search");
                        System.out.println("3.Hash Search");
                        System.out.println("0.BACK");
                        System.out.println("Input choice: ");
                        try {
                            menu1 = input.nextInt();
                        }
                        catch (InputMismatchException e){
                            System.out.println("Error");
                            menu1=0;
                        }

                        if (menu1==0){
                            break;
                        }


                        System.out.println("Input SwissProt ID: ");
                        String spid = input.next();

                        switch (menu1) {
                            case 1: {
                                protein.SequentialSearch(spid);
                            }
                            break;
                            case 2: {
                                protein[] OS_Sorted;
                                OS_Sorted = mergeSort.Sort(protein, "id");
                                protein.BinarySearch(spid, OS_Sorted);
                            }
                            break;
                            case 3: {
                                protein.DHashSearch(spid);
                            }
                            break;
                            default:{
                                System.out.println("Wrong Choice,Please Try Again");
                            }
                            break;
                        }
                    }
                }
                break;
                case 2:{
                    System.out.println("Input minimum length desired of sequences: ");
                    int mlen = input.nextInt();
                    fileManipulation.outputFasta(protein,mlen);
                    System.out.println("Done:Saved to file");

                }
                break;
                case 3: {
                    int menu2 = 1;
                    while (menu2 != 0) {
                        System.out.println("1.MergeSort");
                        System.out.println("2.QuickSort");
                        System.out.println("3.InsertionSort");
                        System.out.println("0.BACK");
                        System.out.println("Input choice: ");
                        try {
                            menu2 = input.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Error");
                            menu2 = 0;
                        }
                        if (menu2 == 0) {
                            break;
                        }

                        switch (menu2) {
                            case 1: {
                                Sort(1);
                            }
                            break;
                            case 2: {
                                Sort(2);
                            }
                            break;
                            case 3: {
                                Sort(3);
                            }
                            break;
                            default: {
                                System.out.println("Wrong Choice,Please Try Again");
                            }
                            break;
                        }
                    }
                }
                break;
                default:{
                    System.out.println("Wrong Choice,Please Try Again");
                }
                break;
            }
        }
    }
    public static void Sort(int choice){
        final long startTime = System.nanoTime();
        protein[] OS_Sorted=null;
        switch (choice){
            case 1:{
                OS_Sorted = mergeSort.Sort(protein, "osid");
            }
            break;
            case 2:{
                OS_Sorted=quickSort.Sort(protein);
            }
            break;
            case 3:{
                OS_Sorted=insertionSort.Sort(protein);
            }
            break;
        }
        final long duration = System.nanoTime() - startTime;
        long millis = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);
        System.out.println("Duration: " + millis + "ms");
        fileManipulation.writeCSV(OS_Sorted);
        System.out.println("DONE");
    }
}
