<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- reference to the css file -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<%-- <link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" /> --%>
</head>
<body>
	<div id="wrapper">

		<div id="header">
			<h2>CRM- Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">


			<!--  put new button: Add Customer -->
			<input class="add-button" type="button" value="Add Customer"
				id="btnAdd">

			<!-- add out html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>

				</tr>
				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customers}">
					<!-- construct an "update" link with customer id  -->

					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id }"></c:param>
					</c:url>


					<!-- construct an "delete" link with customer id  -->

					<c:url var="deleteLink" value="/customer/showFormForDelete">
						<c:param name="customerId" value="${tempCustomer.id }"></c:param>
					</c:url>

					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>

						<td><a href="${updateLink}">Update</a>|
						<td><a id="deleteLink" href="${deleteLink}" onclick="if (!(confirm('Are you sure to delete this customer?'))) return false">Delete</a>
					</tr>


				</c:forEach>

			</table>

		</div>

	</div>

	<script type="text/javascript">
		window.onload = function() {
			document.getElementById("btnAdd").onclick = function() {

				location.href = "showFormForAdd";
				return false;
			};

			/* document.getElementById("deleteLink").onclick = function() {
				alert();
				if (!(confirm('Are you sure to delete this customer?')))
					return false;
			}; */
		}
	</script>



</body>
</html>
