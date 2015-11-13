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
          <a href=''>QueryBook</a>
          <a href='addBook2.jsp'>AddBook</a>
          <a href='updBook.jsp'>UpdateBook</a>
          <a href=''>DeleteBook</a>
        </div>
      </li>
    </ul>
  </div>
  <div class="m-right">
    <div class="right-nav">
      <ul>
        <li>BookManagement</li>
        <li>></li>
        <li>AddBook</li>
      </ul>
    </div>
    <div class="main">
      <form action="admin/crawler" method="post">
        ISBN:<input type="text" name="isbn1" value="<s:property value='#request.book.isbn'/>"/> <input type="submit" value="search"/>
      </form>
      <form action="admin/addBook" method="post">
        <input type="hidden" name="isbn" value="<s:property value='#request.book.isbn'/>">
        Title: <input type="text" name="bname" value="<s:property value='#request.book.bname'/>" /><br>
        Category:<s:select name="category" list="{'Arts & Photography','Biographies & Memoirs ','Business & Money','Calendars','Computers & Technology'}" value="#request.book.category"/><br>
        <s:select list="#request.catList" value="#request.book.categoty"></s:select>
        Author: <input type="text" name="author" value="<s:property value='#request.book.author'/>" /><br>
        Publisher:<input type="text" name="publisher" value="<s:property value='#request.book.publisher'/>" /><br>
        Price: <input type="text" name="price" value="<s:property value='#request.book.price'/>" /><br>
        Explanation: <input type="hidden" name="explanation" id="explanation">
        <br>
        <script name="myEditor" id="myEditor" type="text/plain" style="width:600px;height:100px"><c:out value="${book.explanation}" escapeXml="false"></c:out></script>
        <input type="submit" value="add book" onclick="setExplanation()"/>
      </form>
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
