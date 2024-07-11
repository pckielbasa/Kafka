package org.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    private String id;
    private String orderId;
    private String type;
    private Long cardNumber;
    private String validThru;
    private int ccv;
    private Long phoneNumber;
    private String email;
    private String status;
}
