package com.masai.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BillDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer billId;

    @ManyToOne
    private Customer customer;

    private String transactionMode;
    //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String transactionDate ;
    private String transactionStatus;


//    @Embedded
//    private Address address;




}
