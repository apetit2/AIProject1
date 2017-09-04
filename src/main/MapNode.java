package main;

import java.util.ArrayList;

public class MapNode {

	private double xPos;
	private double yPos;
	private double zPos;

	private double xFeet;
	private double yFeet;
	private double zFeet;

	private String nodeID;
	private ArrayList<MapNode> neighbors;
	private double fScore;
	private double gScore;
	private double hScore;
	private MapNode cameFrom;

	// default constructor
	public MapNode(){
		fScore = -1;
		gScore = -1;
		hScore = -1;
		cameFrom = null;
	}
        
	public double aStarHeuristic(MapNode toNode) {
		double dist = (double) Math.sqrt(Math.pow((xFeet - toNode.getXFeet()),2) + Math.pow(yFeet - toNode.getYFeet(),2)) + Math.abs(zFeet - toNode.getZFeet());

		return dist;
	}

	public void deleteNeighborLink(MapNode node){
		this.neighbors.remove(node);
	}

	public int calcDistance(MapNode toNode) {
		double distance = 0;
		double distanceXLeg = (toNode.getXFeet() - this.getXFeet());
		double distanceYLeg = (toNode.getYFeet() - this.getYFeet());

		distance = (Math.sqrt((distanceXLeg * distanceXLeg) + (distanceYLeg * distanceYLeg)));
		distance = Math.round(distance);
		return (int)distance;
	}

	public ArrayList<MapNode> getNeighbors() {
		return neighbors;
	}

	public double getXPos() {
		return xPos;
	}
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}

	public double getZPos() {
		return zPos;
	}
	public void setZPos(double zPos) {
		this.zPos = zPos;
	}
	public void setxPos(double pos) {
		xPos = pos;
	}
	public void setyPos(double pos) {
		yPos = pos;
	}

	public String getID(){
		return nodeID;
	}

	public MapNode getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(MapNode cameFrom){
		this.cameFrom = cameFrom;		
	}

	public String getNodeID(){
		return this.nodeID;
	}
	public void setNodeID(String id){
		this.nodeID = id;
	}

	public void setGScore(double distance) {
		gScore = distance;
	}

	public void setHScore(double distance) {
		hScore = distance;	
	}

	public double getGScore() {
		return gScore;
	}

	public double getFScore() {
		return fScore;
	}

	public void calcFScore() {
		fScore = gScore + hScore;		
	}

	public void setXFeet(double xFeet){
		this.xFeet = xFeet;
	}
	public void setYFeet(double yFeet){
		this.yFeet = yFeet;
	}
	public double getXFeet(){
		return xFeet;
	}
	public double getYFeet(){
		return yFeet;
	}
	public void setZFeet(double zHeight){
		zFeet = zHeight;
	}
	public double getZFeet(){
		return zFeet;
	}

	public void removeNeighbors() {

		this.neighbors = new ArrayList<>();
		
	}

}
