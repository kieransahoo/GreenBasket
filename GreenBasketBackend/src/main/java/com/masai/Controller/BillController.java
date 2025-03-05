package com.masai.Controller;

import com.masai.Exception.BillException;
import com.masai.Model.BillDetails;
import com.masai.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/BillingService")
public class BillController {

    @Autowired
    private BillService f;


    //add frnd

    @PostMapping("/addBill")
    public ResponseEntity<BillDetails> BillsAdded(@RequestBody BillDetails addBills) throws BillException {

        BillDetails f1=f.BillsAdded(addBills);
        return new ResponseEntity<BillDetails>(f1, HttpStatus.CREATED);

    }


    //get all frnd-->>


    @GetMapping("/getAllBill")
    public ResponseEntity<List<BillDetails>> getAllBill() throws BillException{

        List<BillDetails> f2=f.getAllBill();
        return new ResponseEntity<List<BillDetails>>(f2,HttpStatus.OK);


    }

    //update

    @PutMapping("/updateBill")
    public ResponseEntity<BillDetails> updateBill(@RequestBody BillDetails UpdateBills) throws BillException{

        BillDetails f4=f.BillsAdded(UpdateBills);
        return new ResponseEntity<BillDetails>(f4,HttpStatus.CREATED);


    }


}

