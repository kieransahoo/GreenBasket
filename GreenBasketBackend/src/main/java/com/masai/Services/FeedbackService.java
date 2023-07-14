package com.masai.Services;

import java.util.List;

import com.masai.Exception.FeedbackException;
import com.masai.Model.Feedback;

public interface FeedbackService {

	public Feedback addFeedback(Feedback fdbk)throws FeedbackException;

	public List<Feedback> viewAllFeedback(Integer vegId)throws FeedbackException;

	public List<Feedback> viewFeedback(Integer customerId)throws FeedbackException;

}
