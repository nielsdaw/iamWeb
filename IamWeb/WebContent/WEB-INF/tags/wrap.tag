<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
 		<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
		<link href="${pageContext.request.contextPath}/favicon.ico" rel="shortcut icon" type="image/x-icon" />
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		
		<title>${title}</title>
	</head>
	<body>
		<div class="page-header center-text">
	        <h1 class="text-nd header-size">iamweb</h1>
	        <small class="text-nd">- by niels dawartz</small>
	    </div>
	    <div class="container">
			<jsp:doBody/>
	    </div><!-- /.container -->
	</body>
</html>