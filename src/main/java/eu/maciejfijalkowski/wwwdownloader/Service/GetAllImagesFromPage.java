package eu.maciejfijalkowski.wwwdownloader.Service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GetAllImagesFromPage {

    List<String> inputAll = new ArrayList<>();

    public List<String> imgReader(String webpage) throws Exception {

        URL url = new URL(webpage);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));


        String inputLine;
        while ((inputLine = in.readLine()) != null){
            String input = getImg(inputLine);
            if (!input.equals("")) inputAll.add(input);
        }
        in.close();
        return inputAll;
    }

    private String getImg(String s){
        Pattern p = Pattern.compile("<img [^>]*src=[\\\"']([^\\\"^']*)");
        Matcher m = p.matcher (s);
        String srcTag="";
        while (m.find()) {
            String src = m.group();
            int startIndex = src.indexOf("src=") + 5;
            srcTag = src.substring(startIndex, src.length());
        }
        return srcTag;
    }

    public void downloadImage(String www) throws IOException {

        BufferedImage image = null;
        for (String paths : inputAll) {

            String[] lastPart = paths.split("/");
            String fileName = lastPart[lastPart.length-1];
            String[] extentionPart = fileName.split("\\.");
            String extention = extentionPart[extentionPart.length-1];
            try {
                URL imageUrl = new URL(www+paths);
                image = ImageIO.read(imageUrl);
                if (image != null) {
                    File file = new File(fileName);
                    ImageIO.write(image, extention, file);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
