/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Comparator;

/**
 *
 * @author apand
 */
public class DistanceComparator implements Comparator<Link> {
    @Override 
    public int compare(Link link1, Link link2){
        if(link1.getDistance() < link2.getDistance()) return -1;
        if(link1.getDistance() > link2.getDistance()) return 1;
        return 0;
    }
}
