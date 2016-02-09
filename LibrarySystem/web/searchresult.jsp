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
  <title>MyLibrary</title>
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
      <a class="navbar-brand" href="index">MyLibrary<strong><span class="color">.</span></strong></a> </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="index">Home</a></li>
        <li><a href="index#advance-search" class="page-scroll">Advanced Search</a></li>
        <li><a href="queryUserInfo?uid=${sessionScope.uid}" class="page-scroll">Profile</a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>
<br>


<!-- Portfolio Section -->
<div id="works-section">
  <div class="container" style="height: 100%"> <!-- Container -->
    <div class="section-title text-center center">
      <h2>Search <strong>Results</strong></h2>
        <hr style="margin:0 auto">
      <br>
      <p>for Books:
        <% if (request.getParameter("bname1")!=null){out.print(request.getParameter("bname1"));out.print("&nbsp;");}
          if (request.getParameter("bname")!=null){out.print(request.getParameter("bname"));out.print("&nbsp;");}
          if (request.getParameter("isbn")!=null){out.print(request.getParameter("isbn"));out.print("&nbsp;");}
          if (request.getParameter("author")!=null){out.print(request.getParameter("author"));out.print("&nbsp;");}
          if (request.getParameter("category")!=null){out.print(request.getParameter("category"));out.print("&nbsp;");}

        %>
    </div>
      <div class="portfolio-items" onload="detail()">
        <table class="table table-condensed table-primary table-vertical-center table-thead-simple">

          <thead>
          <tr>
            <th class="center" style="width: 1%">No.</th>
            <th class="center">Title</th>
            <th class="center">Author</th>
            <th class="center">Publisher</th>
            <th class="center">Category</th>
            <th class="center">Amount</th>
            <th class="center">Operation</th>
          </tr>
          </thead>
          <tbody>
          <s:iterator value="#request.blist" id="book" status="L">
          <tr class="selectable">
            <td class="center"><s:property value="#L.index+1"/></td>
            <td class="important"><span class="glyphicons up_arrow btn-success btn-action single"><i></i></span><s:property value="#book.bname"/></td>
            <td class="center"><s:property value="#book.author"/></td>
            <td class="center"><s:property value="#book.publisher"/></td>
            <td class="center"><s:property value="#book.category"/></td>
            <td class="center" style="max-width: 10px;"><s:property value="#book.num"/></td>
            <td class="center" style="max-width: 10px;"><a href="detail?isbn=<s:property value='#book.isbn'/>">detail</a> </td>
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

</div>

<nav id="footer" style="height:30px;position:fixed;bottom:0px;left:0px;width: 100%" >
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
