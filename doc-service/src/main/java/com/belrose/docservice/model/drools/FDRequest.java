package com.belrose.docservice.model.drools;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FDRequest {
    private String bankName;
    private Integer durationInYear;
    private String fdInterestRate;

    public FDRequest(String bank, Integer durationInYear) {
        this.bankName=bank;
        this.durationInYear=durationInYear;
    }
}
