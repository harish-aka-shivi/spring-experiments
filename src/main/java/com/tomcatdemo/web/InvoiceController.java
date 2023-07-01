package com.tomcatdemo.web;

import com.tomcatdemo.dto.InvoiceDto;
import com.tomcatdemo.model.Invoice;
import com.tomcatdemo.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public  InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
//
//    @GetMapping("/")
//    @ResponseBody
//    public String index() {
//        return "Hello World";
//    }

    @GetMapping("/invoices")
    // @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<Invoice> invoices() {
        return this.invoiceService.findAll();
    }

//    @PostMapping("/invoices")
//    public Invoice createInvoice(@RequestParam("user_id") String userId, @RequestParam Integer amount) {
//        return invoiceService.create(userId, amount);
//    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }

//    @PostMapping("/invoices/{userId}/{amount}")
//    public Invoice createInvoice(@PathVariable String userId, @PathVariable Integer amount) {
//        return invoiceService.create(userId, amount);
//    }
}
