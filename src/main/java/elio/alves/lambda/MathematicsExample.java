/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elio.alves.lambda;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author elio_alves
 */
public class MathematicsExample {

    public static void main(String args[]) {
        Runnable r = () -> System.out.println("hello world");
        new Thread(r).start();
        
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.forEach(n -> {System.out.println(n);});
        
        System.out.println();
        System.out.println("n²:");
        list.forEach(n -> {System.out.println(n*n);});
    }
}
