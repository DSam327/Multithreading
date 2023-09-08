import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XML_to_URL {
    public static List<String> extractUrlsFromXML(String xmlUrl) {
        List<String> urlList = new ArrayList<>();

        try {
            // Fetch the XML content from the URL
            Document doc = Jsoup.connect(xmlUrl).get();

            // Parse the XML content using Jsoup
            Element rootElement = doc.select("urlset").first(); // Replace "root" with your XML root element

            if (rootElement != null) {
                // Find and extract URL elements within the XML
                Elements urlElements = rootElement.select("url"); // Replace "url" with your XML element name

                for (Element urlElement : urlElements) {
                    String url = urlElement.text();
                    urlList.add(url);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urlList;
    }

    public static void main(String[] args) {
        String xmlUrl = "https://thapar.edu/sitemap.xml"; // Replace with the actual URL of your XML document
        List<String> urls = extractUrlsFromXML(xmlUrl);

        System.out.println("List of URLs:");
        for (String url : urls) {
            System.out.println(url);
        }
    }
}
