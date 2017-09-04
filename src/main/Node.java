/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author apand
 */
public class Node {
        private ArrayList<Node> neighbors;
        private ArrayList<Link> links;
        private String nodeName;
        private int index;
        
        public Node(String name) {
            this.neighbors = new ArrayList<>();
            this.links = new ArrayList<>();
            this.nodeName = name;
        }
        
        public ArrayList<Node> getNeighbors(){
            return this.neighbors;
        }
        
        public void setNeighbors(ArrayList<Node> nodes) {
            this.neighbors = nodes;
        }
        
        public ArrayList<Link> getLinks(){
            return this.links;
        }
        
        public void setLinks(ArrayList<Link> lks){
            this.links = lks;
        }
        
        public String getNodeName(){
            return this.nodeName;
        }
        
        public void addNeighbor(Node node){
            this.neighbors.add(node);
        }
        
        public void addLink(double distance, Node end){
            Link link = new Link(distance, this, end);
            this.links.add(link);
        }
        
        public int getIndex() {
            return this.index;
        }
        
        public void setIndex(int index){
            this.index = index;
        }
        
        public String toString(){
            return this.getNodeName();
        }
    }
