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
<!-- advance-search -->
<div id="advance-search" class="text-center">
  <div class="container">
    <div class="section-title text-center center">
      <h2 style="text-transform: none;">Add User Success</h2>
      <hr style="margin:0 auto">
    </div>
    <div class="col-md-8 col-md-offset-2">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              UID: <s:property value="#request.u.uid"/>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              Username: <s:property value="#request.u.name"/>
            </div>
          </div>
          <br>
          <div class="col-md-6">
            <div class="form-group">
              Phone: <s:property value="#request.u.phone"/>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              Email: <s:property value="#request.u.email"/>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              SFZ: <s:property value="#request.u.sfz"/>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              authority: <s:property value="#request.u.authority"/>
            </div>
          </div>
          <br>
        </div>
        <div class="form-group">
        </div>
        <hr>
      <button type="button" class="search" style="margin-right: 5px;" onclick="window.location.href='admin/getSub?sub=addUser'"><span style="color:white">Add User</span></button>
      </div>
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
