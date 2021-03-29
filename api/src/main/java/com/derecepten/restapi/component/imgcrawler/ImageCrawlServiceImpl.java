package com.derecepten.restapi.component.imgcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sergioh on 03/22/2021
 **/
@Service
public class ImageCrawlServiceImpl implements ImageCrawlService {

    @Override
    public List<String> crawlImagesUrls(String uri) throws IOException {
        String html = connectUrl(uri).html();

        ArrayList<String> arrayList = Jsoup.parse(html).select("img")
                .stream().map(p -> p.absUrl("src"))
                .filter(url -> !url.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
        return arrayList;

    }

    private Document connectUrl(String uri) throws IOException {
        return Jsoup.connect(uri).get();
    }

}
