<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
	<title>Search User</title>
	<style type="text/css">
		.container {
			width:100%;
			height:100%
		}
		table {
			margin: 0 auto;
		}
		.results,.results td, th {
   				border: 1px solid black;
    			border-collapse: collapse;
    			padding:5px;
    			
		}
	</style>
</head>
<body>
  <div class="container">
	 
	  <form method="POST" action="/sbf/search-user">
	  	<table>
	  		<tr>
	  			<td><input type="text" name="term" value="${term}"></td>
	  			<td><input type="submit" value="Search"></td>
	  			<td><a href="/sbf/add-user">Add User</a></td>
	  		</tr>
	  		
	  	</table>
	  </form>
	  <c:if test="${not empty users}">
		  <table class="results">
				  <tr>
				    <th>First Name</th>
				    <th>Last Name</th>
				  </tr>
				  
				  <c:forEach var="user" items="${users}">
				  	<tr>
   						<td><c:out value="${user.firstName}"/></td>
				        <td><c:out value="${user.lastName}"/></td>
				     </tr>
				  </c:forEach>
		  </table>

			<c:if test="${hits gt 0}">
				<table>
					<tr>
						<td>
							<c:if test="${!firstPage}">
								<a href="/sbf/search-user?term=${term}&page=${prevPageNo}">Prev</a>
							</c:if>
						</td>
						<td>
							<c:if test="${!lastPage && nextPage}">
								<a href="/sbf/search-user?term=${term}&page=${nextPageNo}">Next</a>
							</c:if>
						</td>
					</tr>
				</table>
			</c:if>
			
		</c:if>
  </div>
</body>

</html>
