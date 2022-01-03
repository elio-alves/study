/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elio.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author elio_alves
 */
public class SortingExample {
    
    public static void main(String args[]) {
        List<Person> list = Arrays.asList(
                new Person("Eliane", 44),
                new Person("Evandro", 42),
                new Person("Elio", 40)
        );

        //List by name
        Collections.sort(list, (Person p1, Person p2) -> {return p1.name.compareTo(p2.name);});

        // List
        System.out.println("List by name");
        list.forEach(n -> {
            System.out.println(n.idade + " " + n.name);
        });
        
        // white line
        System.out.println();
        
        // List by age
        System.out.println("List by age");
        Collections.sort(list, (Person p1, Person p2) -> {return p1.idade.compareTo(p2.idade);});
        list.forEach(n -> {
            System.out.println(n.idade + " " + n.name);
        });
    }
}

class Person {
    
    String name;
    Integer idade;
    
    public Person(String name, Integer idade) {
        this.name = name;
        this.idade = idade;
    }
}
