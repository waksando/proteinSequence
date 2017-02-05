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
        if (args.length!=0) {
            fileManipulation.setFilename(args[0]);
        }
        else {
            fileManipulation.setFilename("uniprot_sprot.fasta");
        }

        protein=fileManipulation.ReadFile();

        protein.DHFill();

        while(choice != 0){
            System.out.println("1. Display sequence of specific SwissProt ID: ");
            System.out.println("2. Output to FASTA format file, list of sequences with a minimum length specified by user: ");
            System.out.println("3. Output to a CSV file a list of SwissProt ID and their organism (OS) sorted by organism then SwissProt ID ");
            System.out.println("0. Exit");
            System.out.println("Input choice: ");
            choice=InputNum();

            if (choice==0){
                break;
            }
            System.out.println();
            switch(choice){
                case 1: {
                    int menu1 = 1;
                    while (menu1 != 0) {
                        System.out.println("1.Binary Search(fastest)");
                        System.out.println("2.Hash Search");
                        System.out.println("3.Sequential Search(Slowest)");
                        System.out.println("0.BACK");
                        System.out.println("Input choice: ");
                        menu1=InputNum();

                        if (menu1==0){
                            break;
                        }


                        System.out.println("Input SwissProt ID: ");
                        String spid = input.next();

                        switch (menu1) {
                            case 1: {
                                protein[] OS_Sorted;
                                OS_Sorted = mergeSort.Sort(protein, "id");
                                protein.BinarySearch(spid, OS_Sorted);
                            }
                            break;
                            case 2: {
                                protein.DHashSearch(spid);
                            }
                            break;
                            case 3: {
                                protein.SequentialSearch(spid);
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
                        System.out.println("1.MergeSort(Fastest)");
                        System.out.println("2.QuickSort");
                        System.out.println("3.InsertionSort(Slowest.Try at your own risk(1-2hrs))");
                        System.out.println("0.BACK");
                        System.out.println("Input choice: ");

                        menu2=InputNum();

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
                        }
                    }
                }
                break;
            }
        }
    }
    public static int InputNum(){
        int num;
        Scanner input=new Scanner(System.in);
        do {
            try {
                num = input.nextInt();

                if ((num >= 0) && (num <= 3)){
                    break;
                }

            } catch (InputMismatchException e) {}
            finally {
                input.nextLine();
            }

            System.out.print("Input must be a number between 0 and 3: ");
        } while (true);

        return num;
    }

    public static void Sort(int choice){
        //final long startTime = System.nanoTime();
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
/*        final long duration = System.nanoTime() - startTime;
        long millis = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);
        System.out.println("Duration: " + millis + "ms");*/
        fileManipulation.writeCSV(OS_Sorted);
        System.out.println("DONE: Saved to file");
    }
}
