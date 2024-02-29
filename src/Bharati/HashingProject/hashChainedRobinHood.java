package Bharati.HashingProject;

import java.io.*;
import java.util.Scanner;

 //In the insert method, we first check if the current hash table index is already occupied.
 //If it is, we traverse the linked list until we reach the end and then insert the new data element at the end of the list.
 //This ensures that the most recently inserted data elements are always at the beginning of the linked list,
 //making them the first to be searched when resolving collisions.
public class hashChainedRobinHood {
    private class hashNode {

        String data;
        hashNode next;

        public hashNode(String S, hashNode hN) {
            data = S;
            next = hN;
        }
    }

    private int hashLength;
    private hashNode hashTable[];
    private int n;
    private int countCollisions;
    private int currentSlot;


    public hashChainedRobinHood(int length) {
        hashLength = length;
        hashTable = new hashNode[length];
        n = 0;
        countCollisions = 0;
        currentSlot = 0;
    }

    public float loadfactor() {
        return ((float) n) / hashLength;
    }

    public int antData() {
        return n;

    }
    public int getSize(){
        return n;
    }

    public int countCollisions() {
        return countCollisions;

    }

    int hash(String S) {
        int h = Math.abs(S.hashCode());
        int slot = h % hashLength;
        //int slot = currentSlot;
        //currentSlot = (currentSlot + 1) % hashLength;
        return slot;
    }

    void insert(String S) {

        int h = hash(S);

        n++;

        if (hashTable[h] != null) {
            countCollisions++;
        }
        hashTable[h] = new hashNode(S, hashTable[h]);
        n++;
    }

    boolean search(String S) {
        int h = hash(S);
        hashNode hN = hashTable[hash(S)];

        while (hN != null) {

            if (hN.data.compareTo(S) == 0) {
                return true;
            }

            hN = hN.next;

        }

        return false;
    }

    public static void main(String argv[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Hashlength? ");
        int hashlength = in.nextInt();

        hashChainedRobinHood hC = new hashChainedRobinHood(hashlength);

        try {
            System.out.print("FileName ");
            String filename = in.next();
            in.nextLine(); // to remove newLine symbol for next input
            Scanner input = new Scanner(new File(filename));

            while (input.hasNext()) {
                hC.insert(input.nextLine());
            }
            System.out.println("\nHashlengde : " + hashlength);
            System.out.println("Elementer : " + hC.antData());
            System.out.printf("Load  factor : %5.3f\n", hC.loadfactor());
            System.out.println("kollisioner :" + hC.countCollisions() + "\n");

            System.out.println("Enter the String for search: ");
            String searchStr = in.nextLine();
            boolean result = hC.search(searchStr);


            if(result){
                System.out.println(searchStr + " is in the hash table. ");
            }else {
                System.out.println(searchStr + "is not in the hash table. ");
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);

        }finally{
            in.close();
        }
    }
}
