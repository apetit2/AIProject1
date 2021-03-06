/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
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
        ArrayList<String> heuristics = new ArrayList<>();
        ArrayList<String[]> processedHeuristics = new ArrayList<>();
        
        //for reading in the file
        BufferedReader br = null;
        FileReader fr = null;
        if (args.length == 0){
            System.out.println("Need to enter a file name");
            return;
        }
        
        //assuming this is a filename
        String FILENAME = args[0];
        
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            
            String sCurrentLine;
            
            //go through the buffer and make an array of nodes and their neighbors + distances
            while((sCurrentLine= br.readLine()) != null){
                char[] array = sCurrentLine.toCharArray();
                if(array[0] == '#'){ //do not any more strings, next part is heuristics
                    break;
                }
                strings.add(sCurrentLine);
            }
            
            //need a new while loop to finish reading in the heuristics
            /**TODO 
             * Modify Node and create a while loop to go through the rest of the buffer to support heuristics for astar and so forth
             */
            while((sCurrentLine = br.readLine()) != null){
                heuristics.add(sCurrentLine);
            }
            
            
            for(String heuristic : heuristics){
                String[] str = heuristic.split("\\s+");
                processedHeuristics.add(str);
            }
            
            //set up the graph
            int index = 0;
            for(String string : strings){
                //break the string up into 3 arguments (nodeName, nodeName, and distance)
                String[] str = string.split("\\s+");
                Node start = new Node(str[0]);
                Node end = new Node(str[1]);
                double distance = Double.parseDouble(str[2]);
                
                
                
                //these are used to mark the index that node shows up in the initial array
                //we will use this later for a visited array
                int startExists = -1, endExists = -1;
                
                for(int i = 0; i < processedHeuristics.size(); i++){
                    if(start.getNodeName().equals(processedHeuristics.get(i)[0])){
                        start.setHeuristic(Double.parseDouble(processedHeuristics.get(i)[1]));
                    }
                        
                    if(end.getNodeName().equals(processedHeuristics.get(i)[0])){
                        end.setHeuristic(Double.parseDouble(processedHeuristics.get(i)[1]));
                    }
                }
                
                //if nodes is not empty we should update the existing nodes if the start or end exist
                if(!nodes.isEmpty()){
                    for (int i = 0; i < nodes.size(); i++){ //find the start/end nodes for the given pair and update them if they exist
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
                    //this index value is used to determine where the node is in the nodes array
                    index = nodes.size();
                    
                    if(startExists == -1){ //start node does not initially exist so it will be at the next position in the array
                        start.setIndex(index);
                        index++;
                    } else if(startExists >= 0){ //start node does exist so we must keep the index at the same position
                        start.setIndex(startExists);
                    }
                    
                    if(endExists == -1){ //same thing as above but for end node
                        end.setIndex(index);
                    } else if(endExists >= 0){
                        end.setIndex(endExists);
                    }
                    
                    //add the end node to the start nodes neighbors
                    start.addNeighbor(end);
                    start.addLink(distance, end); //set distance
                    
                    //add the start node to the end node neighbors
                    end.addNeighbor(start);
                    end.addLink(distance, start); //set distance
                    
                    if(startExists == -1){ //if start does not exist add it to the nodes array
                        nodes.add(start);
                    } else if (startExists >= 0){ //else just update the existing node
                        nodes.get(startExists).setLinks(start.getLinks());
                        //sort the neighbor nodes so that the first one is the first to appear in the alphabet and so forth
                        Collections.sort(start.getNeighbors(), new NameComparator()); 
                        Collections.sort(start.getLinks(), new DistanceComparator());
                        nodes.get(startExists).setNeighbors(start.getNeighbors());
                        
                    }
                    if(endExists == -1){ //same thing just for end node
                        nodes.add(end);
                    } else if (endExists >= 0){ //...
                        nodes.get(endExists).setLinks(end.getLinks());
                        //sort the neighbor nodes so that the first one is the first to appear in the alphabet and so forth
                        Collections.sort(end.getNeighbors(), new NameComparator());
                        Collections.sort(start.getLinks(), new DistanceComparator());
                        nodes.get(endExists).setNeighbors(end.getNeighbors());
                    }
                } else { //nodes array has nothing in it so add the nodes to it
                    
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
            
            //get the start node so we know where to begin
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
            SearchMethods.General_Search(nodes, "DFS");
            System.out.println("\n\n");
            
            //Breadth First Search 
            System.out.println("Breadth First Search");
            System.out.println("Expanded \tQueue");
            SearchMethods.General_Search(nodes, "BFS");
            System.out.println("\n\n");
            
            //Depth-limited Search
            System.out.println("Depth-limited Search (Limit = 2)");
            System.out.println("Expanded \tQueue");
            SearchMethods.General_Search(nodes, "DFS-L");
            System.out.println("\n\n");
            
            //Iterative Deepening Search
            System.out.println("Iterative Deepening Search");
            SearchMethods.General_Search(nodes, "IDS");
            System.out.println("\n\n");
            
            //Uniform Search
            System.out.println("Uniform Search");
            System.out.println("Expanded \tQueue");
            SearchMethods.General_Search(nodes, "Uniform");
            System.out.println("\n\n");
            
            //Greedy Search
            System.out.println("Greedy Search");
            System.out.println("Expanded \tQueue");
            SearchMethods.General_Search(nodes, "Greedy");
            System.out.println("\n\n");
            
            //A*
            System.out.println("A* Search");
            System.out.println("Expanded \tQueue");
            SearchMethods.General_Search(nodes, "AStar");
            System.out.println("\n\n");
            
            //hill climb
            System.out.println("Hill Climb");
            System.out.println("Expanded \tQueue");
            SearchMethods.General_Search(nodes, "Hill");
            System.out.println("\n\n");
            
            //Beam Search
            System.out.println("Beam Search (w = 2)");
            System.out.println("Expanded \tQueue");
            SearchMethods.General_Search(nodes, "Beam");
            System.out.println("\n\n");
            
        } catch (Exception e){
            System.out.println("Invalid filename entered, please try to run the program again with a valid txt file");
            System.out.println(e.getMessage());
        }
    }
}
