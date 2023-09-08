import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Web_Scraping {
    public static List<List<String>> emails=new ArrayList<>();
    public static void main(String[] args) {
        String xmlUrl = "https://sample.com/sample.xml";
        List<String> urls = extractUrlsFromXML(xmlUrl);
        List<Long> time=new ArrayList<>();
        for(int i=0;i<8;i++){
            long startTime = System.currentTimeMillis();
            multihtreadimplement.multi(i+1, urls);
            long end=System.currentTimeMillis()-startTime;
            time.add(end);
        }
        List<Integer> x= Arrays.asList(1,2,3,4,5,6,7,8);
        CSVWriterExample.writeIntegerListsToCSV("values.csv", x, time);
    }

    public static List<String> extractUrlsFromXML(String xmlUrl) {
        List<String> urlList = new ArrayList<>();
        try {
            // Fetch the XML content from the URL
            Document doc = Jsoup.connect(xmlUrl).get();
            // Parse the XML content using Jsoup
            Element rootElement = doc.select("urlset").first();
            if (rootElement != null) {
                // Find and extract URL elements within the XML
                Elements urlElements = rootElement.select("url");
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
}

class multihtread implements Runnable{
    private String url;
    public multihtread(String URL){
        url=URL;
    }
    public void run(){
        try{
            List<String> ans=Scraping.scraper(url);
            Web_Scraping.emails.add(ans);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Scraping{
    public static List<String> scraper(String url) {
        List<String> emailList = new ArrayList<>();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            // Set the User-Agent header to mimic a web browser
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            String html = response.toString();
            
            // Define a regular expression pattern for matching email addresses
            String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,7}\\b";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(html);
            
            // Find and add all email addresses found in the HTML content
            while (matcher.find()) {
                emailList.add(matcher.group());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emailList;
    }
}

class multihtreadimplement{
    public static void multi(int i, List<String> urls){
        int Threadcnt=Thread.activeCount();
        for(String url: urls){
            new Thread(new multihtread(url));
            while(true){
                if(Thread.activeCount()-Threadcnt!=i){
                    break;
                }
            }
        }
        while(Thread.activeCount()!=Threadcnt){
            continue;
        }
        CSVWriterExample.writeFlattenedListToCSV("Emails.csv", Web_Scraping.emails);
        for (List<String> innerList : Web_Scraping.emails) {
            innerList.clear();
        }
        Web_Scraping.emails.clear();
    }
}

class CSVWriterExample {
    public static void writeIntegerListsToCSV(String filePath, List<Integer> list1, List<Long> list2) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write the header
            writer.println("List1,List2");

            // Write data from both lists to the CSV file
            int maxSize = Math.max(list1.size(), list2.size());
            for (int i = 0; i < maxSize; i++) {
                int value1 = list1.get(i); 
                long value2 = list2.get(i);

                writer.println(value1 + "," + value2);
            }

            System.out.println("CSV file has been created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeFlattenedListToCSV(String filePath, List<List<String>> listOfLists) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Flatten the list of lists and write the strings to the CSV file
            for (List<String> innerList : listOfLists) {
                for (String str : innerList) {
                    writer.println(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




