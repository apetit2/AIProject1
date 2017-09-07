/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
            queue = new ArrayList<>();
            return;
        }
        
        //we have exceeded the limit
        if(expanded.get(0).size() > limit + 1){
            v[expanded.get(0).get(1).getIndex() - 1] = false; //we falsely marked as visited, should remove that now
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
        int limit = index;
        while(limit != 0){
            dfsLimit(expanded, queue, v, index);
            ArrayList<Node> currentList = queue.get(0);
            queue.remove(0);
            if(queue.get(0).get(0).getNodeName().equals("G")){
                    break;
            }
            if(currentList.size() > limit){
                queue.remove(0);
                System.out.println(queue);
                if(queue.isEmpty()){
                    break;
                }
            }
            System.out.println(currentList.get(0).getNodeName() + "\t\t" + queue);
          
            //System.out.println(queue.get(0).get(0).getNodeName() + "\t\t" + queue);
            
            expanded = expand(currentList);
            
            limit--;
        }
        if(!queue.isEmpty()){
            if(queue.get(0).get(0).getNodeName().equals("G")){
                return queue;
            } 
        } 
        System.out.println("\n");
        return new ArrayList<>();
    }
    
    public static void bfs(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint){
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            Node node = queue.remove();
            //v[node.getIndex()] = true;
            System.out.println(node);
            
            for(Node n : node.getNeighbors()){
                if(v[n.getIndex()] == false){
                    v[n.getIndex()] = true;
                    queue.add(n);
                }
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
    
    public static void hillClimb(){}
    
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
        int index = 1; //for iterative deepening
        

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
                case "DFS-L" : dfsLimit(expanded, queue, v, 2);
                    break;
                case "IDS" : 
                    queue = iterativeDeepening(expanded, queue, v, index, start);
                    index++;                
                    Arrays.fill(v, false);
                    if(!queue.get(0).get(0).getNodeName().equals("G")){
                        System.out.println("got here");
                        queue = new ArrayList<>();
                        //ArrayList<Node> tmp = new ArrayList<>();
                        //tmp.add(start);
                        queue.add(tmp);
                    }
                    
                    System.out.println(queue);
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
