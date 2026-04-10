package com.educandoweb.course.repositories;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("Gabriel");
        user.setEmail("teste@gmial.com");
        user.setPhone("99999999");
    }

    @Test
    void findAllShouldReturnList(){
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> result = service.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findByIdShouldReturnUser() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        User result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Gabriel", result.getName());
    }

    @Test
    void findByIdShouldThrowExceptionWhenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(1L);
        });
    }

    @Test
    void insertShouldReturnUser(){
        when(repository.save(any(User.class))).thenReturn(user);

        User result = service.insert(user);

        assertNotNull(result);
        verify(repository, times(1)).save(user);
    }

    @Test
    void deleteShouldWorkWhenIdExists(){
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.delete(1L));

        verify(repository, times(1)).deleteById(1L);
    }
    @Test
    void deleteShouldThrowWhenIdNotExists() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(1L);
        });
    }

    @Test
    void updateShouldReturnUpdatedUser(){
        User newData = new User();
        newData.setName("Novo nome");
        newData.setEmail("novo@gmail.com");
        newData.setPhone("312465987");

        when(repository.getReferenceById(1L)).thenReturn(user);
        when(repository.save(any(User.class))).thenReturn(user);

        User result = service.update(1L, newData);

        assertEquals("Novo nome", result.getName());
        assertEquals("novo@gmail.com", result.getEmail());
    }

    @Test
    void updateShouldThrowExceptionWhenIdNotFound() {
        when(repository.getReferenceById(1L))
                .thenThrow(jakarta.persistence.EntityNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(1L, new User());
        });
    }

}
