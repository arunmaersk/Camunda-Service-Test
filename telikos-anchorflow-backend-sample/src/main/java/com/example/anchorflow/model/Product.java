package com.example.anchorflow.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
 //IM
 private String productCode;
 //InterModal
 private String productName;
 //Inland InterModal Journey
 private String productDescription;
}

// "Product": {
//     "ProductCode": "CCLR",
//     "ProductEnum": "CUSTOMS_CLEARANCE",
//     "ProductName": "Customs Clearance",
//     "ProductDescription": "Maersk handles Customs Clearance for the customer purchasing this product."
//     }
