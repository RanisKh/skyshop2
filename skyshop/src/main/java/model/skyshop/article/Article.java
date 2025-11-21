package model.skyshop.article;

import model.skyshop.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String articleName;
    private final String articleValue;
    private final UUID id;

    public Article(UUID id, String articleName, String articleValue) {
        validateArticleName(articleName);
        validateArticleValue(articleValue);
        this.id = Objects.requireNonNull(id, "ID не может быть null");

        this.articleName = articleName.trim();
        this.articleValue = articleValue;
    }

    private void validateArticleName(String articleName) {
        if (articleName == null || articleName.isBlank()) {
            throw new IllegalArgumentException("Название статьи не может быть null, пустой строкой или состоять только из пробелов");
        }
    }

    private void validateArticleValue(String articleValue) {
        if (articleValue == null || articleValue.isBlank()) {
            throw new IllegalArgumentException("Текст статьи не может быть null");
        }
    }

    public static Article create(String articleName, String articleValue) {
        return new Article(UUID.randomUUID(), articleName, articleValue);
    }

    @Override
    public String toString() {
        return "articleName='" + articleName + '\'' +
                ", articleValue='" + articleValue + '\'';
    }

    public String getArticleName() {
        return articleName;
    }

    public String getArticleValue() {
        return articleValue;
    }


    @Override
    public UUID getId(){
        return id;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return articleName;
    }

    @Override
    public String getStringRepresentation() {
        return Searchable.super.getStringRepresentation();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleName, article.articleName) && Objects.equals(articleValue, article.articleValue) && Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleName, articleValue, id);
    }
}


