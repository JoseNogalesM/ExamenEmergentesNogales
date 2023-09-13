

package com.emergentes;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        Examen objper = new Examen();
        int id, pos;
        HttpSession ses = request.getSession();
        ArrayList<Examen> lista = (ArrayList<Examen>) ses.getAttribute("listaEx");
        switch (op) {
            case "nuevo":
                request.setAttribute("miobjper", objper);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
             case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request, id);
                objper = lista.get(pos);
                request.setAttribute("miobjper", objper);

                request.getRequestDispatcher("editar.jsp").forward(request, response);

                break;
            case "elminar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request, id);
                if (pos >= 0) {
                    lista.remove(pos);
                }
                request.setAttribute("listaEx", lista);
                response.sendRedirect("index.jsp");
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Examen> lista = (ArrayList<Examen>) ses.getAttribute("listaEx");
        Examen objper = new Examen();
        objper.setId(id);
        objper.setDescripcion(request.getParameter("descripcion"));
        objper.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        objper.setPrecio(Integer.parseInt(request.getParameter("precio")));
        objper.setCategoria(request.getParameter("categoria"));
      
        if (id == 0) {
            //nuevo registro
            int idNuevo = obtenerID(request);
            objper.setId(idNuevo);
            lista.add(objper);
        } else {
            //edicion
            int pos = buscarPorIndice(request, id);
            lista.set(pos, objper);
        }
        request.setAttribute("listaEx", lista);
        response.sendRedirect("index.jsp");
    }

    public int buscarPorIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        ArrayList<Examen> lista = (ArrayList<Examen>) ses.getAttribute("listaEx");

        int pos = -1;
        if (lista != null) {
            for (Examen ele : lista) {
                ++pos;
                if (ele.getId()== id) {
                    break;
                }
            }
        }
        return pos;
    }

    public int obtenerID(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ArrayList<Examen> lista = (ArrayList<Examen>) ses.getAttribute("listaEx");
        int idn = 0;
        for (Examen ele : lista) {
            idn = ele.getId();
        }
        return idn + 1;

    }

}
