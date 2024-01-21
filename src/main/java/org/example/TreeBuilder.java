package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TreeBuilder {

    /**
     * Построить дерево и возвратить корневой узел
     */
    public List<Node> buildRandomTree(List<Node> nodes) {

        int nodeCount = nodes.size();

        while (needScan(nodes)) {
            int randomNode = getRandom(nodeCount);
            Node n = nodes.get(randomNode);
            if (n.getParentNode() == null) {
                // подобрать родителя
                int randomParent = getRandom(nodeCount);
                Node nParent = nodes.get(randomParent);
                if (canParent(n, nParent)) {
                    n.setParentNode(nParent);
                    nParent.getChildren().add(n);
                }
            }
        }

        return rootList(nodes);
    }

    /**
     * продолжать сканировать
     *
     */
    private boolean needScan(List<Node> nodes) {
        // только один корневой узел
        return rootList(nodes).size() != 1;
    }

    private List<Node> rootList(List<Node> nodes) {
        return nodes.stream()
                .filter(el -> el.getParentNode() == null)
                .collect(Collectors.toList());
    }

    /**
     * узел подъодит в качестве родителя
     */
    private boolean canParent(Node n, Node nParent) {
        // цепочка родителей не содержит выбранный узел
        List<Node> allNodes = getAllParentNodes(nParent);

        Node find = allNodes.stream()
                .filter(el ->
                        el.getNumber() == n.getNumber()
                )
                .findFirst()
                .orElse(null);

        return find == null;
    }

    public List<Node> getAllParentNodes(Node node) {
        List<Node> parentNodes = new ArrayList<>();
        parentNodes.add(node);
        Node n = node;
        while (n.getParentNode() != null) {
            parentNodes.add(n.getParentNode());
            n = n.getParentNode();
        }
        return parentNodes;
    }

    public void observeTree(Node node, Consumer<Node> nodeOperation) {
        nodeOperation.accept(node);
        if (node.getChildren() != null) {

            node.getChildren()
                    .forEach(n -> observeTree(n, nodeOperation));
        }
    }

    /**
     * Получить случайное целое число в диапазоне от 0 до maxVal-1
     * @param maxVal - максимальное значение (не может быть достигнуто)
     * @return случайное значение в диапазоне от 0 до (maxVal-1)
     */
    public static int getRandom(int maxVal) {
        Random objGenerator = new Random();
        return objGenerator.nextInt(maxVal);
    }

    public String printAllParents(List<Node> parentsNode) {

        var lst = parentsNode.stream()
                .map(el -> ((Integer) el.getNumber()).toString())
                .collect(Collectors.toList());

        Collections.reverse(lst);

        return String.join("_", lst);

    }
}
