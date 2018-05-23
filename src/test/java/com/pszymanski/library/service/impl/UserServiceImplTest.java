package com.pszymanski.library.service.impl;

import com.pszymanski.library.dto.UserDTO;
import com.pszymanski.library.entity.User;
import com.pszymanski.library.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class UserServiceImplTest {

    private UserServiceImpl userServiceImplUnderTest;

    @BeforeEach
    public void setUp() {
        userServiceImplUnderTest = new UserServiceImpl();
        userServiceImplUnderTest.userRepository = mock(UserRepository.class);
        userServiceImplUnderTest.passwordEncoder = mock(PasswordEncoder.class);
    }

    @Test
    public void testFindAll() {
        
        final List<User> expectedResult = Arrays.asList();
        final List<User> result = userServiceImplUnderTest.findAll();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOne() {
        
        final Integer id = 2;
        final User expectedResult = userServiceImplUnderTest.userRepository.getOne(id);
        final User result = userServiceImplUnderTest.findOne(id);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testSearch() {
        
        final String value = "Beata Kowalska";
        final String option = "2";
        final List<User> expectedResult = userServiceImplUnderTest.userRepository.findByName(value);
        final List<User> result = userServiceImplUnderTest.search(value, option);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testSave() {
		final UserDTO user = new UserDTO();

        userServiceImplUnderTest.save(user);
    }

    @Test
    public void testDelete() {
        final Integer id = 3;

        userServiceImplUnderTest.delete(id);

    }
}
