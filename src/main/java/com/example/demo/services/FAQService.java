package com.example.demo.services;

import com.example.demo.moduls.FAQ;
import com.example.demo.moduls.FAQquestion;
import com.example.demo.repositoties.FAQRepository;
import com.example.demo.repositoties.FAQquestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FAQService {

    private final FAQRepository faqRepository;
    private final FAQquestionRepository faqQuestionRepository;

    public boolean saveThemes(FAQ faq){
        if(faqRepository.findByThemes(faq.getThemes())!=null) return false;
        faqRepository.save(faq);
        return true;
    }
    public boolean deleteThemes(FAQ faq){
        if(faqRepository.findByThemes(faq.getThemes())==null) return false;
        faqRepository.delete(faq);
        return true;
    }
    public List<FAQ> listThemes(){
        return faqRepository.findAll();
    }
    public FAQ getThemeById(long id){
        FAQ faq = faqRepository.findById(id).orElse(null);
        return faq;
    }

    public FAQ saveQuestion(FAQquestion question, Long id){
        FAQ faq = faqRepository.findById(id).orElse(null);
        if (faq == null) return null;
        question.setFaq(faq);
        faqQuestionRepository.save(question);
        return faq;
    }
//    public boolean deleteQuestion(FAQ faq){
//        if(faqRepository.findByThemes(faq.getThemes())==null) return false;
//        faqRepository.delete(faq);
//        return true;
//    }
}
