import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Index {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MergeSort mergeSort=new MergeSort();

        int choice=1;

        Linked_List protein;
        FileManipulation fileManipulation=new FileManipulation();
        System.out.println("Please Wait...");
        fileManipulation.setFilename("C:\\Users\\sandooyea\\yr2\\lab\\src\\uniprot_sprot.fasta");
        //fileManipulation.setFilename("C:\\Users\\sandooyea\\yr2\\lab\\src\\testFile.txt");
        protein=fileManipulation.ReadFile();
        System.out.println("Total number of nodes: "+protein.countNode());


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
                                ArrayList<protein> arrayListProtein = new ArrayList<>();
                                protein[] OS_Sorted;
                                OS_Sorted = mergeSort.Sort(protein, "id");
                                for (int i = 0; i < OS_Sorted.length; i++) {
                                    arrayListProtein.add(OS_Sorted[i]);
                                }
                                protein.BinarySearch(spid, arrayListProtein);
                            }
                            break;
                            case 3: {
                                protein.FillHashTableDH();
                                protein.searchDH(spid);
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
                    //outputFasta(mlen);

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
                                final long startTime = System.nanoTime();
                                protein[] OS_Sorted;

                                OS_Sorted = mergeSort.Sort(protein, "osid");
                                System.out.println("OS Size" + OS_Sorted.length);
                                System.out.println("DONE");
/*                                for (int i = 0; i < 9; i++) {
                                    System.out.println(OS_Sorted[i].getOsID());
                                }*/

                                final long duration = System.nanoTime() - startTime;
                                long millis = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);
                                System.out.println("Duration: " + millis + "ms");
                            }
                            break;
                            case 2: {

                            }
                            break;
                            case 3: {

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
}
