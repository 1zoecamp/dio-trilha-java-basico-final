package me.dio.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.impl.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		// Spring entende que precisa injetar userRepository para utilizá-lo.
		// Qual a diferença de fazer isso e utilizar a annotation @Autowired?
	}

	@Override
	public User findbyId(Long id) {
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public User create(User userToCreate) {
		if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
			throw new IllegalArgumentException("This account number already exists.");
		}
		return userRepository.save(userToCreate);
	}

}
