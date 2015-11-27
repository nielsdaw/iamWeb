<%@ taglib prefix="index" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<index:wrap title="create user">
	<div class="row">
  		<div class="col-xs-6 col-md-4">
  		<div class="text-center">
              <div class="well">
	             <h1 id="form_header">Search Identity</h1>
	             <form method="POST" action="search-identity" class="form-signin">
	                     <p id="form_p">Choose your search criteria: </p>
	                     <input class="form-control" type="text" name="fname" placeholder="Firstname">
	                     <input class="form-control" type="text" name="lname" placeholder="Lastname">
	                     <input class="form-control" type="text" name="email" placeholder="Email">
	                     <br>
	                     <button class="btn btn-success btn-block btn-lg" type="submit">Search for Identities</button>
	             </form>
	             <form action="main" class="form-signin"><br>
	             <button class="btn btn-primary  btn-block btn-lg" type="submit">Go back</button><br>
	             </form>
              </div>
   		 </div>
  		
  		</div>
  		<div class="col-xs-12 col-sm-6 col-md-8">
  			<div class="well">
  			<h1>Search Results</h1>
				<table class="table">
					<tr>
						<th>FirstName</th>
						<th>LastName</th>
						<th>Email</th>
						<th>Birthday</th>
						<th></th>
						<th></th>
					</tr>
					<c:if test="${msgSearch !=null }">
						<span class="label label-info">${msgSearch}</span>
					</c:if>
					<c:if test="${searchResult != null}">
						<c:forEach var="item" items="${searchResult}" varStatus="i">
						<tr>
							<td>${item.firstName}</td>
							<td>${item.lastName}</td>
							<td>${item.email}</td>
							<td>${item.birthDate}</td>
							<td>
								<form method="POST" action="edit">
									<input type="hidden" value="${item.firstName}" name="firstname">
			                     	<input type="hidden" value="${item.lastName}" name="lastname">
			                     	<input type="hidden" value="${item.email}" name="email">
			                     	<input type="hidden" value="${item.birthDate}" name="birthdate">
			                     	<input type="hidden" value="${item.id}" name="id">
									<button class="btn-info btn-xs">Edit</button>
								</form>
							</td>
							<td>
								<form  method="POST" action="delete">
									<input type="hidden" value="${item.id}" name="id">
									<button class="btn btn-danger btn-xs">Delete</button>
								</form>
							</td>
						</tr>
						</c:forEach>
					</c:if>
				</table>

    		
    		</div>
  		
  		</div>
	</div>
	

</index:wrap>