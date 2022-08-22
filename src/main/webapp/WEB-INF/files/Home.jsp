<%@ taglib uri="http://www.springframework.org/tags" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<style>
h1 {
	text-align: left;
	color: blue;
	padding: 2%;
	border: 1opx 5px;
	font-size: small;
}

body.div {
	color: green;
	text-align: center;
}

body.table {
	color: green;
	text-align: center;
}
<
script


 


type




="
text
/




javascript




"
>
<
button


 


onclick




="
return


 


confirm




(




'
Are


 


you


 


sure


 


you


 


want


 


to


 


Delete




?'
)




;"
id




="
btnDelete




"
>
DELETE




</
button
>
</
script
>
</style>


<body>
	<h2 align="center">Welcome To Product Management</h2>
	<div class="forms">
		<form action="search" method="post">
			search<input type="search" name="search"> <input
				type="submit" name="submit">
		</form>
	</div>
	<h1></h1>


	<table border="3">

		<tr>
			<th>
			<td>Id</td>
			<td>Name</td>
			<td>price</td>
			<td>catagory</td>
			</th>
		</tr>

		<jstl:forEach items="${values}" var="localer">
			<tr>
				<td></td>
				<td>${localer.id}</td>
				<td>${localer.name}</td>
				<td>${localer.price}</td>
				<td>${localer.category}</td>
				<td><a href="add?id=${localer.id}">Edit</a></td>
				<td><a href="deletet?id=${localer.id}"
					onclick="return confirm('Are you sure you want to Delete');">Remove</a></td>
			</tr>
		</jstl:forEach>


		<jstl:if test="${currentPage>=1}">
			<a href=PAGE/1>First</a>&nbsp;&nbsp;&nbsp;
        </jstl:if>

		<jstl:if test="${currentPage>=1}">
			<a href="PAGE/${currentPage-1}">Prev</a>&nbsp;&nbsp;&nbsp;
        </jstl:if>


		<jstl:forEach var="i" begin="1" end="${totalPages}">
			<a href="PAGE/${i}">${i}</a>&nbsp;&nbsp;
      </jstl:forEach>


		<jstl:if test="${currentPage<totalPages}">
			<a href="PAGE/${currentPage+1}">Next</a> &nbsp;&nbsp;&nbsp;
        </jstl:if>

		<jstl:if test="${currentPage<totalPages}">
			<a href="PAGE/${totalPages}">Last</a> &nbsp;&nbsp;&nbsp;
        </jstl:if>

		<h4>
			<a href="add?id=0">Add New Item</a>
		</h4>

		</div>



	</table>


</body>

