/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author apand
 */
public class MainDriver {
    
    public static void main(String [] args){ 
        
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;
        if (args.length == 0){
            System.out.println("Need to enter a file name");
            return;
        }
        String FILENAME = args[0];
        
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            
            String sCurrentLine;
            
            while((sCurrentLine= br.readLine()) != null){
                char[] array = sCurrentLine.toCharArray();
                if(array[0] == '#'){
                    break;
                }
                strings.add(sCurrentLine);
            }
            
            //set up the graph
            int index = 0;
            for(String string : strings){
                String[] str = string.split("\\s+");
                Node start = new Node(str[0]);
                Node end = new Node(str[1]);
                double distance = Double.parseDouble(str[2]);
                int startExists = -1, endExists = -1;
                if(!nodes.isEmpty()){
                    for (int i = 0; i < nodes.size(); i++){
                        if (nodes.get(i).getNodeName().equals(start.getNodeName())){
                            start.setNeighbors(nodes.get(i).getNeighbors());
                            start.setLinks(nodes.get(i).getLinks());
                            startExists = i;
                        } 
                        if (nodes.get(i).getNodeName().equals(end.getNodeName())){
                            end.setNeighbors(nodes.get(i).getNeighbors());
                            end.setLinks(nodes.get(i).getLinks());
                            endExists = i;
                        }     
                    }
                    index = nodes.size();
                    
                    if(startExists == -1){
                        start.setIndex(index);
                        index++;
                    } else if(startExists >= 0){
                        start.setIndex(startExists);
                    }
                    
                    if(endExists == -1){
                        end.setIndex(index);
                    } else if(endExists >= 0){
                        end.setIndex(endExists);
                    }
                    
                    start.addNeighbor(end);
                    start.addLink(distance, end);
                    
                    end.addNeighbor(start);
                    end.addLink(distance, start);
                    
                    if(startExists == -1){
                        nodes.add(start);
                    } else if (startExists >= 0){
                        nodes.get(startExists).setLinks(start.getLinks());
                        Collections.sort(start.getNeighbors(), new NameComparator());
                        nodes.get(startExists).setNeighbors(start.getNeighbors());
                        
                    }
                    if(endExists == -1){
                        end.setIndex(index);
                        nodes.add(end);
                        index++;
                    } else if (endExists >= 0){
                        nodes.get(endExists).setLinks(end.getLinks());
                        Collections.sort(end.getNeighbors(), new NameComparator());
                        nodes.get(endExists).setNeighbors(end.getNeighbors());
                    }
                } else {
                    
                    start.setIndex(index);
                    index++;
                    
                    end.setIndex(index);
                    index++;
                    
                    end.addNeighbor(start);
                    end.addLink(distance, start);
                    
                    start.addNeighbor(end);
                    start.addLink(distance, end);
                    
                    
                    nodes.add(start);
                    nodes.add(end);
                }
            }
            
            //get the start node
            Node start = null;
            for(int i = 0; i < nodes.size(); i++){
                if(nodes.get(i).getNodeName().equals("S")){
                    start = nodes.get(i);
                    break;
                }
            }

            //Depth First Search
            System.out.println("Depth First");
            System.out.println("Expanded \tQueue");
            boolean[] array = new boolean[nodes.size()];
            ArrayList<ArrayList<Node>> toPrint = new ArrayList<>();
            ArrayList<Node> tmp = new ArrayList<>();
            tmp.add(start);
            toPrint.add(tmp);
            SearchMethods.dfs(start, nodes, array, toPrint);
            System.out.println("\n\n");
            
            //Breadth First Search -- needs work, kind of right, printing out is wrong because of visited
            System.out.println("Breadth First Search");
            System.out.println("Expanded \tQueue");
            array = new boolean[nodes.size()];
            toPrint = new ArrayList<>();
            toPrint.add(tmp);
            SearchMethods.bfs(start, nodes, array, toPrint);
            System.out.println("\n\n");
            
            //Depth-limited Search
            System.out.println("Depth-limited Search (Limit = 2)");
            System.out.println("Expanded \tQueue");
            array = new boolean[nodes.size()];
            toPrint = new ArrayList<>();
            toPrint.add(tmp);
            SearchMethods.dfsLimit(start, nodes, array, toPrint, 2, 0);
            System.out.println("\n\n");
            
            //Iterative Deepening Search
            System.out.println("Iterative Deepening Search");
            System.out.println("Expanded \tQueue");
            System.out.println("\n\n");
            
            //Uniform Search
            System.out.println("Uniform Search");
            System.out.println("Expanded \tQueue");
            System.out.println("\n\n");
            
            //Greedy Search
            System.out.println("Greedy Search");
            System.out.println("Expanded \tQueue");
            System.out.println("\n\n");
            
            //A*
            System.out.println("A* Search");
            System.out.println("Expanded \tQueue");
            System.out.println("\n\n");
            
            //Beam Search
            System.out.println("Beam Search");
            System.out.println("Expanded \tQueue");
            System.out.println("\n\n");
            
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
