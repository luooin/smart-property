package com.es.estatemanagement.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 疫情选项表
 */
@Table(name = "tb_survey_answers")
public class SurveyAnswers implements Serializable {
    @Id
    private Integer answerId;  //选项ID
    private Integer questionId;//对于的问题ID
    private String choiceText; //选项的字符串

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }
}
