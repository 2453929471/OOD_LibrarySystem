package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.BookService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * Created by gao on 15/10/19.
 */
public class BookAction extends ActionSupport implements ModelDriven<Book>{
    static Logger logger = LogManager.getLogger(BookAction.class.getName());

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

    Map session = (Map<String,Object>)ActionContext.getContext().getSession();


    //for user，默认每页返回20条数据
    public String simpleSearch(){
        //System.out.println(bname1);
        logger.entry();
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
            logger.trace("simpleSearch success");
            return  "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException",e);
        }
        return "ERROR";
    }


    //for user,默认每页返回20条数据
    public String advanceSearch(){
        logger.entry();
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
            logger.trace("advanceSearch success");
            return  "SUCCESS";
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("SQLException"+e);
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
        logger.entry();
        //System.out.println(isbn1);
        BookService bs=new BookService();
        try {
            Book book=bs.crawler(isbn1);
            List catList=bs.getCat();
            //System.out.println(book.getCategory());
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("book",book);
            req.put("catList",catList);
            logger.trace("crawler success");
            return "SUCCESS";
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("SQLException",e);
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error("IOException",e);
        }
        return "ERROR";
    }

    public String preadd(){
        logger.entry();
        BookService bs=new BookService();
        try {
            List catList=bs.getCat();
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("catList", catList);
            logger.trace("preadd success");
            return "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException"+e);
            //e.printStackTrace();
        }
        return "ERROR";
    }

    //for admin addBook
    public String addBook(){
        logger.info("entry addBook");
        BookService bs=new BookService();
        try {
            int bid=bs.addBook(book);
            if(bid==0){
                return "ERROR";
            }
            logger.info("addBook success --"+session.get("uid"));
            book.setBid(bid);
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("b",book);
            return "SUCCESS";
        } catch (SQLException e) {
            logger.trace("SQLException"+e);
           // e.printStackTrace();
        }
        return "ERROR";
    }


    //for admin preupd
    public String preupd(){
        logger.entry();
        BookService bs=new BookService();
        try {
            Book b=bs.getDetail(book.getIsbn());
            List catList=bs.getCat();
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("b",b);
            req.put("catList", catList);
            logger.trace("preupd success");
            return "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException"+e);
            //e.printStackTrace();
        }
        return "ERROR";
    }

    //for admin updBook
    public String updBook(){
        logger.info("entry updBook");
        BookService bs=new BookService();
        try {
            //System.out.println(book.getCategory()+" "+book.getIsbn());
            bs.updBook(book);
            logger.info("updBook success -- "+session.get("uid"));
            return "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException"+e);
            //e.printStackTrace();
        }
        return "ERROR";
    }

    //common method
    public String getCat(){
        logger.entry();
        BookService bs=new BookService();
        try {
            List catList=bs.getCat();
            //System.out.println(catList);
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("catList",catList);
            logger.trace("getCat success");
            return "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException"+e);
            //e.printStackTrace();
        }
        return "ERROR";
    }

    //for user
    public String getDetail(){
        logger.entry();
        BookService bs=new BookService();
        try {
            Book b=bs.getDetail(book.getIsbn());
            req=(Map<String,Object>)ActionContext.getContext().get("request");
            req.put("b",b);
            logger.trace("getDetail success");
            return "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException"+e);
            //e.printStackTrace();
        }
        return "ERROR";
    }

    //for admin
    public String delBook(){
        logger.info("entry delBook");
        BookService bs=new BookService();
        try {
            bs.delBook(book.getBid());
            logger.info("delBook success -- "+session.get("uid"));
            return "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException",e);
            //e.printStackTrace();
        }
        return "ERROR";
    }

}
