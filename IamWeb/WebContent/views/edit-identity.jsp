<%@ taglib prefix="index" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${msgUpdate != null}"><div class="alert alert-success"><p class="msg">${msgUpdate}</p></div></c:if>
	
<index:wrap title="edit identity">
	<div class="text-center">
		<div class="col-md-4 col-md-offset-4">
	        <div class="well">
	        <h1 id="form_header">Edit identity</h1>
	        <form action="update-identity" method=POST class="form-signin">
                <p id="form_p">-change the identities data below</p>
                <input class="form-control" type="text" name="firstname" value="${identity.firstName}">
                <input class="form-control" type="text" name="lastname" value="${identity.lastName }">
                <input class="form-control" type="text" name="email" value="${identity.email}" readonly>
                <input class="form-control" type="date" name="birthdate" value="${identity.birthDate}">
                <br>
                <button class="btn btn-success btn-block btn-lg" type="submit">Update Identity</button>
       		</form>
	        <form action="search-identity" method="GET" class="form-signin"><br>
	        <button class="btn btn-primary  btn-block btn-lg" type="submit">Go back</button>
	        </form>
	        </div>
		</div>
	</div>
</index:wrap>