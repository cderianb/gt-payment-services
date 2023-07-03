package com.example.payment.services.controllers;

import com.example.payment.services.entities.PaymentType;
import com.example.payment.services.helper.ResponseHelper;
import com.example.payment.services.models.Response;
import com.example.payment.services.models.service.paymentType.CreatePaymentTypeRequest;
import com.example.payment.services.models.service.paymentType.GetListPaymentTypeRequest;
import com.example.payment.services.models.service.paymentType.UpdatePaymentTypeRequest;
import com.example.payment.services.models.web.requests.paymentType.PostPaymentTypeWebRequest;
import com.example.payment.services.models.web.requests.paymentType.UpdatePaymentTypeWebRequest;
import com.example.payment.services.services.PaymentTypeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/payment-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;
    public PaymentTypeController(PaymentTypeService paymentTypeService){
        this.paymentTypeService = paymentTypeService;
    }
    @PostMapping
    public Mono<Response<PaymentType>> postPaymentType(@RequestBody PostPaymentTypeWebRequest webRequest)  {
        //TODO: validation for existing type
        CreatePaymentTypeRequest request = CreatePaymentTypeRequest.builder()
                .typeName(webRequest.getTypeName())
                .build();
        return paymentTypeService.createPaymentType(request)
                .map(ResponseHelper::ok);
    }

    @GetMapping(path = "{id}")
    public Mono<Response<PaymentType>> getPaymentTypeById(@PathVariable Long id){
        //TODO: validation for existing type
        return paymentTypeService.getPaymentTypeById(id)
                .map(ResponseHelper::ok);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PaymentType> getPaymentTypeList(@RequestParam(value = "page", defaultValue = "1") Integer page
                                                            , @RequestParam(value = "pageSize", defaultValue = "10") Integer size
                                                            , @RequestParam(value = "typeName", defaultValue = "") String typeName){
        GetListPaymentTypeRequest request = GetListPaymentTypeRequest.builder()
                .pageSize(size)
                .page(page-1)
                .typeName(typeName)
                .build();
        return paymentTypeService.getPaymentList(request);
    }

    @PutMapping(path = "{id}")
    public Mono<Response<PaymentType>> updatePayment(@PathVariable Long id, @RequestBody UpdatePaymentTypeWebRequest webRequest){
        //TODO: validation for existing type
        //TODO: validation type not in use
        UpdatePaymentTypeRequest request = UpdatePaymentTypeRequest.builder()
                .id(id)
                .typeName(webRequest.getTypeName())
                .build();
        return paymentTypeService.updatePaymentType(request)
                .map(ResponseHelper::ok);
    }

    @DeleteMapping(path = "{id}")
    public Mono<Response<Long>> deletePayment(@PathVariable Long id){
        //TODO: validation for existing type
        return paymentTypeService.deletePaymentType(id)
                .thenReturn(ResponseHelper.ok(id));
    }
}
