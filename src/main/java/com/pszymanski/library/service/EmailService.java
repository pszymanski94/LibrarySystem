package com.pszymanski.library.service;

import java.util.List;

public interface EmailService {
    void sendEmail(String to, String subject, List<?> list, Object object, String template);

    String build(List<?> list, Object object, String mailTemplate);
}

