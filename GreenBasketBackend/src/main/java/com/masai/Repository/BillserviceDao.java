package com.masai.Repository;


import com.masai.Model.BillDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BillserviceDao extends JpaRepository<BillDetails,Integer>{

}