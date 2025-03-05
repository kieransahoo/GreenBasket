package com.masai.Services;

import com.masai.Exception.BillException;
import com.masai.Model.BillDetails;
import com.masai.Repository.BillserviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillserviceDao repo;

//    @Autowired
//    private OrderDao orderDao;

    @Override
    public BillDetails BillsAdded(BillDetails addBills) throws BillException {

        if(addBills == null){
            throw new BillException("Bill Can't be Null");
        }
        BillDetails f1=repo.save(addBills);
        return f1;
    }

    @Override
    public List<BillDetails> getAllBill() throws BillException{

        return repo.findAll();
    }

}
