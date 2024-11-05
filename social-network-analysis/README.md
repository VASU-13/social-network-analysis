# social-network-analysis

This project is a spring boot application that provides a REST API for managing users and their
friendships. The API allows you to

* Add, remove and list users
* Create and remove friendship between users.
* List all friends of particular user.

## Features
* User Management
  * Add a new user with name and email
  * Remove a user by userId
  * List all user

* Friendship Management
  * Create a friendship between two users using their userId's
  * Remove a friendship between two users using their userId's
  * List all friends of a user by their userId

## Technologies User
* Java 17
* Spring Boot latest Version (3.x.x)
* Hibernate JPA for data persistence
* MySQL
* JUnit 5 for testing
* Maven