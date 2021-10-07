package ltd.hengpro.backend.service.Impl;

import ltd.hengpro.backend.dao.QuestionDao;
import ltd.hengpro.backend.dto.QuestionDto;
import ltd.hengpro.backend.dto.UserDto;
import ltd.hengpro.backend.entity.Question;
import ltd.hengpro.backend.service.QuestionService;
import ltd.hengpro.backend.utils.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionDao questionDao;
    @Override
    public void startQuestion(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto,question);
        question.setQuestionId(UUIDUtil.getUUID());
        question.setQuestionStatus(1);
        question.setStartTime(new Date());
        questionDao.saveAndFlush(question);
    }

    @Override
    public List<Question> getQuestionByStarterId(String starterId) {
        return questionDao.findByStarterId(starterId);
    }

    public List<Question> getUnsolvedQuestion(){
        return questionDao.findQuestionByQuestionStatusNot(3);
    }

    @Override
    public List<Question> getUnsolvedQuestion(Pageable pageable) {
        return questionDao.findByQuestionStatusNot(3,pageable).getContent();
    }

    public void setQuestionSeen(String questionId){
        Question question = questionDao.findByQuestionId(questionId);
        question.setQuestionStatus(2);
        questionDao.saveAndFlush(question);
    }
    public void setQuestionSolved(UserDto userDto,String questionId){
        Question question = questionDao.findByQuestionId(questionId);
        question.setQuestionStatus(3);
        question.setDealerId(userDto.getUserId());
        question.setEndTime(new Date());
        questionDao.saveAndFlush(question);
    }
}
