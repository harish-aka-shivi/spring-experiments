package com.tomcatdemo.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomcatdemo.service.InvoiceService;
import com.tomcatdemo.service.UserService;

public class Application {

    public static final UserService userService = new UserService();

    public static final InvoiceService invoiceService = new InvoiceService(userService);
    public static final ObjectMapper objectMapper = new ObjectMapper();

}