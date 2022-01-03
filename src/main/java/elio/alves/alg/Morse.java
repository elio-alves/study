/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elio.alves.alg;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elio_alves
 */
public class Morse {
    static final Map<String,String> map = new LinkedHashMap<>();
    static {
        map.put(".", "E");
        map.put("-", "T");
        map.put("..", "I");
        map.put(".-", "A");
        map.put("-.", "N");
        map.put("--", "M");
        map.put("...", "S");
        map.put("..-", "U");
        map.put(".-.", "R");
        map.put(".--", "W");
        map.put("-..", "D");
        map.put("-.-", "K");
        map.put("--.", "G");
        map.put("---", "O");
    }
    
    public static List<String> possibilities(String signals) {
        List<String> listBuffer = new LinkedList<>();
        
        for(char c: signals.toCharArray()){
            List<String> tmpList = new LinkedList<>();
            
            
            if(listBuffer.isEmpty()){
                if(c=='.' || c=='-'){
                    tmpList.add(c+"");
                }else{
                    tmpList.add(".");
                    tmpList.add("-");
                }
            }else{
                for(String sl: listBuffer){
                    if(c=='.' || c=='-'){
                        tmpList.add(sl+c);
                    }else{
                        tmpList.add(sl+".");
                        tmpList.add(sl+"-");
                    }
                }
            }

            listBuffer = tmpList;
        }
        
        // result
        List<String> result = new LinkedList<>();
        for(String r: listBuffer){
            result.add(map.get(r));
        }
        
        return result;
    }
    
    public static void main(String args[]){
        show(possibilities("."));
        show(possibilities(".-"));
        show(possibilities("?"));
        show(possibilities("?."));
        show(possibilities(".?"));
        
    }
    
    public static void show(List<String> list){
        for(String s: list){
            System.out.println("  "+s);
        }
        System.out.println();
    }
}
