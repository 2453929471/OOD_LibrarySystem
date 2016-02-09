<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: gao
  Date: 15/10/16
  Time: 下午2:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <base href=" <%=basePath%>">
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Creativ</title>

  <!-- Bootstrap -->
  <link rel="stylesheet" type="text/css"  href="css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="css/font-awesome.css">

  <!-- Slider
      ================================================== -->
  <link href="css/owl.carousel.css" rel="stylesheet" media="screen">
  <link href="css/owl.theme.css" rel="stylesheet" media="screen">

  <!-- Stylesheet
      ================================================== -->
  <link rel="stylesheet" type="text/css"  href="css/style.css">
  <link rel="stylesheet" type="text/css" href="css/responsive.css">
  <link rel="stylesheet" type="text/css" href="css/prettyPhoto.css">
  <link href='http://fonts.useso.com/css?family=Lato:400,700,900,300' rel='stylesheet' type='text/css'>
  <link href='http://fonts.useso.com/css?family=Open+Sans:400,700,800,600,300' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="css/normalize.css">
    <style type="text/css">
        .search{width: 80px;
            line-height: 35px;
            text-align: center;
            font-weight: bold;
            border-radius: 5px;
            margin:0 20px 20px 0;
            position: relative;
            overflow: hidden;
            border:0px;
            background:rgba(113, 197, 158, 0.99);}
        .search:hover{background: rgba(99, 172, 138, 0.99);}

    </style>

  <!--以下是table所需 -->
  <!-- Theme -->
  <link rel="stylesheet" href="css/style4.css" type="text/css"/>
</head>
<body>
<!-- Navigation
    ==========================================-->
<nav id="menu" class="navbar navbar-default navbar-fixed-top" style="background-color: #000">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" href="index.html">MyLibrary<strong><span class="color">.Admin</span></strong></a> </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="borrow.jsp">BorrowService</a></li>
        <li><a href="return.jsp" >ReturnService</a></li>
          <li><a href="usermanagement.jsp">UserManagement</a></li>
        <li><a href="admin.action">BookManagement</a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>
<br>

<!-- advance-search -->
<div id="advance-search" class="text-center">
  <div class="container">
      <h3>Search</h3>
    <div class="col-md-8 col-md-offset-2">
      <form action="admin/advanceSearch.action" method="post">
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
              <input type="text" name="bname" class="form-control" placeholder="BookName">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
              <input type="text" name="isbn" class="form-control" placeholder="ISBN">
                </div>
            </div>
          <br>
            <div class="col-md-6">
                <div class="form-group">
              <input type="text" name="author" class="form-control" placeholder="author">
                </div>
            </div>
          <div class="col-md-6">
            <div class="form-group">
              <input type="text" name="publisher" class="form-control" placeholder="publisher">
            </div>
          </div>
          <br>
          <div class="col-md-6">
            <div class="form-group">
                <s:select list="#request.catList" name="category" class="form-control"></s:select>
            </div>
          </div>
        </div>
        <div class="form-group">
        </div>
        <hr>
        <button type="submit" class="search"><span style="color:white">Search</span></button>
      </form>

    </div>
      <div>      <button type="button" class="search" onclick="window.location.href='admin/preadd'"><span style="color:white">Add Book</span></button>
      </div>
      <table class="table table-condensed table-primary table-vertical-center table-thead-simple">
          <thead>
          <tr>
              <th class="center" style="width: 1%">No.</th>
              <th>Title</th>
              <th class="center">Author</th>
              <th class="center">Publisher</th>
              <th class="center">Category</th>
              <th class="center">Operations</th>
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
                  <td class="center">
                      <a href="admin/detail?isbn=<s:property value='#book.isbn'/>" class="btn-action glyphicons eye_open btn-info">detail</a>|
                      <a href="admin/preupd?isbn=<s:property value='#book.isbn'/>" class="btn-action glyphicons pencil btn-success">update</a>|
                      <a href="admin/del?bid=<s:property value='#book.bid'/>" class="btn-action glyphicons remove_2 btn-danger">delete</a>
                  </td>
              </tr>
          </s:iterator>
          </tbody>
      </table>

      <div style="position:relative; float: right;font-size: 11pt" >
          <s:url id="url_pre" ><s:param name="currentPage" value="currentPage-1"></s:param></s:url>
          <s:url id="url_first" ><s:param name="currentPage" value="1"></s:param></s:url>
          <s:url id="url_last" ><s:param name="currentPage" value="totalPage"></s:param></s:url>
          <s:url id="url_next" ><s:param name="currentPage" value="currentPage+1"></s:param></s:url>
          <s:if test="%{(totalPage>1)}">
              <s:a href="%{url_first}">首页</s:a>
              <s:if test="currentPage!=1"><s:a href="%{url_pre}" >上一页</s:a></s:if>
              <s:else></s:else>
              <s:if test="currentPage!=totalPage"><s:a href="%{url_next}" >下一页</s:a></s:if>
              <s:else></s:else>
              <s:a href="%{url_last}">尾页</s:a>
          </s:if>
          <span style="font-size: 9pt">第${currentPage}页,共${totalPage}页,共${totalSize}条</span>
      </div>
  </div>
</div>


<nav id="footer" style="height:25px;bottom:0px;left:0px;width: 100%;position: relative">
  <div class="container" >
    <div class="pull-right fnav">
    </div>
  </div>
</nav>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://ajax.useso.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.1.11.1.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/SmoothScroll.js"></script>
<script type="text/javascript" src="js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="js/jquery.isotope.js"></script>
<script src="js/owl.carousel.js"></script>

<!-- Javascripts
    ================================================== -->
<script type="text/javascript" src="js/main.js"></script>
</body>
</html>
