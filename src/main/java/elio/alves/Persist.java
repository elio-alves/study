/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elio.alves;

/**
 *
 * @author elio_alves
 */
public class Persist {

    public static void main(String args[]) {
        System.out.println(persistence(5));
        System.out.println(persistence(39));
        System.out.println(persistence(4));
        System.out.println(persistence(25));
        System.out.println(persistence(999));
        System.out.println(persistence(56867));
    }

    /**
     * Method to reduce the value to one digt value
     * 
     * Ex. 39 
     *   3*9 = 27
     *   2*7 = 14
     *   1*4= 4 (one digit)
     * 
     *  Must return 3 because i used 3 operations
     * 
     * @param n
     * @return 
     */
    public static int persistence(long n) {
        String sn = n + "";
        int count = 0;
        do {
            char array[] = sn.toCharArray();
            int result = 1;
            
            // cont if only more than 1
            if(array.length>1){
                count++;
            }
            
            // make the multiply and store the result string
            for (char d : array) {
                Integer i = Integer.parseInt(d + "");
                result = i * result;
            }
            sn = result + "";

        } while (sn.length() > 1);
        return count;
    }
}
