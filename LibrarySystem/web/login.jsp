<%--
  Created by IntelliJ IDEA.
  User: gao
  Date: 15/10/19
  Time: 下午2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>MyLibrary</title>
  <link href="Wopop_files/style_log.css" rel="stylesheet" type="text/css">
</head>

<body class="login"  mycollectionplug="bind">
<div class="login_m">
  <div class="login_logo"><a href="index"><img src="Wopop_files/logo.png" width="250" height="70"></a></div>
  <div class="login_boder">

    <div class="login_padding" id="login_model">
    <form action="login" method="post" name="loginForm">
      <h2>UID</h2>
      <label>
        <input type="text" name="uid" id="username" class="txt_input txt_input2" onfocus="if (value ==&#39;Your name&#39;){value =&#39;&#39;}" onblur="if (value ==&#39;&#39;){value=&#39;Your name&#39;}" value="Your name"/>
      </label>
      <h2>PASSWORD</h2>
      <label>
        <input type="password" name="pwd" id="userpwd" class="txt_input" onfocus="if (value ==&#39;******&#39;){value =&#39;&#39;}" onblur="if (value ==&#39;&#39;){value=&#39;******&#39;}" value="******"/>
      </label>
      <p class="forgot"><a id="iforget" href="index">Cancel</a></p>
      <div class="rem_sub">
        <label>
          <input type="button" class="sub_button" value="SIGN-IN" style="opacity: 0.7;" onclick="validate()">
        </label>
      </div>
    </form>
    </div>
</div><!--login_m end-->
</div>
<script type="text/javascript">
  function validate(){
    if(isNaN(document.getElementById("username").value)||document.getElementById("username").value.length>9){
      alert("UID can only accept numbers less than 10 bits;");
      return false;
    }else{
      document.loginForm.submit();
    }
  }

</script>
</body>
</html>

