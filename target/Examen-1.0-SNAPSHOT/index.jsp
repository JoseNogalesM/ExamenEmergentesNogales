<%-- 
    Document   : index
    Created on : 12 sep. de 2023, 20:55:12
    Author     : nogal
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.Examen"%>
<%@page import="com.emergentes.Examen"%>
<%
    if (session.getAttribute("listaEx") == null) {
        ArrayList<Examen> listaux = new ArrayList<Examen>();
        session.setAttribute("listaEx", listaux);
    }
    ArrayList<Examen> lista = (ArrayList<Examen>) session.getAttribute("listaEx");

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Examen</title>
    </head>
    <body>
        
        <h2 align="center">SEGUNDO PARCIAL TEM-742</h2>
        <h3 align="center">NOMBRE: JOSE ANTONIO NOGALES MAMANI</h3>
        <h3 align="center">CARNET:9178400</h3>
        <br>
        <hr>
        <h1 align="center">Gestion de Productos</h1>
        <h3 aling="center"><a href="Main?op=nuevo"  >Nuevo registro</a></h3>
        <table align="center" border="1">
            <tr>
                <th>id</th>
                <th>DESCRIPCION</th>
                <th>CANTIDAD</th>
                <th>PRECIO</th>
                <th>CATEGORIA</th>
                <th></th>
                <th></th>

            </tr>
            <%    if (lista != null) {
                    for (Examen item : lista) {

            %>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getDescripcion()%></td>
                <td><%= item.getCantidad()%></td>
                <td><%= item.getPrecio()%></td>
                <td><%= item.getCategoria()%></td>

                <td>
                    <a href="Main?op=editar&id=<%= item.getId()%>">Editar</a>

                </td>
                <td>
                    <a href="Main?op=elminar&id=<%= item.getId()%>"
                       onclick="return(confirm('Esta seguro de Eliminar?? '))"
                       >Eliminar</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>

    </body>
</html>
