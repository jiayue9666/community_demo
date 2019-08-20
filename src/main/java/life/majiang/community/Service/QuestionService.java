package life.majiang.community.Service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO= new PaginationDTO();
      Integer totalCount=questionMapper.count();
        List<QuestionDTO>questionDTOList=new ArrayList<QuestionDTO>();
        paginationDTO.setPagination(totalCount,page,size);

        paginationDTO.setQuestions(questionDTOList);


        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        Integer offset =size*(page-1);
        List<Question> questionList=questionMapper.list(offset,size);
        for(Question question:questionList){
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO= new PaginationDTO();
        Integer totalCount=questionMapper.count();
        List<QuestionDTO>questionDTOList=new ArrayList<QuestionDTO>();
        paginationDTO.setPagination(totalCount,page,size);

        paginationDTO.setQuestions(questionDTOList);


        if(page<1){
            page=1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        Integer offset =size*(page-1);
        List<Question> questionList=questionMapper.list1(userId,offset,size);
        for(Question question:questionList){
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return paginationDTO;
        
    }
}
