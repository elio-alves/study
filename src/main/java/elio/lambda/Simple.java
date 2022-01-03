/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elio.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 *
 * @author elio_alves
 */
public class Simple {

    public static void main(String args[]) {
        arrayListExample();
        consumerExample();
    }
    
    /**
     * The forEach permit a consumer method, so, I can use the lambda here
     */
    public static void arrayListExample(){
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        numbers.forEach((n) -> {
            System.out.println(n);
        });
    }
    
    /**
     * Create a consumer separately and use them into the function
     */
    public static void consumerExample(){
        Consumer<Integer> method = (n)->{System.out.println("Print anything: "+n);};
        
        ArrayList<Integer> numbers = new ArrayList(Arrays.asList(1,2,3));
        numbers.forEach(method);
    }
}
