package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Integer>{

	public List<Feedback> findByVegId(Integer vegId);
	public List<Feedback> findByCustomerId(Integer customerId);

}
