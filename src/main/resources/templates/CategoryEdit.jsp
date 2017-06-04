<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<head xmlns:c="http://www.w3.org/1999/XSL/Transform" xmlns:form="http://www.w3.org/1999/xhtml"
      xmlns:c="http://www.hibernate.org/xsd/hibernate-mapping"
      xmlns:c="http://www.hibernate.org/xsd/orm/hbm" xmlns:c="http://www.springframework.org/schema/beans"
      xmlns:c="http://www.springframework.org/schema/util">

    <title>Edit</title>
    <style type="text/css">.hidden {display: none;}</style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script type="text/javascript">
        $(function() {

            // Start indexing at the size of the current list
            var index =${fn:length(categories.subcategoriesByCatId)};

            // Add a new Employee
            $("#add").off("click").on("click", function() {
                $(this).before(function() {
                    var html = '<div id="categoriesentity' + index + '.wrapper" class="hidden">';
                    html += '<input type="text" id="subcategoriesentity' + index + '.name" name="subcategoriesentity[' + index + '].name" />';
                    html += '<a href="#" class="subcategoriesentity.remove" data-index="' + index + '">remove</a>';
                    html += "</div>";
                    return html;
                });
                $("#subcategoriesentity" + index + "\\.wrapper").show();
                index++;
                return false;
            });

            // Remove an Employee
            $("a.employees\\.remove").off("click").on("click", function() {
                var index2remove = $(this).data("index");
                $("#employees" + index2remove + "\\.wrapper").hide();
                $("#employees" + index2remove + "\\.remove").val("1");
                return false;
            });

        });
    </script>

</head>
<body>

<c:choose>
    <c:when test="${type eq 'create'}"><c:set var="actionUrl" value="employer/create" /></c:when>
    <c:otherwise><c:set var="actionUrl" value="employer/update/${employer.id}" /></c:otherwise>
</c:choose>

<form action="${actionUrl}" modelAttribute="categoriesentity" method="POST" name="categoriesentity">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name" /><form:errors path="name" /></td>
        </tr>
        <tr>
            <td>Subcategories</td>
            <td>
                <c:forEach items="${CategoriesEntity.SubcategoriesEntity}" varStatus="loop">
                    <!-- Add a wrapping div -->
                    <c:choose>
                        <c:when test="${categories.subcategoriesentity[loop.index].remove eq 1}">
                            <div id="subcategories${loop.index}.wrapper" class="hidden">
                        </c:when>
                        <c:otherwise>
                            <div id="subcategories${loop.index}.wrapper">
                        </c:otherwise>
                    </c:choose>
                    <!-- Generate the fields -->
                    <form:input path="subcategories[${loop.index}].name" />
                    <!-- Add the remove flag -->
                    <c:choose>
                        <c:when test="${subcategories[loop.index].remove eq 1}"><c:set var="hiddenValue" value="1" /></c:when>
                        <c:otherwise><c:set var="hiddenValue" value="0" /></c:otherwise>
                    </c:choose>
                    <form:hidden path="subcategories[${loop.index}].remove" value="${hiddenValue}" />
                    <!-- Add a link to remove the Employee -->
                    <a href="#" class="subcategories.remove" data-index="${loop.index}">remove</a>
                </c:forEach>
                <button id="add" type="button">add</button>
            </td>
        </tr>
    </table>
    <button type="submit">OK</button>
</form>

</body>
</html>