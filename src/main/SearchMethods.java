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
    
    public static void dfsLimit(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint, int limit, int at){
        System.out.println(start.getNodeName() + "\t\t" + toPrint);
        int index = 0;
        ArrayList<ArrayList<Node>> tmp = new ArrayList<>();
        if(at < limit){
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
        } else {
            toPrint.remove(0);
        }
        v[start.getIndex()] = true;
        for(Node node : nodes.get(start.getIndex()).getNeighbors()){
            //System.out.println(node.getNodeName());
            if(at < limit){
                if(!(v[node.getIndex()] == true)){
                
                    if(node.getNodeName().equals("G")){
                        System.out.println(node.getNodeName() + "\t\t" + toPrint);
                        System.out.println("Goal Reached!");
                        break;
                    } 
                
                    dfsLimit(node, nodes, v, toPrint, limit, at + 1);
                }
            }
        }
    }
    
    public static void bfs(Node start, ArrayList<Node> nodes, boolean[] v, ArrayList<ArrayList<Node>> toPrint){
        Queue<Node> queue = new LinkedList<>();
        v[start.getIndex()] = true;
        queue.add(start);
        while(!queue.isEmpty()){
            Node node = queue.remove();
            System.out.println(node);
            
            for(Node n : node.getNeighbors()){
                if(v[n.getIndex()] == false){
                    v[n.getIndex()] = true;
                    queue.add(n);
                }
            }
        }
       
    }
}
