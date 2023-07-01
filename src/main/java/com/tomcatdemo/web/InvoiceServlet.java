package com.tomcatdemo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomcatdemo.context.InvoiceApplicationConfiguration;
import com.tomcatdemo.model.Invoice;
import com.tomcatdemo.service.InvoiceService;
import com.tomcatdemo.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;


public class InvoiceServlet extends HttpServlet {
    private UserService userService;
    private ObjectMapper objectMapper;
    private InvoiceService invoiceService;
    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(InvoiceApplicationConfiguration.class);
        ctx.registerShutdownHook();

        this.userService = ctx.getBean(UserService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
        this.invoiceService = ctx.getBean(InvoiceService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/invoices")) {
            response.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoices = this.invoiceService.findAll();  //
            response.getWriter().print(this.objectMapper.writeValueAsString(invoices));  //
        } else {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Hello World</h1>\n" +
                            "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
                            "</body>\n" +
                            "</html>");
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/invoices")) {

            String userId = request.getParameter("user_id");
            Integer amount = Integer.valueOf(request.getParameter("amount"));

            Invoice invoice = this.invoiceService.create(userId, amount);

            response.setContentType("application/json; charset=UTF-8");
            String json = this.objectMapper.writeValueAsString(invoice);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}