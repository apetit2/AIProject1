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
public class NameComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2){
        return node1.getNodeName().compareTo(node2.getNodeName());
    }
}
