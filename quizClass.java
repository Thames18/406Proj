public class quizClass
{
    public String question;
    public String[] answers;

    public quizClass(String question, String[] answers){
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion(){
        return question;
    }

    public String[] getAnswers(){
        return answers;
    }

}
