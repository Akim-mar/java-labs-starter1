package edu.course.lab04;

public final class Book {
    private final String isbn;
    private final String title;

    public Book(String isbn, String title) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("isbn must not be blank");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }

    // Логическое равенство: две книги одинаковы, если совпадает ISBN
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book other = (Book) o;
        return isbn.equals(other.isbn);
    }

    // Использует то же поле, что и equals: равные объекты -> равные хеши
    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}