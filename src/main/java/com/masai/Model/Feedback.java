package com.masai.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedbackId;

	private Integer customerId;

	private Integer vegId;

	private Double rating;

	private String comments;

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getVegId() {
		return vegId;
	}

	public void setVegetableId(Integer vegId) {
		this.vegId = vegId;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Feedback(Integer feedbackId, Integer customerId, Integer vegId, Double rating, String comments) {
		super();
		this.feedbackId = feedbackId;
		this.customerId = customerId;
		this.vegId = vegId;
		this.rating = rating;
		this.comments = comments;
	}

	public Feedback() {
		super();
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", customerId=" + customerId + ", vegId=" + vegId
				+ ", rating=" + rating + ", comments=" + comments + "]";
	}


}
