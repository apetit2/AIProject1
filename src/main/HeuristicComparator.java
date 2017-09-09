/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author apand
 */
public class HeuristicComparator implements Comparator<ArrayList<Node>> {

    @Override
    public int compare(ArrayList<Node> o1, ArrayList<Node> o2) {
        if(o1.get(0).getHeuristic() < o2.get(0).getHeuristic()) return -1;
        if(o1.get(0).getHeuristic() > o2.get(0).getHeuristic()) return 1;
        return 0;
    }
    
}
