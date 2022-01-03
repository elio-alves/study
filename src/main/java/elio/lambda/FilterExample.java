/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elio.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author elio_alves
 */
public class FilterExample {

    public static void main(String args[]) {
        List<Person> list = Arrays.asList(
                new Person("Elio", 40),
                 new Person("Daniela", 35),
                 new Person("Miguel", 7),
                 new Person("Iris", 12),
                 new Person("Joao", 10)
        );
        
        // filter < 18
        List<Person> lessThan18 = list.stream().filter(p -> p.idade<18).collect(Collectors.toList());
        lessThan18.forEach(p -> System.out.println(p.idade+" "+p.name));
        
        // white line
        System.out.println();
        
        // filter >= 18
        List<Person> moreEqualsThan18 = list.stream().filter(p -> p.idade>=18).collect(Collectors.toList());
        moreEqualsThan18.forEach(p -> System.out.println("Old guy: "+p.name));
    }
}
