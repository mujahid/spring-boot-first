package org.muj.sbf.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.muj.sbf.domain.User;

/**
 * Custom repository implementation of UserRepoCustom
 * 
 * @author momujah
 */
public class UserRepoImpl implements UserRepoCustom {

	@PersistenceContext
	private EntityManager em;

	
	@Override
	public List<User> search(String term, int start, int size) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select u from User u where");
		queryBuilder.append(" concat(lower(u.firstName) , ' ' ,lower(u.lastName)) like :term");
		queryBuilder.append(" or concat(lower(u.firstName) ,lower(u.lastName)) like :term");
		queryBuilder.append(" or concat(lower(u.lastName) , ' ' ,lower(u.firstName)) like :term");
		queryBuilder.append(" or concat(lower(u.lastName) ,lower(u.firstName)) like :term");
		queryBuilder.append(" ESCAPE '|'");
		return em.createQuery(queryBuilder.toString(), User.class).setParameter("term", "%" + term + "%")
				.setFirstResult(start).setMaxResults(size).getResultList();
	}

	
	@Override
	public Long searchHitCount(String term) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select count(u) from User u where");
		queryBuilder.append(" concat(lower(u.firstName) , ' ' ,lower(u.lastName)) like :term");
		queryBuilder.append(" or concat(lower(u.firstName) ,lower(u.lastName)) like :term");
		queryBuilder.append(" or concat(lower(u.lastName) , ' ' ,lower(u.firstName)) like :term");
		queryBuilder.append(" or concat(lower(u.lastName) ,lower(u.firstName)) like :term");
		queryBuilder.append(" ESCAPE '|'");
		return em.createQuery(queryBuilder.toString(), Long.class).setParameter("term", "%" + term + "%")
				.getSingleResult();
	}

}
