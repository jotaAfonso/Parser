package utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {

	//private Map<String, LinkedHashSet<String>> map = new HashMap<String, LinkedHashSet<String>>();
	private Map<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();

	public void addEdge(String node1, String node2, String part) {
		node1 = UtilsParser.removeParenthesisFromString(node1);
		node2 = UtilsParser.removeParenthesisFromString(node2);
		HashMap<String, String> adjacent = map.get(node1);
		if (adjacent == null) {
			adjacent = new HashMap<String, String>();
			map.put(node1, adjacent);
		}
		adjacent.put(node2, part);
	}

	public boolean isConnected(String node1, String node2) {
		HashMap<String, String> adjacent = map.get(node1);
		if (adjacent == null) {
			return false;
		}
		return adjacent.containsKey(node2);
	}

	public LinkedList<String> adjacentNodes(String last) {
		HashMap<String, String> adjacent = map.get(last);
		if (adjacent == null) {
			return new LinkedList<String>();
		}
		return new LinkedList<String>(adjacent.keySet());
	}

	public HashMap<String, String> adjacentNodesPart(String last) {
		HashMap<String, String> adjacent = map.get(last);
		if (adjacent == null) {
			return new HashMap<String, String>();
		}
		return adjacent;
	}
}