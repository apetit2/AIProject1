/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author apand
 */
public class SearchMethods {
    public static void dfs(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint){
            System.out.println(start.getNodeName() + "\t\t" + toPrint);
            
            //this code is necessary to create the queue to print, this code works for the next node we will visit
            int index = 0;
            ArrayList<ArrayList<Node>> tmp = new ArrayList<>();
            while(index < start.getNeighbors().size()){ //go through all the neighbor nodes of the current node to see which ones will be queued 
                ArrayList<Node> t = new ArrayList(toPrint.get(0));
                //only add the node into the queue if we have not already visited it
                if(v[start.getNeighbors().get(index).getIndex()] != true){
                    t.add(0, start.getNeighbors().get(index));
                    tmp.add(t);
                } 
                index++;
            }
            toPrint.remove(0); //remove the first node because we have expanded it
            for(int i = tmp.size() - 1; i >= 0; i--){ //go through the list of nodes we created in the prior step
                toPrint.add(0, tmp.get(i)); //add the node back to the toPrint list 
            }
            
            //mark this node as though it was visited
            v[start.getIndex()] = true;
            //go through all of the neighbor nodes and expand them in order that they appear
            for(Node node : nodes.get(start.getIndex()).getNeighbors()){
                //only if that node had not been visited do we expand it to look for the goal
                if(!(v[node.getIndex()] == true)){
                    if(node.getNodeName().equals("G")){ //if the node we expanded is the goal, break the loop and print that we got the goal
                        System.out.println(node.getNodeName() + "\t\t" + toPrint);
                        System.out.println("Goal Reached!");
                        break;
                    }
                    dfs(node, nodes, v, toPrint); //run the program on the next node
                }
            }
    }
    
    public static Node dfsLimit(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint, int limit){       
        System.out.println(start + "\t\t" + toPrint);
        if(limit < 0){ //if limit is lower that 0, this program makes no sense, so just break it
            System.out.println("Limit was below 0");
            return null;
        }
        //if the current node is the goal node, just return it because we have the solution
        if((limit == 0) && (start.getNodeName().equals("G"))){
            return start;
        }
        //if we hit the farthest place we can go in the tree, we should remove the first element in the queue because we can not expand it
        if(limit == 0){
            if(!toPrint.isEmpty()){
                toPrint.remove(0);
            }
        }
        
        //if this is the start node, we should let the program know that we expanded it
        if(start.getNodeName().equals("S")){
            v[start.getIndex()] = true;
        }
        
        //this is the case where we would have to expand neighbor nodes if there are any
        if (limit > 0){
            
            //sets up the queue for neighbor nodes - refer to DFS
            int index = 0;
            ArrayList<ArrayList<Node>> tmp = new ArrayList<>();
            while(index < start.getNeighbors().size()){
                ArrayList<Node> t = new ArrayList(toPrint.get(0));
                if(v[start.getNeighbors().get(index).getIndex()] != true){
                    t.add(0, start.getNeighbors().get(index));
                    tmp.add(t);
                } 
                index++;
            }
            toPrint.remove(0);
            for(int i = tmp.size() - 1; i >= 0; i--){
                toPrint.add(0, tmp.get(i));
            }
            
            //go through the list of neighbor nodes
            for (Node node : start.getNeighbors()){
                boolean[] newVisited = new boolean[nodes.size()]; //we want a new visited array everytime
                for (int i = 0; i < v.length; i++){
                    newVisited[i] = v[i];
                }
                if(newVisited[node.getIndex()] == false){ //this might be unnecessary, but currently works with it - so didn't bother taking it out
                    newVisited[node.getIndex()] = true; //basically the same as DFS
                    Node found = dfsLimit(node, nodes, newVisited, toPrint, limit - 1);
                    if (found != null){
                        return found;
                    }
                }
            }
        }
        return null;
    }
    
    public static void iterativeDeeping(Node start, ArrayList<Node> nodes, ArrayList<ArrayList<Node>> toPrint){
        Node check = null; //if dfsLimit found solution it should return a node
        int index = 0; //keep incrementing the index until iterativeDeeping finds a solution
        while(check == null){ //dfsLimit continues to return null, we continue to loop
            //initialized for each new call of dfsLimit
            boolean[] v = new boolean[nodes.size()];
            toPrint = new ArrayList<>();
            ArrayList<Node> tmp = new ArrayList<>();
            tmp.add(start);
            toPrint.add(tmp);
            System.out.println("L = " + index);
            System.out.println("Expanded \tQueue");
            check = SearchMethods.dfsLimit(start, nodes, v, toPrint, index);
            index++;
            if (check == null){
                System.out.println("\n");
            }
        }
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
    
    public static void uniformSearch(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint){
        
    }
    
    public static void greedySearch(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint){
        
    }
    
    public static void aStar(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint){
        
    }
    
    public static void beamSearch(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint){
        
    }
    
}
