package com.masai.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.FeedbackException;
import com.masai.Model.Feedback;
import com.masai.Repository.FeedbackDao;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackDao fdbkDao;

	@Override
	public Feedback addFeedback(Feedback fdbk) throws FeedbackException {
		Feedback feedback = fdbkDao.save(fdbk);

		return feedback;
	}

	@Override
	public List<Feedback> viewAllFeedback(Integer vegId) throws FeedbackException {
		List<Feedback> list=fdbkDao.findByVegId(vegId);
		if(list.isEmpty()) {
			throw new FeedbackException("No feedback");
		}
		return list;
	}

	@Override
	public List<Feedback> viewFeedback(Integer customerId) throws FeedbackException {
		List<Feedback> list=fdbkDao.findByCustomerId(customerId);
		if(list.isEmpty()) {
			throw new FeedbackException("No feedback by customer");
		}
		return list;
	}

}
