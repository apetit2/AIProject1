/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author apand
 */
public class AStarComparator implements Comparator<ArrayList<Node>>{
    
    private static Map<String, Double> map; 
    public AStarComparator(Map<String, Double> map){
        AStarComparator.map = map;
    }
    @Override
    public int compare(ArrayList<Node> o1, ArrayList<Node> o2) {
        if(map.get(Arrays.toString(o1.toArray())) + o1.get(0).getHeuristic() < map.get(Arrays.toString(o2.toArray())) + o2.get(0).getHeuristic()) return -1;
        if(map.get(Arrays.toString(o1.toArray())) + o1.get(0).getHeuristic() > map.get(Arrays.toString(o2.toArray())) + o2.get(0).getHeuristic()) return 1;
        return o1.get(0).getNodeName().compareTo(o2.get(0).getNodeName());
    }
    
}
