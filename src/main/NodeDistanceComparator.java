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
public class NodeDistanceComparator implements Comparator<ArrayList<Node>> {

    private static Map<String, Double> map; 
    public NodeDistanceComparator(Map<String, Double> map){
        NodeDistanceComparator.map = map;
    }
    
    @Override
    public int compare(ArrayList<Node> o1, ArrayList<Node> o2) {
        if(map.get(Arrays.toString(o1.toArray())) < map.get(Arrays.toString(o2.toArray()))) return -1;
        if(map.get(Arrays.toString(o1.toArray())) > map.get(Arrays.toString(o2.toArray()))) return 1;
        return o1.get(0).getNodeName().compareTo(o2.get(0).getNodeName());
    }
    
}
