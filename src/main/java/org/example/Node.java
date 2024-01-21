package org.example;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor
public class Node {

    private final int number;

    private Node parentNode;

    private List<Node> children = new ArrayList<>();
}
