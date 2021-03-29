package com.derecepten.restapi.component.imgcrawler;

import java.io.IOException;
import java.util.List;

/**
 * Created by sergioh on 03/22/2021
 **/
public interface ImageCrawlService {
    List<String> crawlImagesUrls(String uri) throws IOException;

}
