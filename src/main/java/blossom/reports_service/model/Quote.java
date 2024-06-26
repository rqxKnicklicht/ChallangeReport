package blossom.reports_service.model;

public class Quote {
  private String quote;
  private String author;

  public Quote() {
  }

  public Quote(String quote, String author) {
    this.quote = quote;
    this.author = author;
  }

  // Getters and setters
  public String getQuote() {
    return quote;
  }

  public void setQuote(String quote) {
    this.quote = quote;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Quote{" +
        "quote='" + quote + '\'' +
        ", author='" + author + '\'' +
        '}';
  }
}
