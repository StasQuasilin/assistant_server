<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value=""/>
<fmt:setBundle basename="messages"/>
<html>
<head>
</head>
    <body >
        <div style="width: 100%; height: 100vh;">
            <form method="post">
                <table align="center" valign="middle">
                    <tr>
                        <td>
                            Login
                        </td>
                        <td>
                            <input type="text" name="login">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password
                        </td>
                        <td>
                            <input type="password" name="password">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <button>Submit</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>