package nks.game.domain;

public class Answer {

	private Question question;
	private Option value;
	
	public Answer() {
	}
	
	public Answer(Question question, Option value) {
		super();
		this.question = question;
		this.value = value;
	}
	
	public boolean isCorrect() {
		return value.equals(question.getCorrectAnswer());
	}
	
	

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Option getValue() {
		return value;
	}

	/**
	 * Expecting that value is always one of the question options
	 * @param value
	 */
	public void setValue(Option value) {
		if(!question.getAnswers().contains(value)) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
