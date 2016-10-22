package org.muj.sbf.service;

import java.util.List;

import org.muj.sbf.domain.User;
import org.muj.sbf.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User service implementation of UserService
 * 
 * @author momujah
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public User addUser(User user) {
		return userRepo.save(user);
	}

	
	@Transactional(readOnly = true)
	@Override
	public List<User> search(String term, int start, int size) {
		return userRepo.search(term, start, size);
	}

	
	@Transactional(readOnly = true)
	@Override
	public Long searchHitCount(String term) {
		return userRepo.searchHitCount(term);
	}

}
