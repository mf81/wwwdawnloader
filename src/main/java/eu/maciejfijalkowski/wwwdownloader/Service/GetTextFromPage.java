package eu.maciejfijalkowski.wwwdownloader.Service;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class GetTextFromPage {
    String textOnly;

    private InputStreamReader streamPage(String webpage){
        InputStreamReader page = null;
        try {
            page = new InputStreamReader(CorrectUrl.urlTaker(webpage).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    public String extractText(String webpage) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(streamPage(webpage));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        textOnly = Jsoup.parse(sb.toString()).text();
        return textOnly;
    }

    public void filePrint(String filename) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(textOnly);
        }
    }

}
