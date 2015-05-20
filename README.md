java-utils
==========

[![Build Status](https://travis-ci.org/eidoscode/java-utils.svg?branch=master)](https://travis-ci.org/eidoscode/java-utils)
[![Build status](https://ci.appveyor.com/api/projects/status/v4fyhfqu23bcokfn/branch/master?svg=true)](https://ci.appveyor.com/project/antonini/java-utils/branch/master)

The purpose of this project is to group utilities that are used on most of other Java projects.


## Available Utilities

### 1. Collection

//... TODO

### 2. Multithread

//... TODO

### 3. IO

//... TODO

#### 3.1. Stream 

//... TODO

#### 3.2. Zip

//... TODO

### 4. JAXRS

//... TODO

### 5. Resources

//... TODO



## Requirements

* At least Java 7;
* Apache Maven 3.3+ (developer);



## How to contribute

Fork repository, make changes, send a pull request. We are going to review your changes and apply them to the `master`.

This project is created usign Apache Maven so check the requirements listed above to developers. 

Before sending a pull request don't forget to run all unit tests:

```
$ mvn clean install
```

If your default encoding is not UTF-8, some of unit tests will break. This
is an intentional behavior. To fix that, set this environment variable
in console (in Windows, for example):

```
SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
```

## Bugs, Improvement or Questions?

So, found a bug, want some improvement or just have a questions about how to do someting? Then [open an issue](https://github.com/eidoscode/java-utils/issues/new) an we will try to help you.
