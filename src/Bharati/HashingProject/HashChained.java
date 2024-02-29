package Bharati.HashingProject;

import java.io.*;
import java.util.Scanner;

/**
 * To implement the removal of an element in the HashChained class, you can add a method called remove that takes a string S
 * as input and removes it from the hash table if it exists. Here's an implementation of the remove method.
 *
 * The remove method first computes the hash code of the input string S to determine the index in the hash table
 * where the string might be stored. If the bucket at this index is not empty, the method searches for the node that contains
 * the input string.If the first node in the bucket contains the input string, it is removed from the bucket by updating the
 * reference to the next node in the chain. If the input string is not in the first node of the bucket, the method traverses the chain
 * until it finds the node containing the input string. Once found, the method removes the node by updating the reference to the next
 * node in the chain.Note that the remove method updates the value of the instance variable n to reflect the new size of the hash table
 * after the removal of an element.
 */

public class HashChained {
    private class hashNode{
        String data;
        hashNode next;

        public hashNode(String S, hashNode hN){
            data = S;
            next = hN;
        }
    }
    private int hashLength;
    private hashNode hashTabel1[];
    private int n;
    private int countCollisions;

    public HashChained(int length){
        hashLength = length;
        hashTabel1 = new hashNode[length];
        n = 0;
        countCollisions = 0;
    }

    public float loadfactor(){
        return ((float) n)/hashLength;
    }

    public int antData(){
        return n;
    }

    public int countCollisions(){
        return countCollisions;
    }
    /*private String antProbes() {
        return antProbes();
    }*/

    int hash(String S){
        int h = Math.abs(S.hashCode());
        return h % hashLength;
    }

     //the code became modified here.
     //Provides a method that removes a string S from the hash table by searching for it in the linked list at the hash index h.
     //If S is found at the head of the list, its node is removed by updating the next reference of its predecessor.
     //If S is found elsewhere in the list, its node is removed by updating the next reference of its predecessor to skip over it.
     //The number of data items n is decremented after removal.
    //
    void remove(String S){
        int h = hash(S);

        if (hashTabel1[h] != null){
            if(hashTabel1[h].data.equals(S)){
                hashTabel1[h] = hashTabel1[h].next;
                n--;
                return;
            }
            hashNode previous = hashTabel1[h];
            hashNode current = previous.next;

            while (current != null){
                if(current.data.equals(S)){
                    previous.next = current.next;
                    n--;
                    return;
                }
                previous = current;
                current = current.next;
            }
        }
    }


    void insert(String S){
        int h = hash(S);
        n++;

        if(hashTabel1[h] != null){
            countCollisions++;
        }
        hashTabel1[h] = new hashNode(S, hashTabel1[h]);
    }


     //boolean search(String S) is a public method that takes a string S as input and returns a boolean value indicating
     //whether or the string is present in the hash table.
     //First, the hash value of S is computed using the hash method and used to retrieve the corresponding linked list
     //of nodes from the hash table.
     //Then, a loop is used to iterate over the nodes in the linked list,checking if the data field of each node is
     //equal to S. If a node is found whose data field is equal to S, the method returns true.
     //If the loop completes without finding a matching node, the method returns false.


    boolean search(String S){
        int h = hash(S);

        hashNode current = hashTabel1[hash(S)];

        while (current != null){
            if(current.data.compareTo(S) == 0){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static void main(String argv[])
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Hashlengde? ");
        int hashLengde = in.nextInt();

        HashChained hL = new HashChained(hashLengde);

        try
        {
            System.out.print("Datafil? ");
            String filnavn = in.next();
            in.nextLine(); // to remove newline symbol for next input
            Scanner input = new Scanner(new File(filnavn));

            while (input.hasNext())
            {
                hL.insert(input.nextLine());
            }

            System.out.println("\nHashlengde  : " + hashLengde);
            System.out.println("Elementer   : " + hL.antData());
            System.out.printf( "Load factor : %5.3f\n",  hL.loadfactor());


            System.out.println("\nSkrive inn et element som skal fjernes:");
            String elementToRemove = in.nextLine();
            hL.remove(elementToRemove);

            System.out.println("\nHashlengde  : " + hashLengde);
            System.out.println("Element " + elementToRemove + " er fjernet.");
            System.out.println("Elementer   : " + hL.antData());
            System.out.printf( "Load factor : %5.3f\n",  hL.loadfactor());


            System.out.println("\nSkrive inn et element som skal søkes etter:");
            String elementToSearch = in.nextLine();
            if(hL.search(elementToSearch)){
                System.out.println("Element " + elementToSearch + " finnes i tabellen.");
            }else{
                System.out.println("Element " + elementToSearch + " finnes ikke i tabellen");
            }

        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }
    }



}
