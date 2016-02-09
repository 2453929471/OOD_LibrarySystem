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
  <title>MyLibrary.Admin</title>

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
      <a class="navbar-brand" href="index">MyLibrary<strong><span class="color">.Admin</span></strong></a> </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="admin/getSub?sub=borrow">BorrowService</a></li>
        <li><a href="admin/getSub?sub=return" >ReturnService</a></li>
        <li><a href="admin/getSub?sub=user">UserManagement</a></li>
        <li><a href="admin/admin">BookManagement</a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>
<br>

<div id="works-section">
  <div class="container"> <!-- Container -->
    <div class="section-title text-center center">
      <h2 style="text-transform: none;">Return Service</h2>
      <hr style="margin:0 auto">
      <br>
    </div>
    <form action="admin/prereturn" method="get">
      <span style="text-transform:none;">  <input type="text" name="uid" value="<s:property value='#request.u.uid'/>" placeholder="UID"/> </span>
      <button type="submit" class="search"><span style="color:white">Query</span></button>
    </form>
    <h5><label>Username:&nbsp;&nbsp;&nbsp;</label><span style="text-transform:none;"><s:property value='#request.u.name'/></span></h5><br>
    <h5><label>Phone:&nbsp;&nbsp;&nbsp;</label><span style="text-transform:none;"><s:property value='#request.u.phone'/></span></h5><br>
    <h5><label>Email: &nbsp;&nbsp;&nbsp;</label> <span style="text-transform:none;"> <s:property value='#request.u.email'/></span></h5><br>
    <h5><label>SFZ: &nbsp;&nbsp;&nbsp;</label> <span style="text-transform:none;"> <s:property value='#request.u.sfz'/></span></h5><br>
    <h5><label>authority: &nbsp;&nbsp;&nbsp;</label><span style="text-transform:none;"> <s:property value='#request.u.bnum'/>|<s:property value='#request.u.authority'/></span></h5><br>
    <h5><label>BorrowedBooks: &nbsp;&nbsp;&nbsp;</label></h5>
    <table class="table table-condensed table-primary table-vertical-center table-thead-simple">
      <thead>
      <tr>
        <th class="center" style="width: 1%">No.</th>
        <th class="center">bid</th>
        <th>Title</th>
        <th class="center">ISBN</th>
        <th class="center">author</th>
        <th class="center">Publisher</th>
        <th class="center">BorrowTime</th>
        <th class="center">Operations</th>
      </tr>
      </thead>
      <tbody>
      <s:iterator value="#request.blist" id="book" status="L">
        <tr class="selectable">
          <td class="center"><s:property value="#L.index+1"/></td>
          <td class="center"><s:property value="#book.bid"/></td>
          <td class="important"><span class="glyphicons up_arrow btn-success btn-action single"><i></i></span><s:property value="#book.bname"/></td>
          <td class="center" ><s:property value="#book.isbn"/></td>
          <td class="center" ><s:property value="#book.author"/></td>
          <td class="center"><s:property value="#book.publisher"/></td>
          <td class="center"><s:property value="#book.borrowtime"/></td>
          <td class="center">
            <a href="admin/return?bid=<s:property value='#book.bid'/>&uid=<s:property value="#request.u.uid"/>" class="btn-action glyphicons eye_open btn-info">return</a>
          </td>
        </tr>
      </s:iterator>
      </tbody>
    </table>
  </div>
</div>

<nav id="footer" style="height:25px;bottom:0px;left:0px;width: 100%;position: fixed">
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
