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
* This will run 3 things: mongo, anagram and artifactory
* Go to http://localhost:8080/anagram/generate/Xyz
* Check data is inserted
* Run contract test using spring-cloud-contract
```
$ docker run --rm -e "APPLICATION_BASE_URL=http://192.168.0.8:8080" -e "PUBLISH_ARTIFACTS=true" -e "PROJECT_NAME=anagram" -e "PROJECT_GROUP=anagram" -e "REPO_WITH_BINARIES_URL=http://192.168.0.8:8081/artifactory/libs-release-local" -e "PROJECT_VERSION=0.0.1.RELEASE" -v "/Users/robbiemartinus/dev/rmartinus/reactive-anagram/src/test/resources/contracts/:/contracts:ro" -v "/Users/robbiemartinus/dev/rmartinus/reactive-anagram/src/test/resources/contracts/output:/spring-cloud-contract-output/" springcloud/spring-cloud-contract:1.2.3.RELEASE
```
* Check artifactory has the stubs: http://localhost:8081/artifactory/libs-release-local
* On the consumer side, you run the stub using spring-cloud-contract-stub-runner
```
$ docker run --rm -e "STUBRUNNER_IDS=anagram:anagram:0.0.1.RELEASE:stubs:9876" -e "STUBRUNNER_REPOSITORY_ROOT=http://192.168.0.8:8081/artifactory/libs-release-local" -p "8083:8083" -p "9876:9876" springcloud/spring-cloud-contract-stub-runner:"1.2.3.RELEASE"
```

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