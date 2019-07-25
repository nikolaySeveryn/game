package nks.game.domain;

import java.util.List;
import java.util.Set;

public class Question {

	private Long id;
	private String text;
	private List<Option> options;
	private Option correctAnswer;
	
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
	public List<Option> getAnswers() {
		return options;
	}
	public void setAnswers(List<Option> options) {
		this.options = options;
	}
	public Option getCorrectAnswer() {
		return correctAnswer;
	}
	/**
	 * Expecting that correct answer always is one of options!!!
	 * @param correctAnswer
	 */
	public void setCorrectAnswer(Option correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	
}
