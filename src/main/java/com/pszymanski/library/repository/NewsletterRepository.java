package com.pszymanski.library.repository;

import com.pszymanski.library.entity.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsletterRepository extends JpaRepository <Newsletter,Integer> {

    List<Newsletter> findByEmail(String value);

}

