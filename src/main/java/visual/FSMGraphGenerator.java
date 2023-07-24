package visual;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Constants.*;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Class FSMGraphGenerator.
 */
public class FSMGraphGenerator {
	
    /**
     * Generate FSM graph.
     *
     * @param fsmTextJson - string of a JSON file of a FSM
     * @return the graph
     */
    public static Graph generateFSMGraph(String fsmTextJson) {
        JSONObject fsm = new JSONObject(fsmTextJson);

        JSONArray states = fsm.getJSONArray(JSON_STATES);
        String initialState = fsm.getString(JSON_INITIAL_STATE);
        JSONArray finalStates = fsm.getJSONArray(JSON_END_STATES);
        JSONArray transitions = fsm.getJSONArray(JSON_TRANSITIONS);
        
        // Create a directed graph
        Graph graph = new MultiGraph("FSMGraph");

        // Add states as nodes
        for (int i = 0; i < states.length(); i++) {
        	String state = states.getString(i);
        	addNode(state, graph, initialState, finalStates);
        }
    	addNode("_", graph, initialState, finalStates);

    	// Add transitions as edges
    	addEdges(transitions, graph);

        return graph;
    }
    
    /**
     * Adds the edges to the graph.
     *
     * @param transitions - transitions
     * @param g - graph
     */
    private static void addEdges(JSONArray transitions, Graph g) {
    	// Add transitions as edges
        for (int i = 0; i < transitions.length(); i++) {
            JSONObject transition = transitions.getJSONObject(i);
            String from = transition.getString(JSON_FROM);
            String to = transition.getString(JSON_TO);
            String action = "";
            if(!transition.isNull(JSON_INTERNAL)) {
            	if(transition.getBoolean(JSON_INTERNAL))
            		action = "-";
            	else
            		action = ".";
            }
            action = action.concat(transition.getString(JSON_LABEL));
           	Edge edge = g.addEdge(from.concat(action).concat(to), from, to, true);
           	edge.setAttribute("ui.label", action); 
            edge.setAttribute("ui.style", "text-size: 20px;");
        }
    }
    
    /**
     * Adds the node to the graph.
     *
     * @param state - state
     * @param g - graph
     * @param iState - initial state
     * @param fStates - final states
     */
    private static void addNode(String state, Graph g, String iState, JSONArray fStates) {
    	
        Node node = g.addNode(state);
        Map<String, Boolean> nodeAttributes = new HashMap<>();

        if (iState.equals(state)) {
            nodeAttributes.put("initial", true);
        }
        if (fStates.toList().contains(state)) {
            nodeAttributes.put("final", true);
        }

        Pattern pattern = Pattern.compile("I(\\d+)");
        Matcher matcher = pattern.matcher(state);
        if (matcher.find()) {
            nodeAttributes.put("external", true);
        }

        if (state.equals("_")) {
            nodeAttributes.put("open", true);
        }

        if (!iState.equals(state) && !fStates.toList().contains(state) &&
                !nodeAttributes.containsKey("external") && !nodeAttributes.containsKey("open")) {
            nodeAttributes.put("normal", true);
        }

        node.setAttribute("ui.class", getNodeClass(nodeAttributes));
        node.setAttribute("ui.label", state);
    }
    
    /**
     * Gets the color RGB.
     *
     * @param color - color
     * @return the color RGB
     */
    private static String getColorRGB(Color color) {
	    // Get RGB values
	    int rgb = color.getRGB();
	    int r = (rgb >> 16) & 0xFF;
	    int g = (rgb >> 8) & 0xFF;
	    int b = rgb & 0xFF;
	    return String.format("#%02X%02X%02X", r, g, b);
    }
    

    /**
     * Draws FSM graph.
     *
     * @param graph - graph
     */
    public static void drawFSMGraph(Graph graph) {
    	String formating = String.format(
				"node { fill-color: white; size: 40px, 40px; stroke-mode: plain; stroke-color: %s; text-size: 20px; text-alignment: center; }"
						+ "node.initial { fill-color: %s; }" + "node.final { fill-color: %s; }"
						+ "node.external { fill-color: %s; }" + "node.normal { fill-color: %s; }"
						+ "edge { text-size: 500px; text-alignment: along; }"
						+ "edge .text {text-alignment: along; }",
				getColorRGB(Color.BLACK), getColorRGB(Color.GREEN), getColorRGB(Color.RED), getColorRGB(Color.BLACK),
				getColorRGB(Color.GRAY));
		graph.setAttribute("ui.stylesheet", formating);
        Viewer viewer = graph.display();
        viewer.enableAutoLayout();
    }
    
    /**
     * Read json file.
     *
     * @param filePath - file path
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String readJsonFile(String filePath) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
        return new String(jsonData);
    }

    /**
     * Generates graph.
     *
     * @param filePath - file path
     */
    public static void generateGraph(String filePath) {
        if (filePath.length() == 0)
        	filePath = "outputFiles/global_output.json"; 
        try {
            String fsmTextJson = readJsonFile(filePath);
            System.setProperty("org.graphstream.ui", "swing");
            Graph fsmGraph = generateFSMGraph(fsmTextJson);
            drawFSMGraph(fsmGraph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the node class.
     *
     * @param nodeAttributes - node attributes
     * @return the node class
     */
    private static String getNodeClass(Map<String, Boolean> nodeAttributes) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Boolean> entry : nodeAttributes.entrySet()) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(entry.getKey());
        }
        return sb.toString();
    }
}
