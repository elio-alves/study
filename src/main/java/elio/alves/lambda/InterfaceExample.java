/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elio.alves.lambda;

/**
 *
 * @author elio_alves
 */
public class InterfaceExample {

    public static void main(String args[]) {
        StringTest exclaim = (s) -> s + "!";
        StringTest ask = (s) -> s + "?";
        
        System.out.println(exclaim.a("Elio"));
        System.out.println(ask.a("Alves"));
    }
}

interface StringTest {

    String a(String srt);
}
