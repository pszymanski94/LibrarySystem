package com.pszymanski.library.service;

import com.pszymanski.library.dto.NewsletterDTO;
import com.pszymanski.library.entity.Newsletter;

import java.util.List;

public interface NewsletterService {

    void save (Newsletter newsletter);

    void dtoToEntity(NewsletterDTO newsletterDTO);

    void delete(Integer id);

    List<Newsletter> findOne(String value);

    List<Newsletter> findAll();

}
