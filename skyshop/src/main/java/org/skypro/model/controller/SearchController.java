package org.skypro.model.controller;


import org.skypro.model.search.SearchResult;
import org.skypro.model.service.SearchService;

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
