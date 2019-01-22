<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value=""/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Admin</title>
    <style>
        *{
            user-select: none;
        }
        .clickable {
            padding: 1pt;
        }
        .clickable:hover{
            border: solid gray 1pt;
            background: lightgray;
            padding: 0;
        }
    </style>
</head>
<body>
    <table width="60%" align="center">
        <tr>
            <td align="center">
                Users
                <button type="button">
                    Add
                </button>
            </td>
            <td align="center">
                Contacts
                <button type="button" onclick="location.href='${CONTEXT}/contact/edit.j'">
                    Add
                </button>
            </td>
        </tr>
        <tr>
            <td>
                <div style="border: solid gray 1pt">
                    <table width="100%">
                        <c:forEach items="${users}" var="u">
                            <tr>
                                <td>
                                    <span class="clickable" style="display: block; width: 100%"> ${u.value}</span>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </td>
            <td>
                <div style="border: solid gray 1pt">
                    <table width="100%">
                        <c:forEach items="${contacts}" var="c">
                            <tr class="clickable" onclick="location.href='${CONTEXT}/contact/edit.j?id=${c.id}'">
                                <td>
                                    ${c.number}
                                </td>
                                <td style="text-align: right">
                                    <span>${c.name}</span>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</body>
</html>