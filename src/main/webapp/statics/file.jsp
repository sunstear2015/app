<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <script type="text/javascript">
  //window.open("C:\Users\admin\Desktop\app接口.txt");
   </script>
  </head>
  
  <body>
		<form method="post" action="/api/v1/upload/user" enctype="multipart/form-data" >
			<table>
				<tr>
					<tr><td><input type="file"  name="images"  /></td></tr>
                <tr><td><input type="text"  name="uuid" value="8ae076aa42d540bf0142d6f5eca7759d" /></td>
                <td><input value="bce8ce2e184326a7fe65f8bef63ef2d9"/></td>
                </tr>

					<tr><td><input type="submit" value="提交"></td>
					<!-- <tr><td><input type="text"  name="imgurls"  value="upload/images/1421825958681_say.png"/></td></tr>
					<tr><td><input type="text"  name="userid"  value="367e12f14abcf795014ac240e2b9002c"/></td></tr>
					<tr><td><input type="submit" value="提交"></td> -->
				</tr>
			</table>
		</form>

</body></html>