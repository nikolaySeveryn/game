package nks.game.service;

import nks.game.dao.QuestionRepository;
import nks.game.domain.Question;

public class QuestionService {
	
	private QuestionRepository repository;
	
	public Question getNextQuestion(Long previousQuestionId) {
		return repository.findByIdGreaterThan(previousQuestionId);
	}
}
