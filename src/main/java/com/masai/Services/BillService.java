package com.masai.Services;

import com.masai.Exception.BillException;
import com.masai.Exception.OrderException;
import com.masai.Model.BillDetails;

import java.util.List;

public interface BillService {

    public BillDetails BillsAdded(BillDetails addBills) throws BillException;

    public List<BillDetails> getAllBill() throws BillException;

}
