package bookfinderWebApp.model;

public class Book {

    private String title;

    private String authors;

    private String publishedDate;

    private String subtitle;

    private String url;

    private int listPrice;

    private String formattedListPrice;

    private int retailPrice;

    private String formattedRetailPrice;

    private String description;
    
    private String publisher;

    public Book(String title, String authors, String publishedDate, String subtitle, String url, int listPrice,
            String formattedListPrice, int retailPrice, String formattedRetailPrice, String description,
            String publisher) {
        super();
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.subtitle = subtitle;
        this.url = url;
        this.listPrice = listPrice;
        this.formattedListPrice = formattedListPrice;
        this.retailPrice = retailPrice;
        this.formattedRetailPrice = formattedRetailPrice;
        this.description = description;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    public String getFormattedListPrice() {
        return formattedListPrice;
    }

    public void setFormattedListPrice(String formattedListPrice) {
        this.formattedListPrice = formattedListPrice;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getFormattedRetailPrice() {
        return formattedRetailPrice;
    }

    public void setFormattedRetailPrice(String formattedRetailPrice) {
        this.formattedRetailPrice = formattedRetailPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    

}
