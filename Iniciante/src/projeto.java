

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class projeto {
    public static void main(String[] args) {
        // Lista que permite repetição de "elementos"
        List<String>list=new ArrayList<>();
        list.add("Thallis");
        list.add("Thallis Vitor");
        list.add("Ana");
        list.add("Ana Sousa");
         list.add("Ana Sousa");
        System.out.println(list);

        //Lista que não permite repetição de "elementos"
        Set<String> setList=new HashSet<>();
        setList.add("Thallis");
        setList.add("Thallis");
        setList.add("Thallis");
        setList.add("Thallis");
        setList.add("Thallis");
        System.out.println(setList);
            
    }
    
}