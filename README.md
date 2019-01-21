# Reactive Anagram

Given a string, respond with a set of all anagram permutations.

Eg.

Input: abc

Output: abc, cab, bca, acb, cba, bac

#### Endpoints:
* Make input string uppercase
* Generate a set of all anagram permutations given a string
* Save permutation in MongoDB

Built using Spring Reactor and Reactive MongoDB NoSQL database

## Starting
### Using docker-compose
* `$ ./gradlew clean build`
* `$ docker-compose up --build`
* Go to http://localhost:8080/anagram/generate/Xyz
* Check data is inserted

### Manually
* Start up mongod

  `$ sudo mongod`

* Start up app

  `$ ./gradlew bootRun`
  
* Run AnagramWebClient
* Go to mongo client

  `$ mongo`

* Check that data is inserted

  `> use anagram`
  
  `> db.anagram.find()`