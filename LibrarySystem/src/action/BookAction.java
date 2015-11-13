package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import entity.Book;
import service.BookService;

import javax.swing.plaf.basic.BasicSliderUI;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by gao on 15/10/19.
 */
public class BookAction extends ActionSupport implements ModelDriven<Book>{
    private Map<String,Object> req;

    private Book book=new Book();
    public Book getModel(){
        return book;
    }

    private String bname1;
    public void setBname1(String bname1) {
        this.bname1 = bname1;
    }
    public String getBname1() {
        return bname1;
    }

    private String isbn1;
    public String getIsbn1() {
        return isbn1;
    }
    public void setIsbn1(String isbn1) {
        this.isbn1 = isbn1;
    }



    //for user，默认每页返回20条数据
    public String simpleSearch(){
        //System.out.println(bname1);
        BookService bs=new BookService();
        List<Book> blist=null;
        try {
            blist = bs.simpleSearch(bname1,currentPage,pageSize);
            if(blist==null){
                return "NORESULT";
            }
            req= (Map<String,Object>)ActionContext.getContext().get("request");
            req.put("blist",blist);

            totalSize=bs.getResultCount(bname1);
            if(totalSize%pageSize==0){
                totalPage=totalSize/pageSize;
            }else{
                totalPage=totalSize/pageSize+1;
            }
            //System.out.println(totalSize);
            //System.out.print("success");
            return  "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }


    //for user,默认每页返回20条数据
    public String advanceSearch(){
        //System.out.println(book.getBname()+book.getAuthor()+book.getIsbn()+book.getPublisher()+book.getCid());
        BookService bs=new BookService();
        List<Book> blist=null;
        try {
            blist=bs.advanceSearch(book,currentPage,pageSize);
            if(blist==null){
                return "NORESULT";
            }
            req= (Map<String,Object>)ActionContext.getContext().get("request");
            req.put("blist",blist);
            totalSize=bs.getResultCount(book);
            if(totalSize%pageSize==0){
                totalPage=totalSize/pageSize;
            }else{
                totalPage=totalSize/pageSize+1;
            }
            return  "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }




    //分页
    private int currentPage=1;
    private int totalPage;
    private int totalSize;
    private int pageSize=20;
    public int getCurrentPage(){
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    //for admin seach ISBN in database then in amazon
    public String crawler(){
        System.out.println(isbn1);  //jsp中大写参数传不过来
        BookService bs=new BookService();
        try {
            Book book=bs.crawler(isbn1);
            List catList=bs.getCat();
            System.out.println(book.getCategory());
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("book",book);
            req.put("catList",catList);
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String preadd(){
        BookService bs=new BookService();
        try {
            List catList=bs.getCat();
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("catList", catList);
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    //for admin addBook
    public String addBook(){
        //System.out.println(book.getExplanation());
        BookService bs=new BookService();
        try {
            bs.addBook(book);
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    //for admin before updBook searchByIsbn
    public String searchByIsbn(){
        BookService bs=new BookService();
        try {
            Book book=bs.findByIsbn(isbn1);
            if(book==null){
                return "NOFIND";
            }
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("book",book);
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";

    }

    //for admin preupd
    public String preupd(){
        BookService bs=new BookService();
        try {
            Book b=bs.getDetail(book.getBid());
            List catList=bs.getCat();
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("b",b);
            req.put("catList", catList);
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    //for admin updBook
    public String updBook(){
        BookService bs=new BookService();
        try {
            System.out.println(book.getCategory()+" "+book.getIsbn());
            bs.updBook(book);
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    //common method
    public String getCat(){
        BookService bs=new BookService();
        try {
            List catList=bs.getCat();
            //System.out.println(catList);
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("catList",catList);
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    //for user
    public String getDetail(){
        BookService bs=new BookService();
        try {
            System.out.print(book.getBid());
            Book b=bs.getDetail(book.getBid());
            System.out.println(b.getAuthor() + b.getBname());
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("b",b);
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    //for admin
    public String delBook(){
        BookService bs=new BookService();
        try {
            bs.delBook(book.getBid());
            return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

}
