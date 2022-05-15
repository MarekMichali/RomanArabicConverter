package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import math.Convert;

/**
 * HistoryServlet displays history of conversions
 *
 * @author Marek Michali
 * @version 4.0
 */

/**
 * HistoryServlet displays history of conversions
 *
 * @author Marek Michali
 * @version 4.0
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
        if ((Convert) session.getAttribute("convert") != null) {
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
            for (int i = 0; i < ((Convert) session.getAttribute("convert")).getHistory().size(); i++) {
                out.println(((Convert) session.getAttribute("convert")).getHistory().get(i));
                out.println(" -> ");
                i++;
                out.println(((Convert) session.getAttribute("convert")).getHistory().get(i));
                out.println("<br />");               
            }
        } else {            
                out.println("No history!");
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
