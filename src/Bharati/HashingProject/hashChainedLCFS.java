package Bharati.HashingProject;

import java.io.*;
import java.util.Scanner;


 //In the insert method, we first check if the current hash table index is already occupied.
 // If it is, we traverse the linked list until we reach the end and then insert the new data element at the end of the list.
 // This ensures that the most recently inserted data elements are always at the beginning of the linked list,
 // making them the first to be searched when resolving collisions.


public class hashChainedLCFS {
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

    public hashChainedLCFS(int length) {
        hashLength = length;
        hashTable = new hashNode[length];
        n = 0;
        countCollisions = 0;
    }

    public float loadfactor() {
        return ((float) n) / hashLength;
    }

    public int antData() {
        return n;
    }

    public int countCollisions() {
        return countCollisions;
    }

    int hash(String S) {
        int h = Math.abs(S.hashCode());
        return h % hashLength;
    }



    void insert(String S) {
        int h = hash(S);
        n++;

        if (hashTable[h] != null) {
            countCollisions++;
            hashNode previousNode = null;
            hashNode currentNode = hashTable[h];

            while (currentNode != null) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }

            previousNode.next = new hashNode(S, null);
        } else {
            hashTable[h] = new hashNode(S, null);
        }
    }

     //It searches for the string S in the hash table by computing its hash value and then traversing the linked list
     //at that index to find a node with the same data value as S. If such a node is found, the method returns true,
     //indicating that the string is present in the hash table. If the end of the linked list is reached without
     //finding a node with the same data value as S, the method returns false, indicating that the string is
     //not present in the hash table.

    boolean search(String S) {
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

        hashChainedLCFS hC = new hashChainedLCFS(hashlength);
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

            //Search for a string
            System.out.println("Enter a string to search: ");
            String searchString = in.nextLine();
            if (hC.search(searchString)) {
                System.out.println("The String has been found in table. ");
            } else {
                System.out.println("The String has not been found in table. ");
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);

        }
    }
}
