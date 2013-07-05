STEM
====

[![Build Status](https://travis-ci.org//dhanush/stem.png)](https://travis-ci.org/dhanush/stem)

STEM stands for Spring Testing with Embeddable Mongo database. This is a library which will help you embed flapdoodle's embeddable MongoDB into your Spring application for testing, if you are using MongoDB as your database. 

You can use this library into your Maven project and use the Embeddable Mongo database provided by [flapdoodle](https://github.com/flapdoodle-oss/embedmongo.flapdoodle.de) for testing. The project stem-usage along with the stem project explains the usage of stem-core

## What needs to be done ##

To your project where you use Spring Data Repositories, add the following in the POM.

    	<dependency>
			<groupId>com.dhanush.stem</groupId>
			<artifactId>stem-core</artifactId>
			<version>1.0.0</version>
		</dependency>

In your project's test spring context xml, add the following entries:

    
	<import resource="classpath*:spring/spring-mongo-context.xml" />

	<mongo:repositories base-package="com.dhanush.repository"
		mongo-template-ref="mongoTemplate">
	</mongo:repositories>

The first line (import statment) imports the `EmbeddedMongoDBFactory` implementation from stem-core project.

The second line declares your mongo respository package that you should scan.

Finally write your JUnit Test Case

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

During the Junit test case execution it will save and query in the embeddable MongoDB