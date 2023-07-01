package com.example.payment.services.controllers;

import com.example.payment.services.entities.Payment;
import com.example.payment.services.helper.ResponseHelper;
import com.example.payment.services.models.Response;
import com.example.payment.services.models.service.payment.CreatePaymentRequest;
import com.example.payment.services.models.web.requests.PagingRequest;
import com.example.payment.services.models.web.requests.PostPaymentWebRequest;
import com.example.payment.services.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping
    public Response<Payment> postPayment(@RequestBody PostPaymentWebRequest webRequest)  {
        CreatePaymentRequest request = CreatePaymentRequest.builder()
                .amount(webRequest.getAmount())
                .paymentTypeId(webRequest.getPaymentTypeId())
                .customerId(webRequest.getCustomerId())
                .date(webRequest.getDate())
                .build();
        Payment payment = paymentService.createPayment(request);
        return ResponseHelper.ok(payment);
    }

    @GetMapping(path = "{id}")
    public Response<Payment> getPaymentById(@PathVariable Long id){
        return ResponseHelper.ok(null);
    }

    @GetMapping
    public Response<List<Payment>> getPaymentList(@RequestParam PagingRequest pagingRequest){
        return ResponseHelper.ok(null);
    }

    @PutMapping(path = "{id}")
    public Response<Payment> updatePayment(@PathVariable Long id){
        return ResponseHelper.ok(null);
    }

    @DeleteMapping(path = "{id}")
    public Response<Payment> deletePayment(@PathVariable Long id){
        return ResponseHelper.ok(null);
    }
}
