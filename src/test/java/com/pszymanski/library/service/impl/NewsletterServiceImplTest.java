package com.pszymanski.library.service.impl;

import com.pszymanski.library.dto.NewsletterDTO;
import com.pszymanski.library.entity.Newsletter;
import com.pszymanski.library.repository.NewsletterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class NewsletterServiceImplTest {

    private NewsletterServiceImpl newsletterServiceImplUnderTest;

    @BeforeEach
    public void setUp() {
        newsletterServiceImplUnderTest = new NewsletterServiceImpl();
        newsletterServiceImplUnderTest.newsletterRepository = mock(NewsletterRepository.class);
    }

    @Test
    public void testSave() {
        final Newsletter newsletter = null;

        newsletterServiceImplUnderTest.save(newsletter);
        }



    @Test
    public void testDelete() {
        final Integer id = 3;

        newsletterServiceImplUnderTest.delete(id);

    }

    @Test
    public void testFindOne() {
        final String value = "abc@abc.pl";
        final List<Newsletter> expectedResult = Arrays.asList();
        final List<Newsletter> result = newsletterServiceImplUnderTest.findOne(value);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAll() {
        final List<Newsletter> expectedResult = Arrays.asList();
        final List<Newsletter> result = newsletterServiceImplUnderTest.findAll();

        assertEquals(expectedResult, result);
    }
}
