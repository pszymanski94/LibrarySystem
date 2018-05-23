package com.pszymanski.library.service.impl;

import com.pszymanski.library.entity.Book;
import com.pszymanski.library.entity.Newsletter;
import com.pszymanski.library.entity.Rent;
import com.pszymanski.library.entity.Reservation;
import com.pszymanski.library.repository.BookRepository;
import com.pszymanski.library.repository.NewsletterRepository;
import com.pszymanski.library.repository.RentRepository;
import com.pszymanski.library.repository.ReservationRepository;
import com.pszymanski.library.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationsServiceImpl implements NotificationsService {
    @Autowired
    NewsletterRepository newsletterRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    RentRepository rentRepository;

    @Scheduled(cron = "0 0 20 * * *")
    public void sendReminderToUser() {
        int i = 0;
        List<Rent> overdueBooks = rentRepository.getOverdueRentForUser();
        if (overdueBooks.isEmpty() == false) {
            for (Rent rent : overdueBooks) {
                emailService.sendEmail(rent.getUser().getEmail(), "Przetrzymanie książki", null, overdueBooks.get(i), "email/overdueUser");
                i++;
                rentRepository.setEmailSentUser(rent.getId());

            }
        }
    }

    @Scheduled(cron = "0 0 16 * * SUN")
    public void sendNewsletter() {
        LocalDate sevenMinus = LocalDate.now().minusDays(7);
        LocalDate now = LocalDate.now();
        List<Book> newBooks = bookRepository.getNewBooks(sevenMinus, now);
        List<Newsletter> newsletterList = newsletterRepository.findAll();
        if (newsletterList.isEmpty() == false) {
            for (Newsletter newsletter : newsletterList) {
                emailService.sendEmail(newsletter.getEmail(), "Nowe książki w bibliotece", newBooks, null, "email/newsletter");

            }
        }
    }

    @Scheduled(cron = "0 0/30 8 * * MON-FRI")
    public void sendReminderToEmployee() {
        List<Rent> overdueBooks = rentRepository.getOverdueRentForUser();
        if (overdueBooks.isEmpty() == false) {
            emailService.sendEmail("librarysystemtest@gmail.com", "Przetrzymanie książek przez czytelników", overdueBooks, null, "email/overdueEmployee");
            for (Rent rent : overdueBooks) {
                rentRepository.setEmailSentEmployee(rent.getId());
            }
        }
    }

    public void sendInfoToUserReservatiom(Integer idBook) {
        Reservation reservationForUser = reservationRepository.findReservationForUser(idBook);
        if (reservationForUser != null) {
            emailService.sendEmail(reservationForUser.getUser().getEmail(), "Zarezerwowana książka już czeka na Ciebie!", null, reservationForUser, "email/reservation");
            reservationRepository.sendEmail(reservationForUser.getId());
        }
    }
}
