<%@ taglib prefix="index" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<index:wrap title="create user">
	<div class="text-center">
    	<div class="col-md-4 col-md-offset-4">
    		<c:if test="${msgCreate != null}"><div class="alert alert-success"><p class="msg">${msgCreate}</p></div></c:if>
	        <c:if test="${msg != null}"><div class="alert alert-danger"><p class="msg">${msg}</p></div></c:if>
              <div class="well">
              <h1 id="form_header">Create new identity</h1>
              <form method="POST" action="create-identity" class="form-signin">
                      <p id="form_p">Fill in the information below: </p>
                      <input class="form-control" type="text" name="fname" placeholder="Firstname">
                      <input class="form-control" type="text" name="lname" placeholder="Lastname">
                      <input class="form-control" type="email" name="email" placeholder="Email">
                      <input class="form-control" type="date" name="date" placeholder="Birth-date: yyyy-mm-dd">
                      <br>
                      <button class="btn btn-success btn-block btn-lg" type="submit">Create new Identity</button>
              </form>
              <form action="main" class="form-signin" method="GET" ><br>
              <button class="btn btn-primary  btn-block btn-lg" type="submit">Go back</button><br>
              </form>
              </div>
         </div>
    </div>
</index:wrap>