<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<body>

	<h2 align="center">Product Management</h2>

	<table border="3">
		<tr>
			<th>
			<td>Id</td>
			<td>Name</td>
			<td>price</td>
			<td>catagory</td>
			</th>
		</tr>

		<jstl:forEach items="${category}" var="localer">
			<tr>
				<td></td>
				<td>${localer.id}</td>
				<td>${localer.name}</td>
				<td>${localer.price}</td>
				<td>${localer.category}</td>
			</tr>
		</jstl:forEach>

	</table>
	<a href="welcome">Home</a>
</body>