package com.emergentes.controller;

import com.emergentes.dao.EstudianteDAO;
import com.emergentes.dao.EstudianteDAOimpl;
import com.emergentes.modelo.Estudiante;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EstudianteController", urlPatterns = {"/EstudianteController"})
public class EstudianteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EstudianteDAO dao = new EstudianteDAOimpl();

        Estudiante et = new Estudiante();
        int id;

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":
                request.setAttribute("estudiante", et);
                request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                try {
                    et = dao.getById(id);
                } catch (Exception ex) {
                    System.out.println("Error al obtener registro " + ex.getMessage());
                }

                request.setAttribute("estudiante", et);
                request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);

                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));

                try {
                    dao.delete(id);
                } catch (Exception ex) {
                    System.out.println("Error al eliminar: " + ex.getMessage());
                }

                response.sendRedirect("EstudianteController");

                break;

            case "view":
                List<Estudiante> lista = new ArrayList<Estudiante>();
                try {
                    lista = dao.getAll();
                } catch (Exception ex) {
                    System.out.println("Error al listar " + ex.getMessage());
                }
                request.setAttribute("estudiantes", lista);
                request.getRequestDispatcher("estudiantes.jsp").forward(request, response);//raro

                break;
            default:
                //response.sendRedirect("posts.jsp"); //raro
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String seminario = request.getParameter("seminario");
        //int confirmado = Integer.parseInt(request.getParameter("confirmado")); //raro
        int confirmado;
        if(request.getParameter("confirmado") == null){
            confirmado = 0;
        }else{
            confirmado = Integer.parseInt(request.getParameter("confirmado"));
        }
        
        String fecha_ins = request.getParameter("fecha_ins");
        

        Estudiante et = new Estudiante();
        et.setId(id);
        et.setNombres(nombres);
        et.setApellidos(apellidos);
        et.setSeminario(seminario);
        
        et.setConfirmado(confirmado);
        
        et.setFecha_ins(fecha_ins);
        

        EstudianteDAO dao = new EstudianteDAOimpl();
        if (id == 0) {
            try {
                //nuevo
                dao.insertar(et);
            } catch (Exception ex) {
                System.out.println("Error al insertar " + ex.getMessage());
            }
        } else {
            try {
                dao.update(et);
            } catch (Exception ex) {
                System.out.println("Error al editar " + ex.getMessage());
            }
        }
        response.sendRedirect("EstudianteController");
    }

}
