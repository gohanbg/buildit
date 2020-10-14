package com.wiprodigital.buildit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PageNode extends Node<String> {

    public PageNode(String value) {
        super(value);
    }

    @Override
    Set<PageNode> neighbours() {
        Set<PageNode> nodes = new HashSet<>();
            try {
                Document doc = Jsoup.connect(getValue()).get();
                Elements links = doc.select("a[href]");
                nodes = links.stream().map(element -> element.attr("abs:href"))
                        .filter(s -> s.startsWith("http://wiprodigital.com") || s.startsWith("https://wiprodigital.com"))
                        .map(PageNode::new)
                        .collect(Collectors.toSet());
            } catch (Exception e) {
                e.printStackTrace();
            }
        return nodes;
    }
}
