import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import sun.jvm.hotspot.runtime.vmSymbols;

public class alienOrder {
    String alienOrder( String[] words){
        if( words == null || words.length == 0){
            return null;
        }
        if(words.length == 1){
            return words[0];
        }
        // 构建有向图：定义一个邻接链表 adjList，也可以用邻接矩阵

        Map< Charater ,List<Charater>> adList = new HashMap<>();

        for(int i = 0; i< words.length -1; ++i){
            String w1 = words[i], w2   = words[i+1];
            int n1 = w1.length(), n2 = w2.length();
            boolean found = false;
            for( int j = 0; j< Math.max(w1.length(), w2.length()); ++j){
                Character c1 = j< n1 ? w1.charAt(j) :null;
                Character c2 = j< n2 ? w2.charAt(j) :null;
                if(c1 !=null &&!adList.containsKey(c1)){
                    adList.put(c1, new ArrayList<Character>());
                }
                if(c2 !=null &&!adList.containsKey(c2)){
                    adList.put(c2, new ArrayList<Character>());
                }
                if( c1!=null && c2 !=null && c1!=c2 && !found){
                    adList.get(c1).add(c2);
                    found = true;
                }
            }
        }

        Set<Charater> visited = new HashSet<>();
        Set<Character> loop = new HashSet<>();
        Stack<Charater> stack = new Stack<>();

        for( Character key : adList){
            if(!visited.containsKey(key)){
                if(!topologicalSort(adList,key,visited,loop,stack))
                    return "";
            }
        }
        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();

    }

    boolean topologicalSort(Map<Character,List<Character>> adList,char u, Set<Character> visited,
    Set<Character> loop, Stack<Character> stack ) {
        visited.add(u);
        loop.add(u);
        if(adList.containsKey(u)){
            for(int i = 0; i< adList.get(u).size(); i++){
                char v = adList.get(u).get(i);

                if(loop.contains(v)){
                    return false;
                }
                if(!visited.contains(v)){
                    if(!topologicalSort(adList, v, visited, loop, stack)){
                        return false;
                    }
                }
            }
        }
        loop.remove(u);
        stack.push(u);
        return true;
    }
}