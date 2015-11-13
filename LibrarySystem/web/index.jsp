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
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Creativ</title>
  <meta name="description" content="">
  <meta name="author" content="">


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
  <script type="text/javascript" src="js/modernizr.custom.js"></script>
  <script src="js/modernizr.js"></script>

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
<nav id="menu" class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand" href="index.html">MyLibrary<strong><span class="color">.</span></strong></a> </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#home" class="page-scroll">Home</a></li>
        <li><a href="#advance-search" class="page-scroll">Advanced Search</a></li>
        <li><a href="#works-section" class="page-scroll">Portfolio</a></li>
        <li><a href="#about-section" class="page-scroll">About</a></li>
        <li><a href="#team-section" class="page-scroll">Team</a></li>
        <li><a href="#testimonials-section" class="page-scroll">Testimonials</a></li>
        <li><a href="#contact-section" class="page-scroll">Contact</a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>

<!-- Header -->
<header class="text-center" name="home">
  <div class="intro-text">
    <h1>Welcome to <strong><span class="color">MyLibrary</span></strong></h1>
    <p>Hope our website deliver you with great user experiences.</p>
    <form class="search" action="search.action" method="get">
      <input name="bname1" type="search" placeholder="Input title here..." required>
      <button type="submit">Search</button>
    </form>
    <a href="#advance-search" class="page-scroll"><strong>Advanced Search</strong></a>
  </div>

</header>

<!-- advance-search -->
<div id="advance-search" class="text-center">
  <div class="container">
    <div class="section-title center">
      <h2>Advanced Search</h2>
      <hr>
      <p>Attention: we only accpet ISBN with 13 numbers. And we support only accurate search.</p>
    </div>
    <div class="col-md-8 col-md-offset-2">
      <form action="advanceSearch.action" method="post">
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
        <button type="submit" class="btn btn-default">Search</button>
      </form>
    </div>
  </div>
</div>
</div>


<nav id="footer">
  <div class="container">
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



