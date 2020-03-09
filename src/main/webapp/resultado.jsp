<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- 
        <META http-equiv="Pragma" content="no cache">
        <META http-equiv="Cache-control" content="no cache"> 
        <META http-equiv="Expires" content="0"> 
        --%>
        <title></title>
    </head>
    <body>
        <div>
            <h2>Seguro que tus mejores amigos son:</h2>
            <c:forEach items="${amigos}" var="amg">
                <div>
                    <p>${amg.value}</p>
                    <%-- <p><img src="${amg.value.profileImageURL}"/> ${amg.} </p>        --%>
                </div>
            </c:forEach>
        </div>

        <div>
            <h2>Parece que vives en o frecuentas los siguientes lugares:</h2>
            <c:forEach items="${domicilio}" var="dmc">
                <div>
                    <p>${dmc.value}</p>
                </div>
            </c:forEach>
        </div>

        <div>
            <h2>Los dispositivos desde los que usas Twitter:</h2>
            <c:forEach items="${dispositivos}" var="dsp">
                <div>
                    <p>${dsp.key}: ${dsp.value}</p>
                </div>
            </c:forEach>
        </div>

        <div>
            <h2>Las horas a las que twiteas:</h2>
            <c:forEach items="${horas}" var="hrs" varStatus="iteracion">
                <div>
                    <p>${iteracion.count}: ${hrs}</p>
                </div>
            </c:forEach>
        </div>


    </body>

</html>
