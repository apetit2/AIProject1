/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author apand
 */
public class SearchMethods {
    
    public static void dfs(ArrayList<ArrayList<Node>> expanded, ArrayList<ArrayList<Node>> queue, boolean[] v){
        for(int i = 0; i < expanded.size(); i++){ 
            if (v[expanded.get(i).get(0).getIndex()] == false){ //if we haven't already visited the node we add it to the queue
                queue.add(0, expanded.get(i));
            }
        }
    }
    
    public static void dfsLimit(ArrayList<ArrayList<Node>> expanded, ArrayList<ArrayList<Node>> queue, boolean[] v, int limit){
        //if limit was ever below 0 we have severe problem
        
        if(limit < 0){
            System.out.println("Limit was below 0");
            return;
        }
        
        //we know that we don't need to run the whole thing because we only look at S
        if(limit == 0){
            Node start = new Node("S");
            ArrayList<Node> nodes = new ArrayList<>();
            nodes.add(start);
            queue.add(nodes);
            return;
        }
        
        //if we look at the child nodes and the child is visited and is the only child we don't need to worry about it
        if((v[expanded.get(0).get(0).getIndex()] == true) && (expanded.size() == 1)){
            v[expanded.get(0).get(1).getIndex()] = false;
            return;
        }
        
        //we have exceeded the limit
        if(expanded.get(0).size() == limit + 1){
            
            if(queue.isEmpty()){
                return;
            }
            
            //don't add to list and remove visited from the list
            if(expanded.get(0).size() > queue.get(0).size()){
                int diff = expanded.get(0).size() - queue.get(0).size();
                int count = 1;
                while(count <= diff){
                    v[expanded.get(0).get(count).getIndex()] = false;
                    count++;
                }
            } 
            return;
        }
        
        //dfs
        for(int i = 0; i < expanded.size(); i++){
            if(v[expanded.get(i).get(0).getIndex()] == false){
                queue.add(0, expanded.get(i));
            }
        }
    }
    
    public static ArrayList<ArrayList<Node>> iterativeDeepening(ArrayList<ArrayList<Node>> expanded, ArrayList<ArrayList<Node>> queue, boolean[] v, int index, Node start){
        //run dfs limit to start
        dfsLimit(expanded, queue, v, index + 1);
        if((index + 1) == 0){
            return new ArrayList<>();
        }
        
        //keep going until we have emptied the entire queue
        while(!queue.isEmpty()){
            
            //basically rerun general search here with the current queue
            //could honestly probably just used mutual recursion with general search here
            ArrayList<Node> currentList = queue.get(0);
            System.out.println(currentList.get(0).getNodeName() + "\t\t" + queue);
            Node current = currentList.get(0);
            if(current.getNodeName().equals("G")){
                break;
            }
            queue.remove(0);
            v[current.getIndex()] = true;
            if(currentList.size() > (index + 1)){
                if(queue.isEmpty()){
                    break;
                }
            } else {
                if(!currentList.isEmpty()){
                    expanded = expand(currentList);
                    
                    dfsLimit(expanded, queue, v, index + 1);
                }
            }
        }
        if(!queue.isEmpty()){
            return queue;
        }
        return new ArrayList<>();
    }
    
    public static void bfs(ArrayList<ArrayList<Node>> expanded, ArrayList<ArrayList<Node>> queue, boolean[] v){
        //tmp array to keep track of all visited nodes so far
        ArrayList<ArrayList<Node>> tmp = new ArrayList<>();
        for(int i = expanded.size() - 1; i >= 0; i--){
            
            //get the first node in the list we are looking at
            Node t = expanded.get(i).get(0);
            
            //this is needed because it breaks sublist if we keep it going
            if(expanded.get(i).size() == 2){
                tmp.add(0, expanded.get(i));
                continue;
            }
            
            //make a sublist of all the visited nodes
            List<Node> ts = expanded.get(i).subList(1, expanded.get(i).size());
            //if there happens to be no visited nodes, well then we can add this to the queue
            if(ts.isEmpty()){
                tmp.add(0, expanded.get(i));
                continue;
            }

            //temporarily add the child to the queue
            tmp.add(expanded.get(i));
            for(int j = 0; j < ts.size(); j++){
                //if the child is not the same as another child in the expanded list, then we don't remove
                //if it is, we remove from the temporary array
                if (t.getNodeName().equals(ts.get(j).getNodeName())){
                    tmp.remove(tmp.size() - 1);
                    break;
                }
            }
            
            //sort the array 
            if(tmp.size() > 1){
                Collections.sort(tmp, new ArrayListComparator());
            }

        }
        
        if(!tmp.isEmpty()){
            for(int i = tmp.size() - 1; i >= 0; i--){
                queue.add(tmp.get(i));
            }
        }
    }
    
    public static void uniformSearch(){
        
    }
    
    public static void greedySearch(){
        
    }
    
    public static void aStar(){
        
    }
    
    public static void beamSearch(){
        
    }
    
    public static void hillClimb(){
    
    }
    
    public static String General_Search(ArrayList<Node> problem, String searchMethod){
        
        //get the start node so we know where to begin
        Node start = null;
        for(int i = 0; i < problem.size(); i++){
            if(problem.get(i).getNodeName().equals("S")){
                start = problem.get(i);
                break;
            }
        }

        //initialize stuff
        boolean[] v = new boolean[problem.size()];//to check if visited or not
        int index = 0; //for iterative deepening
        

        //make the queue and the start node to it
        ArrayList<ArrayList<Node>> queue = new ArrayList<>();
        ArrayList<Node> tmp = new ArrayList<>();
        tmp.add(start);
        queue.add(tmp);
        
        //start doing the methods
        while(!queue.isEmpty()){
            ArrayList<ArrayList<Node>> tmpQueue = new ArrayList(queue); //temporary so we can print out
            ArrayList<Node> currentList = queue.get(0); //get the first arraylist of nodes
            queue.remove(0); //remove the first element of the queue
            Node current = currentList.get(0); //new current node
            //print out the step we are one
            if(searchMethod.equals("IDS")){
                System.out.println("L = " + index);
                System.out.println("Expanded \tQueue");
            }
            System.out.println(current.getNodeName() + "\t\t" + tmpQueue);
            v[current.getIndex()] = true; //we have visited this node
            //if the current node is the goal node we break
            if(current.getNodeName().equals("G")){
                System.out.println("Goal Reached!");
                return "Goal Reached!";
            }
            //expand the children of the current node we are testing
            ArrayList<ArrayList<Node>> expanded = expand(currentList);
            //run the search methods
            switch(searchMethod){
                case "DFS" : dfs(expanded, queue, v);
                    break;
                case "DFS-L" : dfsLimit(expanded, queue, v, 3);
                    break;
                case "IDS" : 
                    queue = iterativeDeepening(expanded, queue, v, index, start);
                    index++;                
                    Arrays.fill(v, false);
                    if(queue.isEmpty()){
                        System.out.println("\n");
                        queue.add(tmp);
                    } else {
                        System.out.println("Goal Reached!");
                        return "Goal Reached!";
                    }
                    break;
                case "BFS" : bfs(expanded, queue, v);
                    break;
                case "Uniform" :
                    break;
                case "Greedy":
                    break;
                case "AStar":
                    break;
                case "Beam":
                    break;
                case "Hill":
                    break;
                default: return "Failed - invalid method entry";
            }
            
        }
        
        return "Failed";
        
    };
    
    public static ArrayList<ArrayList<Node>> expand(ArrayList<Node> currentList){
        int index = 0;
        ArrayList<ArrayList<Node>> tmp = new ArrayList<>();
        while(index < currentList.get(0).getNeighbors().size()){
            ArrayList<Node> t = new ArrayList(currentList);
            t.add(0, currentList.get(0).getNeighbors().get(index));
            tmp.add(0, t);
            index++;
        }
        
        return tmp;
    } 
    
}
