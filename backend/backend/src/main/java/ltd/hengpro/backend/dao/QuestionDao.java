package ltd.hengpro.backend.dao;

import ltd.hengpro.backend.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, String> {

    List<Question> findByStarterId(String starterId);
    List<Question> findQuestionByQuestionStatusNot(int status);
    Page<Question> findByQuestionStatusNot(int status, Pageable pageable);
    Question findByQuestionId(String questionId);
}