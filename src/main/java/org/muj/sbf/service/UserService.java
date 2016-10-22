package org.muj.sbf.service;

import java.util.List;

import org.muj.sbf.domain.User;

public interface UserService {

	/**
	 * Persist a new user record in the use table
	 * 
	 * @param user
	 * @return User. The newly created user
	 */
	User addUser(User user);

	/**
	 * 
	 * Search the user table for term and get the maximum of given size from
	 * start.
	 * 
	 * @param term
	 * @param start
	 * @param size
	 * @return List of users
	 */
	List<User> search(String term, int start, int size);

	/**
	 * Get total number of records found with search term.
	 * 
	 * @param term
	 * @return Long Hit count
	 */
	Long searchHitCount(String term);

}
