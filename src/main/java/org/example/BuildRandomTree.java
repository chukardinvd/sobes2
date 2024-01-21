package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BuildRandomTree {

    public static int nodeCount = 10;


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 50; i++) {

            List<Node> nodes = new ArrayList<>();
            for (int nc = 0; nc <= nodeCount - 1; nc++) {
                Node n = new Node(nc);
                nodes.add(n);
            }

            TreeBuilder treeBuilder = new TreeBuilder();
            List<Node> retVal = treeBuilder.buildRandomTree(nodes);


            retVal
                    .forEach(root -> {
                        Consumer<Node> nOp = node -> {
                            // узел конечный
                            if (node.getChildren() == null || node.getChildren().size() == 0) {
                                List<Node> parentsNode = treeBuilder.getAllParentNodes(node);
                                System.out.println(treeBuilder.printAllParents(parentsNode));
                            }
                        };
                        treeBuilder.observeTree(root, nOp);
                    });

            // все узлы дерева
            List<Node> allNode = new ArrayList<>();
            retVal
                    .forEach(root -> {
                        Consumer<Node> nOp = node -> {
                            if (!allNode.contains(node)) {
                                allNode.add(node);
                            }
                        };
                        treeBuilder.observeTree(root, nOp);
                    });


            System.out.println("all node is");
            List<Node> sortNodes = allNode.stream()
                    .sorted(Comparator.comparingInt(Node::getNumber))
                    .collect(Collectors.toList());


            var strNodes = sortNodes.stream()
                    .map(el -> el.getNumber() + ":" + (el.getParentNode() == null ? "" : el.getParentNode().getNumber()))
                    .collect(Collectors.toList());

            System.out.println(String.join(";", strNodes));

            System.out.println("done");
        }
    }

}