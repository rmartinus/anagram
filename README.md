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
* Start up app

  `$ ./gradlew bootRun`
  
* Start up mongod

  `$ sudo mongod`

* Run AnagramWebClient
* Go to mongo client

  `$ mongo`

* Check that data is inserted

  `> use anagram`
  
  `> db.anagram.find()`