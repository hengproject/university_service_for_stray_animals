package ltd.hengpro.backend.service;

import ltd.hengpro.backend.dto.QuestionDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.Question;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {
    void startQuestion(QuestionDto questionDto);
    List<Question> getQuestionByStarterId(String starterId);
    List<Question> getUnsolvedQuestion();
    List<Question> getUnsolvedQuestion(Pageable pageable);
    void setQuestionSeen(String questionId);
    void setQuestionSolved(UserDto userDto, String questionId);
}
