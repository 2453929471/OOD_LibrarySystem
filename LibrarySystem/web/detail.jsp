<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gao
  Date: 15/10/16
  Time: 下午2:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
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
  <link rel="stylesheet" href="css/style2.css" media="screen" type="text/css" />

  <!--以下是table所需 -->
  <!-- Theme -->
  <link rel="stylesheet" href="css/style4.css" type="text/css"/>
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<!-- Navigation
    ==========================================-->
<nav id="menu" class="navbar navbar-default navbar-fixed-top" style="background-color: #000">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" href="index.html">MyLibrary<strong><span class="color">.</span></strong></a> </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="index.jsp">Home</a></li>
        <li><a href="index.jsp#advance-search" >Advanced Search</a></li>
        <li><a href="index.jsp">Portfolio</a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>
<br>


<!-- Portfolio Section -->
<div id="works-section">
  <div class="container"> <!-- Container -->
    <div class="section-title text-center center">
      <h2 style="text-transform: none;"><s:property value="#request.b.bname"/></h2>
      <hr style="margin:0 auto">
      <br>
    </div>

            <h5><label><strong style="color: #D2527F;">Author:&nbsp;&nbsp;&nbsp;</strong></label><span style="text-transform:none;"> <s:property value="#request.b.author" /></span></h5>
    <h5><label><strong style="color: #D2527F;">Publisher: &nbsp;&nbsp;&nbsp;</strong></label> <span style="text-transform:none;"> <s:property value="#request.b.publisher"/></span></h5>
      <h5><label ><strong style="color: #D2527F;">Category: &nbsp;&nbsp;&nbsp;</strong></label> <span style="text-transform:none;"> <s:property value="#request.b.category"/></span></h5>
        <h5><label ><strong style="color: #D2527F;">Price: &nbsp;&nbsp;&nbsp;</strong></label><span style="text-transform:none;">  <s:property value="#request.b.price"/></span></h5>
          <h5><label><strong style="color: #D2527F;">ISBN:&nbsp;&nbsp;&nbsp;</strong></label> <span style="text-transform:none;"> <s:property value="#request.b.isbn"/></span></h5>
            <h5><label ><strong style="color: #D2527F;">Description:</strong></label><br></h5>
              <h5><span style="text-transform:none;"> <c:out value="${b.explanation}" escapeXml="false"></c:out></span></h5><br>
  </div>
</div>



<nav id="footer" style="height:30px;position:fixed;bottom:0px;left:0px;width: 100%">
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