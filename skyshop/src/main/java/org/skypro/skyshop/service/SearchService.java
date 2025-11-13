package org.skypro.skyshop.service;

import org.skypro.skyshop.search.SearchResult;
import org.skypro.skyshop.search.Searchable;
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

        String LowerQuery = query.toLowerCase().trim();

        Collection<Searchable> allSearchables = storageService.getAllSerchables();

        return allSearchables.stream()
                .filter(Objects::nonNull)
                .filter(searchable ->{
                    String searchTerm = searchable.getSearchTerm();
                    return searchTerm != null && searchTerm.toLowerCase().contains(LowerQuery);
                })
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }

    private List<Searchable> getAllSearchables(){
        List<Searchable> allSearchables = new ArrayList<>();

        allSearchables.addAll(storageService.getAllProducts());

        allSearchables.addAll(storageService.getAllArticle());

        return allSearchables;

    }

    private Comparator<Searchable> createSearchComparator(){
        return (s1,s2) ->{
            int lengthCompare = Integer.compare(
                    s2.getName().length(),
                    s1.getName().length()
            );
            if (lengthCompare != 0){
                return lengthCompare;
            }
            return s1.getName().compareTo(s2.getName());
        };
    }
}

