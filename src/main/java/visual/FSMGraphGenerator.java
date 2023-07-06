package visual;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;
import org.json.JSONArray;
import org.json.JSONObject;

public class FSMGraphGenerator {

    public static Graph generateFSMGraph(String fsmTextJson) {
        JSONObject fsm = new JSONObject(fsmTextJson);

        JSONArray states = fsm.getJSONArray("states");
        JSONArray initialStates = fsm.getJSONArray("initialStates");
        JSONArray finalStates = fsm.getJSONArray("finalStates");
        JSONArray transitions = fsm.getJSONArray("transitions");

        // Create a directed graph
        Graph graph = new MultiGraph("FSMGraph");

        // Add states as nodes
        for (int i = 0; i < states.length(); i++) {
            String state = states.getString(i);
            Node node = graph.addNode(state);
            Map<String, Boolean> nodeAttributes = new HashMap<>();

            if (initialStates.toList().contains(state)) {
                nodeAttributes.put("initial", true);
            }
            if (finalStates.toList().contains(state)) {
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

            if (!initialStates.toList().contains(state) && !finalStates.toList().contains(state) &&
                    !nodeAttributes.containsKey("external") && !nodeAttributes.containsKey("open")) {
                nodeAttributes.put("normal", true);
            }

            node.setAttribute("ui.class", getNodeClass(nodeAttributes));
            node.setAttribute("ui.label", state); // Set the node label
        }

        // Add transitions as edges
        for (int i = 0; i < transitions.length(); i++) {
            JSONObject transition = transitions.getJSONObject(i);
            String from = transition.getString("from");
            String to = transition.getString("to");
            String action = transition.getString("actionCalled");
            Edge edge = graph.addEdge(from + "-" + to, from, to, true);
            edge.setAttribute("ui.label", action);
        }

        return graph;
    }
    
    
    public static void drawFSMGraph(Graph graph) {
    	
        Viewer viewer = graph.display();
        viewer.enableAutoLayout();
    }
    
    public static String readJsonFile(String filePath) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
        return new String(jsonData);
    }

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
