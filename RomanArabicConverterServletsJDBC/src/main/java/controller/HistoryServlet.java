package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * HistoryServlet displays history of conversions
 *
 * @author Marek Michali
 * @version 5.0
 */
@WebServlet("/HistoryForm")
public class HistoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Cookie[] cookies = request.getCookies();
        String lastValue = "none";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastValue")) {
                    lastValue = cookie.getValue();
                    break;
                }
            }
            out.println("Your last converted number was: " + lastValue + " (read from cookies)<br />");
        }
        if ((DatabaseController) session.getAttribute("databaseController") == null) {
            try {
                DatabaseController databaseController = new DatabaseController();
                session.setAttribute("databaseController", databaseController);
                ArrayList<String> history;
                history = ((DatabaseController) session.getAttribute("databaseController")).displayData();
                out.println("Read from database<br />");
                for (int i = 0; i < history.size(); i += 2) {
                    out.println(history.get(i) + " -> " + history.get(i + 1) + "<br />");
                }
            } catch (SQLException ex) {
                response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
            }
        } else {
            try {
                ArrayList<String> history;
                history = ((DatabaseController) session.getAttribute("databaseController")).displayData();
                out.println("Read from database<br />");
                for (int i = 0; i < history.size(); i += 2) {
                    out.println(history.get(i) + " -> " + history.get(i + 1) + "<br />");
                }
            } catch (SQLException ex) {
                response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
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
