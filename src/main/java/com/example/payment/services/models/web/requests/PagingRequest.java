package com.example.payment.services.models.web.requests;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagingRequest {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
