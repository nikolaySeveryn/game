package nks.game.service;

import java.util.Objects;

import nks.game.dao.DAOException;
import nks.game.dao.GameRepository;
import nks.game.dao.OptionRepository;
import nks.game.dao.QuestionRepository;
import nks.game.domain.Answer;
import nks.game.domain.Game;
import nks.game.domain.Option;
import nks.game.domain.Question;

public class GameService {
	
	private GameRepository gameRepository;
	private QuestionRepository questionRepository;
	private OptionRepository optionRepository;
	
	private GameLocks locks;
	
	public Game startGame() {
		//Assume that we have only one user
		Game newGame = new Game();
		try {
			gameRepository.save(newGame);
		} catch (DAOException e) {
			throw new RuntimeException("Cannot create new game", e);
		}
		return newGame;
	}
	
	/**
	 * 
	 * @param gameId
	 * @param questionId
	 * @param answerValue
	 * @return <tt>true</tt> if it was last question in game, <tt>false<tt> - otherwise
	 */
	public boolean addAnswer(Long gameId, Long questionId, Long optionId) {
		Objects.requireNonNull(gameId);
		Objects.requireNonNull(questionId);
		Objects.requireNonNull(optionId);
		try {
			Game game = findGame(gameId);
			Question question = questionRepository.get(questionId);
			Option choosenOption = optionRepository.get(optionId);
			
			if(! question.getAnswers().contains(choosenOption)) {
				throw new IllegalArgumentException("");
			}
			synchronized (locks.getLock(game)) {
				if(game.isEnded()) {
					return true;
				}
				Answer answer = new Answer(question, choosenOption);
				game.getAnswers().add(answer);
				gameRepository.save(game);
				return game.isEnded();
			}
		} catch (DAOException e) {
			String message = String.format("Cannot add answer for game %d, question %d, option %d", gameId, questionId, optionId);
			throw new RuntimeException(message  , e);
		}
	}
	
	public Game findGame(Long id) {
		Objects.requireNonNull(id);
		try {
			return gameRepository.get(id);
		} catch (DAOException e) {
			throw new RuntimeException("Cannot find game with id " + id, e);
		}
	}
}
