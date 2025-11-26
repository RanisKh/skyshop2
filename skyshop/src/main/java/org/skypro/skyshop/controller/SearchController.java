package org.skypro.skyshop.controller;


import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam("pattern") String pattern){
        return searchService.search(pattern);
    }
}
