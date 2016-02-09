<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gao
  Date: 15/10/19
  Time: 下午9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>MyLibraryAdmin</title>
  <link type="text/css" rel="stylesheet" href="/LibrarySystem/css/style_admin.css" />
  <script type="text/javascript" src="/LibrarySystem/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="/LibrarySystem/js/admin_menu.js"></script>
  <script type="text/javascript" charset="utf-8" src="/LibrarySystem/utf8-jsp/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="/LibrarySystem/utf8-jsp/ueditor.all.min.js"> </script>
  <script type="text/javascript" charset="utf-8" src="/LibrarySystem/utf8-jsp/lang/en/en.js"> </script>
  <script type="text/javascript">
    var ue=UE.getEditor('myEditor');
    function setExplanation(){
      document.getElementById("explanation").value=ue.getContent();
    }
  </script>
</head>


<body>
<div class="top"></div>
<div id="header">
  <div class="logo">MyLibraryAdmin<strong><span style="color: #D2527F">.</span></strong></div>
  <div class="navigation">
    <ul>
      <li>Welcome!</li>
      <li><a href="">张山</a></li>
      <li><a href="">修改密码</a></li>
      <li><a href="">设置</a></li>
      <li><a href="">退出</a></li>
    </ul>
  </div>
</div>
<div id="content">
  <div class="left_menu">
    <ul id="nav_dot">
      <li>
        <a href="borrow.jsp"><h4>BorrowService</h4></a>
      </li>
      <li>
        <a href="return.jsp" ><h4>ReturnService</h4></a>
      </li>
      <li>
        <h4>BookManagement</h4>
        <div class="list-item none">
          <a href='z_searchBook.jsp'>QueryBook</a>
          <a href='z_addBook.jsp'>AddBook</a>
          <a href='z_updBook.jsp'>UpdateBook</a>
        </div>
      </li>
    </ul>
  </div>
  <div class="m-right">
    <div class="right-nav">
      <ul>
        <li><a href="#">BookManagement</a></li>
        <li>></li>
        <li><a href="#">SearchBook</a></li>
      </ul>
    </div>
    <div class="main">
      <form action="advanceSearch.action" method="post">
              <input type="text" name="bname" placeholder="BookName">
              <input type="text" name="ISBN" placeholder="ISBN">
              <input type="text" name="author" placeholder="author">
              <input type="text" name="publisher" placeholder="publisher">
              <select name="cid">
                <option value=1>Arts & Photography</option>
                <option value=2>Biographies & Memoirs</option>
                <option value=3>Business & Money</option>
                <option value=4>Calendars</option>
                <option value=8>Computers & Technology</option>
              </select>
              <button type="submit" class="btn btn-default">Search</button>
      </form>

      <table>
        <thead>
        <tr>
          <th class="center" style="width: 1%">No.</th>
          <th>Title</th>
          <th class="center">Author</th>
          <th class="center">Publisher</th>
          <th class="center">Category</th>
          <th>Price</th>
          <th class="center">ISBN</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.blist" id="book" status="L">
          <tr class="selectable">
            <td class="center"><s:property value="#L.index+1"/></td>
            <td class="important"><span class="glyphicons up_arrow btn-success btn-action single"><i></i></span><s:property value="#book.bname"/></td>
            <td class="center" style="max-width: 15px;"><s:property value="#book.author"/></td>
            <td class="center"><s:property value="#book.publisher"/></td>
            <td class="center"><s:property value="#book.category"/></td>
            <td class="center"><s:property value="#book.price"/></td>
            <td class="center"><s:property value="#book.isbn"/></td>
          </tr>
        </s:iterator>
        </tbody>
      </table>
      <br>
      <br>
      <br>
    </div>
  </div>
</div>
<div class="bottom"></div>
<div id="footer"><p></p></div>
<script>navList(12);</script>
</body>
</html>
