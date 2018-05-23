package com.pszymanski.library.service;

import org.springframework.stereotype.Service;


public interface NotificationsService {

    void sendReminderToUser();

    void sendReminderToEmployee();

    void sendInfoToUserReservatiom(Integer idBook);

    void sendNewsletter();



    }
