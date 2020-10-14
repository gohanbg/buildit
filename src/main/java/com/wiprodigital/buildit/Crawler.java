package com.wiprodigital.buildit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawler {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://wiprodigital.com").get();
        System.out.println(doc.title());
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        System.out.printf("\nMedia: (%s)", media.size());
        for (Element src : media) {
            if (src.normalName().equals("img"))
                System.out.printf(" * %s: %s %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        src.attr("alt"));
            else
                System.out.printf(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        System.out.printf("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            System.out.printf(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
        }

        System.out.printf("\nLinks: (%d)", links.size());
        for (Element link : links) {
            System.out.printf(" * a: <%s>  (%s)", link.attr("abs:href"), link.text().trim());
        }
    }
}
