package com.dhanush.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dhanush.domain.Person;


/**
 * {@link PagingAndSortingRepository} extension to query for {@link Person} objects
 * 
 * @author Dhanush Gopinath
 * @version 1.0.0
 *
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {

	/**
	 * Returns the persons with lastname as lastname parameter passed in
	 * 
	 * @param lastName
	 * @return
	 */
	public List<Person> findByLastName(String lastName);
}
