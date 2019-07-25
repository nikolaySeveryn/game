package nks.game.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private Long id;
	private List<Answer> answers = new ArrayList<Answer>();
	private Integer numberOfQuestion = 10;
	
	public boolean isEnded() {
		return answers.size() >= numberOfQuestion;
	}
	public long getNumberOfCorrectAnswers() {
		return answers.stream().filter(answer -> answer.isCorrect()).count();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumberOfQuestion() {
		return numberOfQuestion;
	}
	public void setNumberOfQuestion(Integer numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> ansawers) {
		this.answers = ansawers;
	}
}
