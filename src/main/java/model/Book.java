package model;

public class Book {
    private final String title;
    private final String genre;
    private final Long authorId;

    public Book(String title, String genre, Long authorId) {
        this.title = title;
        this.genre = genre;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }


    public String getGenre() {
        return genre;
    }


    public Long getAuthorId() {
        return authorId;
    }

}
