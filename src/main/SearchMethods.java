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
            v[start.getIndex()] = true;
            for(Node node : nodes.get(start.getIndex()).getNeighbors()){
                if(!(v[node.getIndex()] == true)){
                    if(node.getNodeName().equals("G")){
                        System.out.println(node.getNodeName() + "\t\t" + toPrint);
                        System.out.println("Goal Reached!");
                        break;
                    }
                    dfs(node, nodes, v, toPrint);
                }
            }
    }
    
    public static Node dfsLimit(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint, int limit){       
        System.out.println(start + "\t\t" + toPrint);
        if(limit < 0){
            System.out.println("Limit was below 0");
            return null;
        }
        if((limit == 0) && (start.getNodeName().equals("G"))){
            return start;
        }
        if(limit == 0){
            if(!toPrint.isEmpty()){
                toPrint.remove(0);
            }
        }
        if(start.getNodeName().equals("S")){
            v[start.getIndex()] = true;
        }
        if (limit > 0){
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
            for (Node node : start.getNeighbors()){
                boolean[] newVisited = new boolean[nodes.size()];
                for (int i = 0; i < v.length; i++){
                    newVisited[i] = v[i];
                }
                if(newVisited[node.getIndex()] == false){
                    newVisited[node.getIndex()] = true;
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
        Node check = null;
        int index = 0;
        while(check == null){ 
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
    
    public static void uniformSearch(Node start, ArrayList<Node> nodes, ArrayList<ArrayList<Node>> toPrint){
        
    }
    
}
