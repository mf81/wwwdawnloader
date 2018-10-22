package eu.maciejfijalkowski.wwwdownloader.Controller;

import eu.maciejfijalkowski.wwwdownloader.Service.CorrectUrl;
import eu.maciejfijalkowski.wwwdownloader.Service.GetAllImagesFromPage;
import eu.maciejfijalkowski.wwwdownloader.Service.GetTextFromPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    GetTextFromPage getTextFromPage;

    @Autowired
    GetAllImagesFromPage getAllImagesFromPage;

    @Autowired
    CorrectUrl correctUrl;

    @GetMapping("/api/get-text/{www}")
    public String textGetter(@PathVariable String www) throws IOException {
        return getTextFromPage.extractText(correctUrl.urlChacker(www));

    }

    @GetMapping("/api/get-text/{www}/{param}")
    public String textGetter2(@PathVariable String www, @PathVariable String param) throws IOException {
        return getTextFromPage.extractText(correctUrl.urlChacker(www + "/" + param));

    }

    @GetMapping("/api/download-text/{www}")
    public String textGetter3(@PathVariable String www) throws IOException {
        String text = getTextFromPage.extractText(correctUrl.urlChacker(www));
        getTextFromPage.filePrint(www + ".txt");
        return text;

    }

    @GetMapping("/api/download-text/{www}/{param}")
    public String textGetter4(@PathVariable String www, @PathVariable String param) throws IOException {
        String text = getTextFromPage.extractText(correctUrl.urlChacker(www + "/" + param));
        getTextFromPage.filePrint(www + "-" + param + ".txt");
        return text;

    }




    @GetMapping("/api/get-img/{www}")
    public List<String> imageGetter(@PathVariable String www) throws Exception {
        return getAllImagesFromPage.imgReader(correctUrl.urlChacker(www));
    }


    @GetMapping("/api/get-img/{www}/{param}")
    public List<String> imageGetter2(@PathVariable String www, @PathVariable String param) throws Exception {
        return getAllImagesFromPage.imgReader(correctUrl.urlChacker(www + "/" + param));
    }


    @GetMapping("/api/download-img/{www}")
    public List<String> imageGetter3(@PathVariable String www) throws Exception {
        List<String> imgs = getAllImagesFromPage.imgReader(correctUrl.urlChacker(www));
        getAllImagesFromPage.downloadImage(correctUrl.urlChacker(www));
        return imgs;
    }

    @GetMapping("/api/download-img/{www}/{param}")
    public List<String> imageGetter4(@PathVariable String www, @PathVariable String param) throws Exception {
        List<String> imgs = getAllImagesFromPage.imgReader(correctUrl.urlChacker(www + "/" + param));
        getAllImagesFromPage.downloadImage(correctUrl.urlChacker(www + "/" + param));
        return imgs;
    }



}
