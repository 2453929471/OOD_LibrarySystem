package dao.impl;

import dao.util.DBUtil;
import entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gao on 15/10/20.
 */
public class BookDaoImpl extends DBUtil{


    //for admin, when adding books, first search in database, see if the book already in DB
    //查询结果cid， 返回 category
    public Book isbnExist(String ISBN) throws SQLException{
        Book book=null;
        Connection conn= DBUtil.getConn();
        String sql="select * from book where ISBN="+ISBN;
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            book=new Book();
            book.setIsbn(ISBN);
            book.setBname(rs.getString(2));
            book.setPublisher(rs.getString(4));
            book.setAuthor(rs.getString(3));
            book.setPrice(rs.getString(6));
            book.setExplanation(rs.getString(7));
            convertCidToCategory(conn,rs.getInt(10),book);
        }
        //System.out.print(book.getBname());
        DBUtil.close(rs,pstmt,conn);
        return book;
    }

    public void convertCidToCategory(Connection conn,int cid,Book book) throws SQLException{
        String sql1="select cname from category where cid='"+cid+"'";
        PreparedStatement pstmt1 = (PreparedStatement) conn.prepareStatement(sql1);
        ResultSet rs = pstmt1.executeQuery();
        while (rs.next()) {
            book.setCategory(rs.getString(1));
        }
    }

    public void convertCategoryToCid(Connection conn,String category,Book book) throws SQLException{
        String sql="select cid from category where cname=?";
        PreparedStatement pstmt1 = (PreparedStatement) conn.prepareStatement(sql);
        pstmt1.setString(1,category);
        ResultSet rs = pstmt1.executeQuery();
        while (rs.next()) {
            book.setCid(rs.getInt(1));
        }
    }


    //for amdin, when adding books, if isbnExist==false, crawl data from amazon and return data to admin.
    public Book crawler(String ISBN) throws IOException{
        Book book=new Book();
        book.setIsbn(ISBN);
        String amazonURL="http://www.amazon.com/gp/search/ref=sr_adv_b/?search-alias=stripbooks&field-isbn="+ISBN;
        System.out.println(amazonURL);
        String jumpURL=null;
        for(int i=0;i<5;i++) {
            org.jsoup.Connection con = Jsoup.connect(amazonURL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2");
            con.timeout(10000);
            Document doc = con.get();
            //System.out.println(doc.body());
            Element e1 = doc.select("[class=a-link-normal s-access-detail-page  a-text-normal]").first();
            if (e1 == null) {
                System.out.println("e1==null");
                continue;
            }
                jumpURL = e1.attr("href");
                if(jumpURL==null){
                    System.out.println("jumpURL==null");
                    continue;
                }
                //System.out.println(jumpURL);
                String bname = e1.attr("title");
                //System.out.println(bname);
                if (bname != null) {
                    book.setBname(bname);
                    System.out.println(book.getBname());
                }

            Element e2 = doc.select("[class=a-row a-spacing-none]").get(0);
            if (e2 != null) {
                String author = e2.text().replace("by", "");
                book.setAuthor(author);
                System.out.println(book.getAuthor());
            }
            /**
             Element e3=doc.select("[class=a-row a-spacing-none]").get(2).select("[class=a-size-small a-color-secondary a-text-strike]").first();
             if(e3!=null){
             String price=e3.text();
             book.setPrice(price);
             }**/
            Element e3 = doc.select("[class=a-size-small a-color-secondary a-text-strike]").first();
            if (e3 != null) {
                String price = e3.text();
                book.setPrice(price);
                System.out.println(book.getPrice());
            } else {
                Element e33 = doc.select("[class=a-row a-spacing-none]").get(2).select("[class=a-size-small a-color-secondary a-text-strike]").first();
                String price = e33.text();
                book.setPrice(price);
                System.out.println(book.getPrice());
            }
            //一本书有多个category，但是为了方便，这里取第一个
            Element ecat = doc.select("[id=ref_1000]").first().select("[class=refinementLink]").first();
            if (ecat != null) {
                book.setCategory(ecat.text());
                System.out.println(book.getCategory());
            }
            break;
        }
        if(jumpURL!=null){
            for(int j=0;j<5;j++){
                org.jsoup.Connection con2 = Jsoup.connect(jumpURL).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2");
                con2.timeout(10000);
                Document d2=con2.get();
                Element desc0=d2.select("[id=bookDescription_feature_div]").first();
                if(desc0==null){
                    System.out.println("desc0==null");
                    continue;
                }
                    Element desc=desc0.select("noscript div").first();
                    if(desc==null){
                        System.out.println("desc==null");
                        continue;
                    }
                    String explanation=desc.html();
                    book.setExplanation(explanation);
                    System.out.println(book.getExplanation());

                Element pub=d2.select("[class=bucket]").get(1).select("li").first();
                if(pub==null&&book.getPublisher()==null){
                    System.out.println("pub==null");
                    continue;
                }
                Elements xxx = pub.siblingElements();
                for(Element p:xxx){
                    if(p.text().contains("Publisher")){
                        String publisher=p.text().replace("Publisher: ", "");
                        book.setPublisher(publisher);
                        System.out.println(book.getPublisher());
                        break;
                    }
                }
            }
            return book;
        }
        //不写入数据库，传回管理员页面
        System.out.println("success");
        return book;
    }


    //for user, simple search
    public List<Book> simpleSearch(String bname,int currentPage,int pageSize) throws SQLException{
        List<Book> blist=new ArrayList<Book>();
        Connection conn= DBUtil.getConn();
        String sql="select bname,author,publisher,ISBN,cid,count(bid) from book where bname like ? GROUP by ISBN limit ?,?";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1,'%'+bname+'%');
        pstmt.setInt(2, (currentPage - 1) * pageSize);
        pstmt.setInt(3,currentPage*pageSize);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Book book=new Book();
            //book.setBid(rs.getInt(1));
            book.setBname(rs.getString(1));
            book.setPublisher(rs.getString(3));
            book.setAuthor(rs.getString(2));
            book.setIsbn(rs.getString(4));
            convertCidToCategory(conn, rs.getInt(5), book);
            book.setNum(rs.getInt(6));
            blist.add(book);
        }
        DBUtil.close(rs,pstmt,conn);
        return blist;
    }

    //for simple search 分页
    public int getResultCount(String bname) throws SQLException{
        int totalSize=0;
        Connection conn= DBUtil.getConn();
        String sql="select * from book where bname like ? GROUP BY ISBN";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1,'%'+bname+'%');
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            totalSize+=1;
        }
        DBUtil.close(rs,pstmt,conn);
        return totalSize;
    }

    //for advanced search 分页
    public int getResultCount(Book book) throws SQLException{
        int totalSize=0;
        Connection conn= DBUtil.getConn();
        //convertCategoryToCid(conn,book.getCategory(),book);
        String sql="select * from book where book.cid=(select cid from category where cname=?)";
        int a1=0;
        int a2=0;
        int a3=0;
        int a4=0;
        if(!book.getBname().equals("")){
            sql+=(" and bname like ?");
            a1=1;
        }
        if(!book.getIsbn().equals("")){
            sql+=(" and ISBN=?");
            a2=1;
        }
        if(!book.getAuthor().equals("")){
            sql+=(" and author like '%?%'");
            a3=1;
        }
        if(!book.getPublisher().equals("")){
            sql+=(" and publisher like '%?%'");
            a4=1;
        }
        sql+=" GROUP by ISBN";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1,book.getCategory());
        if(a1==1){
            pstmt.setString(2,'%'+book.getBname()+'%');
        }
        if(a2==1){
            pstmt.setString(3,book.getIsbn());
        }
        if(a3==1){
            pstmt.setString(4,book.getAuthor());
        }
        if(a4==1){
            pstmt.setString(5,book.getPublisher());
        }
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            totalSize+=1;
        }
        DBUtil.close(rs,pstmt,conn);
        return totalSize;
    }






    //for user, advance search
    public List<Book> advanceSearch(Book book,int currentPage,int pageSize) throws SQLException{
        List<Book> blist=new ArrayList<Book>();
        Connection conn=DBUtil.getConn();
        //System.out.println(book.getBname() + "---" + book.getIsbn());
        //convertCategoryToCid(conn,book.getCategory(),book);
        String sql="select bid,bname,author,publisher,ISBN,cid,count(bid) from book where book.cid=(select cid from category where cname=?)";
        int a1=0;
        int a2=0;
        int a3=0;
        int a4=0;
        if(!book.getBname().equals("")){
            sql+=(" and bname like ?");
            a1=1;
        }
        if(!book.getIsbn().equals("")){
            sql+=(" and ISBN=?");
            a2=1;
        }
        if(!book.getAuthor().equals("")){
            //System.out.println(book.getAuthor());
            sql+=(" and author like '%?%'");
            a3=1;
        }
        if(!book.getPublisher().equals("")){
            //System.out.println(book.getPublisher());
            sql+=(" and publisher like '%?%'");
            a4=1;
        }
        sql+=(" GROUP BY ISBN limit "+(currentPage-1)*pageSize+","+currentPage*pageSize);
        System.out.println(sql);
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1,book.getCategory());
        if(a1==1){
            pstmt.setString(2,'%'+book.getBname()+'%');
        }
        if(a2==1){
            pstmt.setString(3,book.getIsbn());
        }
        if(a3==1){
            pstmt.setString(4,book.getAuthor());
        }
        if(a4==1){
            pstmt.setString(5,book.getPublisher());
        }
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Book b=new Book();
            b.setBid(rs.getInt(1));
            b.setBname(rs.getString(2));
            b.setPublisher(rs.getString(4));
            b.setIsbn(rs.getString(5));
            b.setAuthor(rs.getString(3));
            b.setNum(rs.getInt(7));
            /**
            b.setIsbn(rs.getString(5));
            b.setPrice(rs.getString(6));
            b.setExplanation(rs.getString(7));**/
            convertCidToCategory(conn, rs.getInt(6),b);
            blist.add(b);
        }
        DBUtil.close(rs,pstmt,conn);
        return blist;
    }



    //for admin addBook
    public int addBook(Book book) throws SQLException{
        Connection conn=DBUtil.getConn();
        //System.out.println(book.getCategory()+"aaaaaa");
        convertCategoryToCid(conn,book.getCategory(),book);
        String sql="insert into book(bname,author,publisher,price,ISBN,explanation,cid) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1,book.getBname());
        pstmt.setString(2,book.getAuthor());
        pstmt.setString(3,book.getPublisher());
        pstmt.setString(4,book.getPrice());
        pstmt.setString(5,book.getIsbn());
        pstmt.setString(6,book.getExplanation());
        pstmt.setInt(7,book.getCid());
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        int bid=0;
        if (rs.next()) {
            bid = rs.getInt(1);
            //System.out.println("数据主键：" + bid);
        }
        pstmt.close();
        conn.close();
        return bid;
    }

    //for admin, 传过来的是category， 写入数据库之前转成cid
    public void updBook(Book book) throws SQLException{
        Connection conn=DBUtil.getConn();
        convertCategoryToCid(conn, book.getCategory(), book);
        String sql="update book set bname=?,author=?,publisher=?,price=?,explanation=?,cid=? where ISBN=?";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1,book.getBname());
        pstmt.setString(2,book.getAuthor());
        pstmt.setString(3,book.getPublisher());
        pstmt.setString(4,book.getPrice());
        pstmt.setString(5,book.getExplanation());
        pstmt.setInt(6,book.getCid());
        pstmt.setString(7, book.getIsbn());
        int rows = pstmt.executeUpdate();
        if (rows > 0 ) {
            System.out.println( "operate successfully!" );
        }
        pstmt.close();
        conn.close();
    }

    //common
    public List getCat() throws SQLException{
        List catList=new ArrayList();
        Connection conn=DBUtil.getConn();
        String sql="select * from category";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            catList.add(rs.getString(2));
        }
        DBUtil.close(rs,pstmt,conn);
        return catList;

    }

    //common
    public Book getDetail(String isbn) throws SQLException{
        Book book=new Book();
        Connection conn=DBUtil.getConn();
        String sql="select * from book where ISBN=?";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1,isbn);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            book.setBname(rs.getString(2));
            book.setPublisher(rs.getString(4));
            book.setAuthor(rs.getString(3));
            book.setIsbn(rs.getString(5));
            book.setPrice(rs.getString(6));
            book.setExplanation(rs.getString(7));
            convertCidToCategory(conn,rs.getInt(10),book);
        }
        DBUtil.close(rs,pstmt,conn);
        return book;
    }


    //for admin
    public void delBook(int bid) throws SQLException{
        Connection conn=DBUtil.getConn();
        String sql="delete from book WHERE bid="+bid;
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        int rows = pstmt.executeUpdate();
        if (rows > 0 ) {
            System.out.println( "operate successfully!" );
        }
        pstmt.close();
        conn.close();
    }


    //common
    public List queryUserInfo(int uid) throws SQLException {
        List blist=new ArrayList();
        Connection conn= DBUtil.getConn();
        String sql="select bid,bname,ISBN,author,publisher,borrowtime from book where uid="+uid;
        PreparedStatement pstmt=(PreparedStatement)conn.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        while (rs.next()){
            //System.out.println("rs.next():"+rs.next());
            Book b=new Book();
            b.setBid(rs.getInt(1));
            b.setBname(rs.getString(2));
            b.setIsbn(rs.getString(3));
            b.setAuthor(rs.getString(4));
            b.setPublisher(rs.getString(5));
            b.setBorrowtime(rs.getTimestamp(6));
            blist.add(b);
        }
        DBUtil.close(rs,pstmt,conn);
        //System.out.println("blist==null:"+blist==null);
        return blist;
    }


    //authority:1  5 books, 30days
    //算了，反正小柳给c，判断能否借交给管理员好了。
    public void borrow(int uid,int bid) throws SQLException{
        Connection conn= DBUtil.getConn();
        //System.out.print("uid:"+uid+"  bid:"+bid);
        String sql="update book set uid=?,borrowtime=? where bid=?";
        PreparedStatement pstmt=(PreparedStatement)conn.prepareStatement(sql);
        pstmt.setInt(1,uid);
        pstmt.setTimestamp(2,new java.sql.Timestamp(new java.util.Date().getTime()));
        pstmt.setInt(3,bid);
        int rows=pstmt.executeUpdate();
        if(rows==1){
            System.out.println("borrow success");
        }
        pstmt.close();
        conn.close();
    }

    public void breturn(int bid) throws SQLException{
        Connection conn=DBUtil.getConn();
        String sql="update book set uid=null,borrowtime='0000-00-00 00:00:00' where bid="+bid;
        PreparedStatement pstmt=conn.prepareStatement(sql);
        int rows=pstmt.executeUpdate();
        if(rows==1){
            System.out.println("return success");
        }
        pstmt.close();
        conn.close();
    }
   /* public List<Line> getLines(String tid){
        List<Line> lines=new ArrayList<Line>();
        String sql="select * from line where tid=? order by station_seq";
        List args=new ArrayList();
        args.add(tid);
        List result=query(sql, args);
        Iterator iter=result.iterator();
        while(iter.hasNext()){
            Map m=(Map) iter.next();
            Line l=convertL(m);
            lines.add(l);
        }
        return lines;
    }*/
}
