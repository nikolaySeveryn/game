package nks.game.dao;

import nks.game.domain.Question;

public interface QuestionRepository {

	Question get(Long id);
	
	Question findByIdGreaterThan(Long value);
}
