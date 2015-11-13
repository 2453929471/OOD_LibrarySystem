package entity;

/**
 * Created by gao on 15/10/16.
 */
public class Book {
    private int bid;
    private String bname;
    private String author;
    private String publisher;
    private String isbn;
    private String price;
    private String explanation;
    private String uid;
    private String borrowtime;
    private int cid;
    private String category;
    private int num;


    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }


    public Book() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    //for user advance search
    public Book(String bname, String publisher, String author, String isbn, int cid) {
        this.bname = bname;
        this.publisher = publisher;
        this.author = author;
        this.isbn = isbn;
        this.cid = cid;
    }

    //for user search result
    public Book(String bname, String author, String isbn, String publisher, String price, String explanation, String category) {
        this.bname = bname;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.price = price;
        this.explanation = explanation;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
