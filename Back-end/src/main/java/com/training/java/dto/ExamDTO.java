package com.training.java.dto;

import java.util.List;

import com.training.java.entities.Account;
import com.training.java.entities.Answer;

public class ExamDTO {
		
		private Long id;

	    private Account user;

	    private int level;

	    private List<Answer> answers;

	    private boolean submitted;
}
