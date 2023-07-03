package com.example.payment.services.controllers;

import com.example.payment.services.entities.Payment;
import com.example.payment.services.helper.ResponseHelper;
import com.example.payment.services.models.Response;
import com.example.payment.services.models.service.payment.CreatePaymentRequest;
import com.example.payment.services.models.service.payment.GetListPaymentRequest;
import com.example.payment.services.models.service.payment.UpdatePaymentRequest;
import com.example.payment.services.models.web.requests.payment.PostPaymentWebRequest;
import com.example.payment.services.models.web.requests.payment.UpdatePaymentWebRequest;
import com.example.payment.services.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Payment> getPaymentList(@RequestParam(value = "page", defaultValue = "1") Integer page
                                                    , @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
                                                    , @RequestParam(value = "customerId", required = false) Long customerId
                                                    , @RequestParam(value = "typeName", required = false) String typeName
                                                    , @RequestParam(value = "minAmount", required = false) Double minAmount
                                                    , @RequestParam(value = "maxAmount", required = false) Double maxAmount
                                                    , @RequestParam(value = "minDate", required = false) Long minDate
                                                    , @RequestParam(value = "maxDate", required = false) Long maxDate){
        GetListPaymentRequest request = GetListPaymentRequest.builder()
                .customerId(customerId)
                .typeName(typeName)
                .minAmount(minAmount)
                .maxAmount(maxAmount)
                .minDate(minDate)
                .maxDate(maxDate)
                .page(page-1)
                .pageSize(pageSize)
                .build();
        return paymentService.getPaymentList(request);
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
