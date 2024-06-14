package com.example.demo.repositoties;

import com.example.demo.moduls.FAQ;
import com.example.demo.moduls.FAQquestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQquestionRepository extends JpaRepository<FAQquestion, Long> {

}
