package dao.inter;
import entity.Book;
import java.util.List;

/**
 * Created by gao on 15/11/15.
 */
public interface BookDao {
    public List<Book> simpleSearch(String bname, int currentPage, int pageSize);
    public  List<Book> advanceSearch(Book book,int currentPage, int pageSize);
    public void addBook(Book book);
    public void updBook(Book book);
    public Book getDetail(int bid);
    public void delBook(int bid);
    public void borrow(int bid,int uid);
    public void breturn(int bid,int uid);
}
