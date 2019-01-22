<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value=""/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <c:choose>
        <c:when test="${not empty contact}">
            <title>Edit contact ${contact.id}</title>
        </c:when>
        <c:otherwise>
            <title>Create new contact</title>
        </c:otherwise>
    </c:choose>
    <style>
        .button{
            width: 55pt;
        }
    </style>
</head>
<body>
    <form method="post">
        <table align="center" width="40%">
            <tr>
                <td align="right">
                    Number
                </td>
                <td>
                    <input type="tel" name="number" value="${contact.number}" autocomplete="off">
                </td>
            </tr>
            <tr>
                <td width="30%" align="right">
                    Name
                </td>
                <td width="70%">
                    <input style="width: 100%" type="text" name="name" value="${contact.name}" autocomplete="off">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    Details
                    <button type="button" onclick="addDetail()">Add</button>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <table id="detailTable" style="font-size: 10pt"></table>
                    <script>
                        function addDetail(id, key, value){
                            var table = document.getElementById('detailTable');
                            var tr = document.createElement('tr');

                            var buttonTd = document.createElement('td');
                            var btn = document.createElement('button');
                            btn.setAttribute('type', 'button');
                            btn.innerText='-';
                            btn.onclick=function(){
                                table.removeChild(tr);
                            };
                            buttonTd.appendChild(btn);


                            var keyTd = document.createElement('td');
                            var idInput = document.createElement('input');
                            idInput.setAttribute('type', 'hidden');
                            idInput.setAttribute('name', 'detail_id');
                            if (id)idInput.value = id;
                            keyTd.appendChild(idInput);
                            var keyEdit = document.createElement('input');
                            keyEdit.setAttribute('name', 'key');
                            keyEdit.setAttribute('autocomplete', 'off');
                            if(key) keyEdit.value = key;
                            keyTd.appendChild(keyEdit);

                            var valueTd = document.createElement('td');
                            var valueEdit = document.createElement('input');
                            valueEdit.setAttribute('name', 'value');
                            valueEdit.setAttribute('autocomplete', 'off');
                            if(value)valueEdit.value=value;
                            valueTd.appendChild(valueEdit);

                            tr.appendChild(buttonTd);
                            tr.appendChild(keyTd);
                            tr.appendChild(valueTd);

                            table.appendChild(tr);
                        }
                    </script>

                    <c:forEach items="${details}" var="d">
                        <script>
                            addDetail('${d.id}', '${d.key}', '${d.value}');
                        </script>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="hidden" name="id" value="${contact.id}">
                    <button class="button" type="button" onclick="location.href='${CONTEXT}/admin.j'">Cancel</button>
                    <button class="button">Save</button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>