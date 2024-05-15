<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Estudiante"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <center>
        <div>
            <h2>SEGUNDO PARCIAL TEM-742</h2>
            <p>Nombre: Javier Apaza Mamani</p>
            <p>Carnet: 9870820</p>
        </div>

        <h1>Registro Dia del Internet</h1>
        <a href="EstudianteController?action=add">Nuevo</a>

        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Seminario</th>
                <th>Confirmado</th>
                <th>Fecha Inscripcion</th>
                <th></th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${estudiantes}">
                <tr>

                    <td>${item.id}</td>
                    <td>${item.nombres}</td>
                    <td>${item.apellidos}</td>
                    <td>${item.seminario}</td>
                    
                    <td>
                        <c:if test="${item.confirmado == 1}">
                            <input type="checkbox" checked>
                        </c:if>

                            <c:if test="${item.confirmado != 1}">
                            <input type="checkbox">
                        </c:if>

                    </td>
                    <td>${item.fecha_ins}</td>
                    <td><a href="EstudianteController?action=edit&id=${item.id}">Editar</a></td>
                    <td><a href="EstudianteController?action=delete&id=${item.id}">Eliminar</a></td>
                </tr>
            </c:forEach>



        </table>
    </center>
    </body>
</html>
