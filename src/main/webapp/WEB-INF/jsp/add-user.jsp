<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<title>Create User</title>
<style type="text/css">
.container {
	width: 100%;
	height: 100%
}

table {
	margin: 0 auto;
}

.error {
	color: #ff0000;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="container">
		<h1 align="center">Create User</h1>
		
		<table>
			<tr>
				<td><a href="/sbf/search-user">Search User</a></td>
			</tr>
		</table>


		<form:form modelAttribute="user" method="POST"
			action="/sbf/add-user">
			<table>
				<tr>
					<td><form:label path="firstName">First Name</form:label></td>
					<td><form:input path="firstName" /></td>
					<td><form:errors path="firstName" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="lastName">Last Name</form:label></td>
					<td><form:input path="lastName" /></td>
					<td><form:errors path="lastName" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Save"></td>
				</tr>

				<c:if test="${saved}">
					<tr>
						<td colspan="2"><c:out value="${savedSuccessMsg}" /></td>
					</tr>
				</c:if>

			</table>



		</form:form>
	</div>
</body>

</html>
