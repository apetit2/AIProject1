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
public class ArrayListComparator implements Comparator<ArrayList<Node>> {
    @Override
    public int compare(ArrayList<Node> a1, ArrayList<Node> a2){
        return a1.get(0).getNodeName().compareTo(a2.get(0).getNodeName())/-1;
    }
}
