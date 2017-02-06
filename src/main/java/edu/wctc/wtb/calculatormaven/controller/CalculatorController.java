/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.calculatormaven.controller;

import java.io.IOException;
import static java.lang.System.out;
import java.text.DecimalFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.wctc.wtb.calculatormaven.model.CalculatorService;

/**
 *
 * @author kanst_000
 */
@WebServlet(name = "CalculatorController", urlPatterns = {"/calculate"})
public class CalculatorController extends HttpServlet {
private static final String CALCULATOR_TYPE = "calculatorType";
//Rectangle vars
private static final String LENGTH = "length";
private static final String WIDTH = "width";
private static final String AREA = "area";
//Circle vars
private static final String TYPE = "type";
private static final String SIZE = "size";
//Triangle vars
private static final String BASE = "base";
private static final String HEIGHT = "height";
private static final String DECIMAL_FORMAT = "0.####";
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
        try {
            String calculatorType = request.getParameter(CALCULATOR_TYPE);
            CalculatorService rs = new CalculatorService();
            DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
            
            String resultPage;
            double area;
            switch (calculatorType){
                case "rectangle":
                    String length = request.getParameter(LENGTH);
            String width = request.getParameter(WIDTH);
            
            area = rs.getRectangleArea(length, width);
            request.setAttribute(LENGTH, length);
            request.setAttribute(WIDTH, width);
            request.setAttribute(AREA, df.format(area));
            resultPage = "rectangle.jsp";
            break;
                case "circle":
                    String type = request.getParameter(TYPE);
                    String size = request.getParameter(SIZE);
                    
                    area = rs.getCircleArea(type, size);
                    size = df.format(rs.getCircleRadius(type, size));
                    request.setAttribute(TYPE, type);
                    request.setAttribute(SIZE, size);
                    request.setAttribute(AREA, df.format(area));
            resultPage = "circle.jsp";
            break;
                case "triangle":
                    String base = request.getParameter(BASE);
            String height = request.getParameter(HEIGHT);
            
            area = rs.getTriangleHypotenuse(base, height);
            request.setAttribute(BASE, base);
            request.setAttribute(HEIGHT, height);
            request.setAttribute(AREA, df.format(area));
            resultPage = "triangle.jsp";
            break;
                default:
                    resultPage = "index.html";
            }
            /* TODO output your page here. You may use following sample code. */
            
            RequestDispatcher view =
                request.getRequestDispatcher(resultPage);
        view.forward(request, response);
        } catch(IOException | NumberFormatException | ServletException e){
            out.print(e);
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
