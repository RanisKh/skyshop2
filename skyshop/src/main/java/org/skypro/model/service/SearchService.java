package org.skypro.model.service;

import org.skypro.model.search.SearchResult;
import org.skypro.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search (String query){
        if (query == null|| query.trim().isEmpty()){
            return Collections.emptyList();
        }

        String lowerQuery = query.toLowerCase().trim();

        Collection<Searchable> allSearchables = storageService.getAllSerchables();

        return allSearchables.stream()
                .filter(Objects::nonNull)
                .filter(searchable ->{
                    String searchTerm = searchable.getSearchTerm();
                    return searchTerm != null && searchTerm.toLowerCase().contains(lowerQuery);
                })
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}