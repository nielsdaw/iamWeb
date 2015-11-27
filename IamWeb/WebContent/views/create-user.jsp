<%@ taglib prefix="index" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<index:wrap title="create user">
	<div class="text-center">
		<div class="col-md-4 col-md-offset-4">
			<c:if test="${msgCreate != null}"><div class="alert alert-warning"><p class="msg">${msgCreate}</p></div></c:if>
	        <div class="well">
	        <h1 id="form_header">Create User</h1>
	        <form action="create-user" method=POST class="form-signin">
                <p id="form_p">-please fill in the information below: <br> *A password must be at least be of length 6 
                and consist of a capital letter and a special character </p>
                <input class="form-control" type="text" name="username" placeholder="username">
                <input class="form-control" type="password" name="password" placeholder="password"><br>
                <button class="btn btn-success btn-block btn-lg" type="submit">Create User</button>
       		</form>
	        <form action="login" method="GET" class="form-signin"><br>
	        <button class="btn btn-primary  btn-block btn-lg" type="submit">Go back</button>
	        </form>
	        </div>
		</div>
	</div>
</index:wrap>