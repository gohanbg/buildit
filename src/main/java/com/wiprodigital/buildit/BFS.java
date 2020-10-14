package com.wiprodigital.buildit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class BFS {
    public void traverseGraph(Node<?> firstElement) {
        Queue<Node<?>> pageNodes = new LinkedList<>();
        pageNodes.offer(firstElement);
        Set<Node<?>> visited = new HashSet<>();
        visited.add(firstElement);
        while(!pageNodes.isEmpty()) {
            Node<?> currentElement = pageNodes.poll();
            List<Node<?>> newNodes = currentElement.neighbours().stream()
                    .filter(node -> !visited.contains(node))
                    .collect(Collectors.toList());
            visited.addAll(newNodes);
            for (Node<?> node : newNodes) {
                pageNodes.offer(node);
            }
        }
        visited.stream().map(Node::getValue).forEach(System.out::println);
    }
}
