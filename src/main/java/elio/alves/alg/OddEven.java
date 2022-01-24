/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elio.alves.alg;

/**
 *
 * @author elio_alves
 */
public class OddEven {

    public static void main(String args[]) {
        System.out.println(findIt(new int[]{5,19,27,5,19}));
    }

    /**
     * Function to find an odd uniq value in array
     * 
     * @param A
     * @return 
     */
    public static int findIt(int[] A) {
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }
        return xor;
    }
}
