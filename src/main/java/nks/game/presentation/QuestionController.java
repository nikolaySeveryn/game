package nks.game.presentation;

import nks.game.domain.Answer;
import nks.game.domain.Game;
import nks.game.domain.Question;
import nks.game.service.GameService;
import nks.game.service.QuestionService;

//@RestController
public class QuestionController {

	private GameService gameService;
	private QuestionService questionService;

	public Object startGame() {
		try {
			gameService.startGame();
			return questionService.getNextQuestion(0L);
		} catch (Exception e) {
			// log
			return "Something wnet wrong";
		}
	}

	public Object /* spring's ResponseEntity */ answer(Long gameId, Long questionId, Long optionId) {
		try {
			boolean isEnded = gameService.addAnswer(gameId, questionId, optionId);
			if (!isEnded) {
				Question nextQuestion = questionService.getNextQuestion(questionId);
				return QuestionDTO.fromEntity(nextQuestion);
			} else {
				// Obviously I will not use string as responce in real project
				Game game = gameService.findGame(gameId);
				String result = String.format("It was the last question. Your result is %d/%d",
						game.getNumberOfCorrectAnswers(), game.getNumberOfQuestion());
				return result;
			}
		} catch (Exception e) {
			//log
			return "Something went wrong";
		}
	}

	// to avoid returning a correct answer with the question
	private static class QuestionDTO {
		private Long id;
		private String text;

		public static QuestionDTO fromEntity(Question entity) {
			QuestionDTO dto = new QuestionDTO();
			dto.setId(entity.getId());
			dto.setText(entity.getText());
			return dto;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}

}
