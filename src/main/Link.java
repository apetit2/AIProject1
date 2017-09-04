/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author apand
 */
public class Link {
        private double distance;
        private Node start;
        private Node end;
        
        public Link(double distance, Node start, Node end){
            this.distance = distance;
            this.start = start;
            this.end = end;
        }
        
        public double getDistance() {
            return this.distance;
        }
        
        public Node getStart(){
            return this.start;
        }
        
        public Node getEnd() {
            return this.end;
        }
    }
