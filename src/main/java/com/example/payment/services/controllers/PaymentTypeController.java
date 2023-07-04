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
import org.springframework.data.domain.Page;
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
    public Response<PaymentType> postPaymentType(@RequestBody PostPaymentTypeWebRequest webRequest)  {
        //TODO: validation for existing type
        CreatePaymentTypeRequest request = CreatePaymentTypeRequest.builder()
                .typeName(webRequest.getTypeName())
                .build();
        PaymentType paymentType = paymentTypeService.createPaymentType(request);
        return ResponseHelper.ok(paymentType);
    }

    @GetMapping(path = "{id}")
    public Response<PaymentType> getPaymentTypeById(@PathVariable Long id){
        //TODO: validation for existing type
        PaymentType paymentType = paymentTypeService.getPaymentTypeById(id);
        return ResponseHelper.ok(paymentType);
    }

    @GetMapping
    public Page<PaymentType> getPaymentTypeList(@RequestParam(value = "page", defaultValue = "1") Integer page
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
    public Response<PaymentType> updatePayment(@PathVariable Long id, @RequestBody UpdatePaymentTypeWebRequest webRequest){
        //TODO: validation for existing type
        //TODO: validation type not in use
        UpdatePaymentTypeRequest request = UpdatePaymentTypeRequest.builder()
                .id(id)
                .typeName(webRequest.getTypeName())
                .build();
        PaymentType paymentType = paymentTypeService.updatePaymentType(request);
        return ResponseHelper.ok(paymentType);
    }

    @DeleteMapping(path = "{id}")
    public Response<Long> deletePayment(@PathVariable Long id){
        //TODO: validation for existing type
        paymentTypeService.deletePaymentType(id);
        return ResponseHelper.ok(id);
    }
}
