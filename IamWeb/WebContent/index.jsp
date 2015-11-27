<%@ taglib prefix="index" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<index:wrap title="iam web">
	<div class="col-md-4 col-md-offset-4">
		<c:if test="${msg != null}"><div class="alert alert-warning"><p class="msg">${msg}</p></div></c:if>
		<c:if test="${msgOK != null}"><div class="alert alert-success"><p class="msg">${msgOK}</p></div></c:if>
		<div class="well">
	        <h1 id="form_header" class="text-nd">login below</h1>
	        <form class="form-signin" action="login" method="POST">
	            <input class="form-control" placeholder="username" type="text" name="username" />
	            <input class="form-control" placeholder="password" type="password" name="password" /><br>
	            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login" />
	        </form>
	    </div>
        <div class="well">
            <p class="text-center">don't have an account yet?</p>
            <form action="create-user" method="GET">
                <button class="btn btn-success btn-block">Create A User</button>
            </form>
        </div>
	</div>
</index:wrap>