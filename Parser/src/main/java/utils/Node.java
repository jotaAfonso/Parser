package utils;

import java.util.*;

public class Node {

	private String name;
	private List<Node> shortestPath = new LinkedList<Node>();
	private Integer distance = Integer.MAX_VALUE;

	Map<Node, Integer> adjacentNodes = new HashMap<>();

	public void addDestination(Node destination, int distance) {
		adjacentNodes.put(destination, distance);
	}
	
	public Map<Node, Integer> getAdjacentNodes() {
		return this.adjacentNodes;
	}

	public Node(String name) {
		this.name = name;
	}
	
	public void setDistance(int number) {
		this.distance = number;
	}
		
	public int getDistance() {
		return this.distance;
	}
	
	public void setShortestPath(LinkedList<Node> path) {
		this.shortestPath = path;
	}
	
	public List<Node> getShortestPath(){
		
		return this.shortestPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}