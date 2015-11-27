<%@ taglib prefix="index" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<index:wrap title="main">
	<div class="text-center">
		<h1 class="text-nd">welcome to the iamweb - have fun!</h1>
		<div class="col-md-4  col-md-offset-4">
			<div class="well">
				<p class="text-nd">you are currently logged in as: <span style="color:#008547;">${user.userName}</span></p>
				<a href="logout">Logout</a>
			</div>
		</div>
		
	</div>
	<div class="row">
		<div class="col-md-4 col-md-offset-2">
	          <img class="img-circle" src="${pageContext.request.contextPath}/img/identity.png" alt="Generic placeholder image" width="140" height="140">
	          <h2 class="text-nd">create Identity</h2>
	          <p class="text-nd text-size-nd">here you can create as many Identites as you like<br> - just remember that an email is unique!</p>
	          <p>
	          	<form class="form-signin" action="create-identity" method="GET">
				<input class="btn btn-default" type="submit" value="Create Identity" />
    			</form>
    		  </p>
	    </div>
	   	<div class="col-md-4 col-md-offset-1">
	          <img class="img-circle" src="${pageContext.request.contextPath}/img/search.jpg" alt="Generic placeholder image" width="140" height="140">
	          <h2 class="text-nd">search Identity</h2>
	          <p class="text-nd text-size-nd">here you can search for as many Identites as you like<br> - you can even <span style="color:#f7c460">edit</span> and/or <span style="color:#58011c">delete</span> them!</p>
	          <p>
          	   	<form class="form-signin" action="search-identity" method="GET">
					<input class="btn btn-default" type="submit" value="Search Identity" />
   				</form>
	          </p>
	    </div>
    </div>
</index:wrap>