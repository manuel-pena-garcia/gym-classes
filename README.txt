This a trial app oriented to show programming style & skills, simulating a gym structure where members can join classes and pay fees.

	- Developed with Spring Boot + Java 8
	- Provides various REST services with centralized Exception handling (pay a fee, create member, create activity, add member to activity...)
	- JPA with a H2 in-memory database
	- Logging with AOP and Lombok Logging
	- Quartz to schedule a task to create new fees for months that do not have one
	- Emailer to notify that a member paid a fee (must replace in application.properties with your valid smtp server & destination address)
	- Rest services securized with Oauth2 and Keycloak (standalone installation, realm.json file provided)
	
