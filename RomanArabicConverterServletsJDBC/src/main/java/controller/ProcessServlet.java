package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import math.Convert;
import math.ConvertException;

/**
 * ProcessServlet converts the number of the user
 *
 * @author Marek Michali
 * @version 5.0
 */
@WebServlet("/ProcessForm")
public class ProcessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String convertedNumber = "";

        if ((DatabaseController) session.getAttribute("databaseController") == null) {
            try {
                DatabaseController databaseController = new DatabaseController();
                session.setAttribute("databaseController", databaseController);
                if ((Convert) session.getAttribute("convert") != null) {
                    String number = request.getParameter("number");
                    if (number == null || number.length() == 0) {
                        response.sendError(response.SC_BAD_REQUEST, "No number!");
                    } else {
                        try {
                            convertedNumber = ((Convert) session.getAttribute("convert")).conversion(number);
                            out.println(convertedNumber);
                            Cookie cookie = new Cookie("lastValue", convertedNumber);
                            try {
                                ((DatabaseController) session.getAttribute("databaseController")).insertData(number, convertedNumber);
                            } catch (SQLException ex) {
                                response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
                            }
                            response.addCookie(cookie);
                        } catch (ConvertException ex) {
                            response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
                        }

                    }
                } else {

                    Convert convert = new Convert();
                    session.setAttribute("convert", convert);
                    String number = request.getParameter("number");
                    if (number.length() == 0) {
                        response.sendError(response.SC_BAD_REQUEST, "No number!");
                    } else {
                        try {
                            convertedNumber = ((Convert) session.getAttribute("convert")).conversion(number);
                            out.println(convertedNumber);
                            Cookie cookie = new Cookie("lastValue", convertedNumber);
                            try {
                                ((DatabaseController) session.getAttribute("databaseController")).insertData(number, convertedNumber);
                            } catch (SQLException ex) {
                                response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
                            }
                            response.addCookie(cookie);
                        } catch (ConvertException ex) {
                            response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
                        }
                    }
                }
            } catch (SQLException ex) {
                response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
            }
        } else {
            if ((Convert) session.getAttribute("convert") != null) {
                String number = request.getParameter("number");
                if (number == null || number.length() == 0) {
                    response.sendError(response.SC_BAD_REQUEST, "No number!");
                } else {
                    try {
                        convertedNumber = ((Convert) session.getAttribute("convert")).conversion(number);
                        out.println(convertedNumber);
                        Cookie cookie = new Cookie("lastValue", convertedNumber);
                        try {
                            ((DatabaseController) session.getAttribute("databaseController")).insertData(number, convertedNumber);
                        } catch (SQLException ex) {
                            response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
                        }
                        response.addCookie(cookie);
                    } catch (ConvertException ex) {
                        response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
                    }

                }
            } else {

                Convert convert = new Convert();
                session.setAttribute("convert", convert);
                String number = request.getParameter("number");
                if (number.length() == 0) {
                    response.sendError(response.SC_BAD_REQUEST, "No number!");
                } else {
                    try {
                        convertedNumber = ((Convert) session.getAttribute("convert")).conversion(number);
                        out.println(convertedNumber);
                        Cookie cookie = new Cookie("lastValue", convertedNumber);
                        try {
                            ((DatabaseController) session.getAttribute("databaseController")).insertData(number, convertedNumber);
                        } catch (SQLException ex) {
                            response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
                        }
                        response.addCookie(cookie);
                    } catch (ConvertException ex) {
                        response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
