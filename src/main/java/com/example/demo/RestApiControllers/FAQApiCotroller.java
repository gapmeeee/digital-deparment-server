package com.example.demo.RestApiControllers;

import com.example.demo.moduls.FAQ;
import com.example.demo.moduls.FAQquestion;
import com.example.demo.services.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/FAQ")
@RequiredArgsConstructor
public class FAQApiCotroller {

    private final FAQService faqService;

    @GetMapping("/get-faq")
    public ResponseEntity<?> getFAQs(){
        return ResponseEntity.ok(faqService.listThemes());
    }


    @PostMapping("/add-faq")
    public ResponseEntity<?> addFAQs(@RequestBody FAQ faq){
        if(!faqService.saveThemes(faq)){
            return ResponseEntity.badRequest().body("Тема: " + faq.getThemes() + " уже существует");
        }
        return ResponseEntity.ok(faq);
    }

//    @PostMapping("/delete-faq/{id}")
//    public ResponseEntity<?> deleteFAQs(@PathVariable long id){
//        faqService.deleteThemes(faqService.);
//        return ResponseEntity.ok(faq);
//    }

    @PostMapping("/{id}/add-question")
    public ResponseEntity<?> addQuestion(@PathVariable long id, @RequestBody FAQquestion question){
        if (faqService.getThemeById(id) == null)  return  ResponseEntity.badRequest().body("Такой темы не существует");
        return ResponseEntity.ok(faqService.saveQuestion(question, id));
    }
}
