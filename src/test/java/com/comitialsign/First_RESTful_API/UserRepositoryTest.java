package com.comitialsign.First_RESTful_API;

import com.comitialsign.First_RESTful_API.domain.model.User;
import com.comitialsign.First_RESTful_API.domain.repository.UserRepository;
import com.comitialsign.First_RESTful_API.service.exception.NotFoundException;
import com.comitialsign.First_RESTful_API.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Should get all users successfully from DB")
	public void TestFindAllUsers() {
		List<User> mockUsers = List.of(
				new User(1L, "Daniel", "daniel@test.com", 30),
				new User(2L, "Ana", "ana@test.com", 20),
				new User(3L, "Fernando", "fernando@test.com", 47)
		);

		when(userRepository.findAll()).thenReturn(mockUsers);

		List<User> users = userService.findAll();

		Assertions.assertEquals(3, users.size());
	}

	@Test
	@DisplayName("Should get user by id successfully from DB")
	public void TestFindUserByIdCase1() {
		User mockUser = new User(1L, "Daniel", "daniel@test.com", 30);

		when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

		User user = userService.findUserById(1L);

		Assertions.assertEquals(mockUser, user);
	}

	@Test
	@DisplayName("Should not get user by id from DB")
	public void TestFindUserByIdCase2() {
		when(userRepository.findById(1L)).thenReturn(Optional.empty());

		NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
			userService.findUserById(1L);
		});

		Assertions.assertEquals("User not found", exception.getMessage());
	}

	@Test
	@DisplayName("Should create a user successfully")
	public void TestCreateUser() {
		User user = new User(1L, "Daniel", "daniel@test.com", 30);

		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		User userCreated = userService.createUser(user);
		User returnedUser = userService.findUserById(1L);

		Assertions.assertEquals(user, userCreated);
		Assertions.assertEquals(user, returnedUser);

		verify(userRepository).save(user);
	}

	@Test
	@DisplayName("Should update a user from DB successfully")
	public void TestUpdateUser() {
		User mockUser = new User(1L, "Daniel", "daniel@test.com", 30);
		User updateUser = new User(1L, "Ana", "ana@test.com", 32);

		when(userRepository.save(updateUser)).thenReturn(updateUser);
		when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

		User updatedUser = userService.updateUser(updateUser, 1L);

		Assertions.assertEquals(updateUser, updatedUser);

		verify(userRepository).save(updatedUser);
	}

	@Test
	@DisplayName("Should delete a user from DB successfully")
	public void TestDeleteUser() {
		User user = new User(1L, "Daniel", "daniel@test.com", 30);

		when(userRepository.save(user)).thenReturn(user);

		userService.deleteUser(1L);

		verify(userRepository).deleteById(1L);
	}

}
