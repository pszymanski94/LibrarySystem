package com.pszymanski.library.service.impl;

import com.pszymanski.library.dto.NewsletterDTO;
import com.pszymanski.library.entity.Newsletter;
import com.pszymanski.library.repository.NewsletterRepository;
import com.pszymanski.library.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsletterServiceImpl implements NewsletterService {

    @Autowired
    NewsletterRepository newsletterRepository;

    @Override
    public void save(Newsletter newsletter) {
        newsletterRepository.save(newsletter);
    }

    @Override
    public void dtoToEntity(NewsletterDTO newsletterDTO) {
        Newsletter newsletter = new Newsletter();
        newsletter.setName(newsletterDTO.getName());
        newsletter.setEmail(newsletterDTO.getEmail());
        save(newsletter);
    }


    public void delete(Integer id) {
        newsletterRepository.deleteById(id);
    }

    public List<Newsletter> findOne(String value) {
        return newsletterRepository.findByEmail(value);
    }

    public List<Newsletter> findAll() {
        return newsletterRepository.findAll();
    }
}
