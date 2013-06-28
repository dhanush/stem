package com.dhanush.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dhanush.domain.Person;

/**
 * Unit test for {@link PersonRepository}
 * 
 * @author Dhanush Gopinath
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-spring-repo-context.xml")
public class PersonRepositoryTest {
	
	@Autowired
	private PersonRepository personRepository;

	@Before
	public void setUp() {
		Person person = new Person();
		person.setFirstName("Dhanush");
		person.setLastName("Gopinath");
		person.setId("dhanush");
		personRepository.save(person);
	}
	
	@After
	public void tearDown() {
		personRepository.deleteAll();
	}
	
	@Test
	public void test() {
		List<Person> persons = personRepository.findByLastName("Gopinath");
		assertNotNull(persons);
		assertFalse(persons.isEmpty());
		assertEquals("Dhanush", persons.get(0).getFirstName());
		assertTrue(personRepository.findByLastName("Ghosh").isEmpty());
	}

}
