package org.skypro.skyshop.search;

import java.util.Objects;

public final class SearchResult {
    private final String id;
    private final String name;
    private  final  String content;

    public SearchResult(String id, String name, String content) {
        this.id = Objects.requireNonNull(id, "ID не может быть null");
        this.name = Objects.requireNonNull(name, "Name не может быть null");
        this.content = Objects.requireNonNull(content, "Content не может быть null");
    }

    public static SearchResult fromSearchable(Searchable searchable){
        Objects.requireNonNull(searchable, "Searchable не может быть null");

        return new SearchResult(
                searchable.getId().toString(),
                searchable.getName(),
                searchable.getContentType()
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
