package org.example;


import java.util.*;
import java.util.function.Consumer;

/**
 * 5_4_7
 * 5_4_0_8_2_6
 * 5_4_0_1
 * 5_9_3
 * all node is
 * 0:4;1:0;2:8;3:9;4:5;5:;6:2;7:4;8:0;9:5
 *
 *
 8_9_7_6
 8_4_0_3_1
 8_5_2
 all node is
 0:4;1:3;2:5;3:0;4:8;5:8;6:7;7:9;8:;9:8

 4_3_5_6_7
 4_3_5_6_2_0
 4_3_5_6_2_9_8
 4_3_5_6_2_9_1
 all node is
 0:2;1:9;2:6;3:4;4:;5:3;6:5;7:6;8:9;9:2


 */

public class LoadTreeFromString {

    public static void main(String[] args) {

        String treeString = "0:2;1:9;2:6;3:4;4:;5:3;6:5;7:6;8:9;9:2";

        Node rootNode = buildTreeFromString(treeString);


        // все ветки дерева
        List<Node> tree = new ArrayList<>();
        TreeBuilder treeBuilder = new TreeBuilder();
        Consumer<Node> nOp = node -> {
            if (node.getChildren() == null || node.getChildren().size() == 0) {
                tree.add(node);
            }
        };
        treeBuilder.observeTree(rootNode, nOp);

        // самые длинные ветки
        Map<Integer, List<Node>> mapLongTree = new HashMap<>();

        tree
                .forEach(n -> {
                    List<Node> parentsNode = treeBuilder.getAllParentNodes(n);
                    System.out.println(treeBuilder.printAllParents(parentsNode));

                    int size = parentsNode.size();

                    List<Node> lst = mapLongTree.computeIfAbsent(size, s -> new ArrayList<>());
                    lst.add(n);
                });

        Integer maxLenTree = mapLongTree.keySet()
                .stream().min((f1, f2) -> Long.compare(f2, f1))
                .orElse(null);


        System.out.println("long tree is");

        List<Node> lst = mapLongTree.get(maxLenTree);
        lst
                .forEach(n -> {
                    List<Node> parentsNode = treeBuilder.getAllParentNodes(n);
                    System.out.println(treeBuilder.printAllParents(parentsNode));
                });

        System.out.println("done");
    }

    private static Node buildTreeFromString(String treeString) {
        String[] nodesString = treeString.split(";");

        Map<Integer, Node> mapNodes = new HashMap<>();

        Arrays.stream(nodesString)
                .forEach(nodeStr -> {
                    String[] nodeParam = nodeStr.split(":");

                    int nodeId = Integer.parseInt(nodeParam[0]);
                    int parentId = nodeParam.length == 1 ? -1 : Integer.parseInt(nodeParam[1]);

                    Node pNode = mapNodes.computeIfAbsent(parentId, id -> id == -1 ? null : new Node(id));

                    Node node = mapNodes.computeIfAbsent(nodeId, Node::new);

                    if (pNode != null) {
                        node.setParentNode(pNode);
                        pNode.getChildren().add(node);
                    }

                });

        return mapNodes.values().stream()
                .filter(n -> n.getParentNode() == null)
                .findFirst()
                .orElse(null);
    }
}
