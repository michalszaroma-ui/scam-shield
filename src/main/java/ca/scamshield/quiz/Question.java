

/*
 *  Question.java
 * 
 *  Rev 1
 *
 *  15/11/2026
 *
 *  @author Michal Szaroma
 */
package ca.scamshield.quiz;

import java.io.Serializable;

/*
*  Abstract parent class for every quiz question.
*
*  Each question decides on its own how to check answer
*/

public abstract class Question implements Serializable {
    
    protected String questionText;
    protected String topic;
    protected String explanation;
    
    public Question(String questionText, String topic, String explanation){
        
        this.questionText = questionText;
        this.topic = topic;
        this.explanation = explanation;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public String getExplanation() {
        return explanation;
    }
    
    // Abstract methods - all child classes must have own versions
    public abstract boolean checkAnswer(String answer); //check if answer is correct
    public abstract String getType(); // multiple choice / True || False
    public abstract String[] getOptions(); // answer shown to the user
    
    @Override
    public String toString(){
        return "[" + topic + "]" + questionText + "(" + getType() + ")";
    }
}
