package model.skyshop.controller;


import model.skyshop.search.SearchResult;
import model.skyshop.service.SearchService;

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
