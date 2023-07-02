package com.example.payment.services.controllers;

import com.example.payment.services.entities.Payment;
import com.example.payment.services.helper.ResponseHelper;
import com.example.payment.services.models.Response;
import com.example.payment.services.models.service.payment.CreatePaymentRequest;
import com.example.payment.services.models.service.payment.UpdatePaymentRequest;
import com.example.payment.services.models.web.requests.PagingRequest;
import com.example.payment.services.models.web.requests.payment.PostPaymentWebRequest;
import com.example.payment.services.models.web.requests.payment.UpdatePaymentWebRequest;
import com.example.payment.services.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping
    public Mono<Response<Payment>> postPayment(@RequestBody PostPaymentWebRequest webRequest)  {
        //TODO: validation for invalid type id
        //Improvement payment type name saved (so if the object is updated, we still have original data)
        CreatePaymentRequest request = CreatePaymentRequest.builder()
                .amount(webRequest.getAmount())
                .paymentTypeId(webRequest.getPaymentTypeId())
                .customerId(webRequest.getCustomerId())
                .date(webRequest.getDate())
                .build();
        return paymentService.createPayment(request)
                .map(ResponseHelper::ok);
    }

    @GetMapping(path = "{id}")
    public Mono<Response<Payment>> getPaymentById(@PathVariable Long id){
        //TODO: validation for existing type
        return paymentService.getPaymentById(id)
                .map(ResponseHelper::ok);
    }

    @GetMapping
    public Response<List<Payment>> getPaymentList(@RequestParam PagingRequest pagingRequest){
        return ResponseHelper.ok(null);
    }

    @PutMapping(path = "{id}")
    public Mono<Response<Payment>> updatePayment(@PathVariable Long id, @RequestBody UpdatePaymentWebRequest webRequest){
        //TODO: validation for existing type
        //TODO: validation for existing payment
        UpdatePaymentRequest request = UpdatePaymentRequest.builder()
                .id(id)
                .amount(webRequest.getAmount())
                .date(webRequest.getDate())
                .customerId(webRequest.getCustomerId())
                .paymentTypeId(webRequest.getPaymentTypeId())
                .build();
        return paymentService.updatePayment(request)
                .map(ResponseHelper::ok);
    }

    @DeleteMapping(path = "{id}")
    public Mono<Response<Long>> deletePayment(@PathVariable Long id){
        return paymentService.deletePayment(id)
                .thenReturn(ResponseHelper.ok(id));
    }
}
