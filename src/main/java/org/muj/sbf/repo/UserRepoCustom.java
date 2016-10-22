package org.muj.sbf.repo;

import java.util.List;

import org.muj.sbf.domain.User;

public interface UserRepoCustom {
	/**
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
