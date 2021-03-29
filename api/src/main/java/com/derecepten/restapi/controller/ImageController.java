package com.derecepten.restapi.controller;

import com.derecepten.restapi.component.imgcrawler.ImageCrawlService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by sergioh on 03/22/2021
 **/
@RestController
@RequestMapping(value = "/images")
public class ImageController {

    private final ImageCrawlService imageCrawlService;

    public ImageController(ImageCrawlService imageCrawlService) {
        this.imageCrawlService = imageCrawlService;
    }

    @GetMapping(value = "/find")
    @PreAuthorize("hasRole('USER')")
    public List<String> getImageUrls(@RequestParam String uri) throws IOException {
        return imageCrawlService.crawlImagesUrls(uri);
    }
}
