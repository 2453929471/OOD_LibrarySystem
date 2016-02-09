package service;

import dao.impl.BookDaoImpl;
import entity.Book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gao on 15/10/19.
 */
public class BookService {

    //for user,simpleSearch
    public List<Book> simpleSearch(String bname,int currentPage,int pageSize) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        return bdi.simpleSearch(bname,currentPage,pageSize);
    }


    //for simple Search 分页
    public int getResultCount(String bname) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        return bdi.getResultCount(bname);
    }

    //for advanced Search 分页
    public int getResultCount(Book book) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        return bdi.getResultCount(book);
    }


    //for user
    public List<Book> advanceSearch(Book book,int currentPage, int pageSize) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        return bdi.advanceSearch(book,currentPage,pageSize);
    }

    //for admin
    public Book crawler(String ISBN) throws SQLException,IOException{
        BookDaoImpl bdi=new BookDaoImpl();
        Book book=bdi.isbnExist(ISBN);
        if(book!=null){
            return book;
        }
        Book book1=bdi.crawler(ISBN);
        return book1;
    }

    //for admin
    public int addBook(Book book) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        return bdi.addBook(book);
    }



    //for admin
    public void updBook(Book book) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        bdi.updBook(book);
    }

    //for admin
    public Book findByIsbn(String isbn) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        return bdi.isbnExist(isbn);
    }

    //common
    public List getCat() throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        return bdi.getCat();
    }

    //common
    public Book getDetail(String isbn) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        return bdi.getDetail(isbn);
    }

    //for admin
    public void delBook(int bid) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        bdi.delBook(bid);
    }

    //common
    public List queryUserInfo(int uid) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        return bdi.queryUserInfo(uid);
    }
}
