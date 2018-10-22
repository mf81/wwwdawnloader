package eu.maciejfijalkowski.wwwdownloader.Service;

import org.springframework.stereotype.Service;
import com.google.common.base.Strings;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class CorrectUrl {

    private static boolean isUrlHttps(String url){
        if(Strings.isNullOrEmpty(url))
            return false;
        return url.toLowerCase().startsWith("https://");
    }

    public static String urlChacker(String www)
    {
        if (isUrlHttps(www)) return "https://" + www;
        else return "http://" + www;
    }

    public static URL urlTaker(String webpage){
        URL url = null;
        try {
            url = new URL(webpage);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}
