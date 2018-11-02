package main;

public class Book
{
    private String title;
    private String author;
    private String publication;
    private String isbn;
    private int pages;

    public Book(String title,
            String author,
            String publication,
            String isbn,
            int pages)
    {
        this.title       = title;
        this.author      = author;
        this.publication = publication;
        this.isbn        = isbn;
        this.pages       = pages;
    }
}
