<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<body>
<h2>Product Management </h2>
      <form:form method = "POST" action = "add" modelAttribute="form">
         <table>
        
          <tr>
                <td><form:input type="hidden" path="id"/></td>
            </tr>
            <tr>
               <td><form:label path = "name">Name</form:label></td>
               <td><form:input path = "name" /></td>
            </tr>
            <tr>
               <td><form:label path = "price">price</form:label></td>
               <td><form:input path = "price" /></td>
            </tr>
            <tr>
               <td><form:label path = "category">category</form:label></td>
               <td><form:input path = "category" /></td>
            </tr>
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
               </tr>
         </table>  
      </form:form>
      <a href="welcome">Home</a>
</body>
</html>