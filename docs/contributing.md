# Contributing

## Join us

This guide is to help you get started and get in touch with us, if needed.

The main communication channel is our
[slack team](https://rlearnprograming.slack.com/shared_invite/MTM5NzQwOTU5MjY5LTE0ODY2OTQ4NjYtZDE0MGU4ZTY0NA).

Use
[trello](https://trello.com/invite/b/HZQ5RPer/36b3a64b8c2cc299f1088f547a62f60d/kanban)
to track our work. You will have to be in the slack room before you are able to be added to the trello board.


## Coding standard

This project follows the
[Google Java coding standard](https://google.github.io/styleguide/javaguide.html).
Please make sure you read, understand and follow the coding standard before
contributing.

Also in addition to the Google Java Standard, we will be adding a piece of 
documentation for the whole class that outlines what the class does, what objects it can create,
what Instance variables it has, what methods it contains, as well as a todo list. 
You may not see this kind of decimation all the time as a SWE, but it is
helpful from a readability standpoint for any new devs who want to support the project. 
  
This documentation should appear after the import statements, and before any actual code 
in the class. It should be ordered in the following format 

Class Description   
Instance Variables 
*  variable1 
    *  description 
*  variable2
    *  description 
  
Constructors 
*  MyObject(int parameter)
    *  description 

Methods 
*  public int getInt() 
    *  description 
*  public void method()
    *  description 
  
Todos 
*  Todo1
    *  Subtask1 
*  Todo2 
    
    

## Sharing your work

We use git to share our code.

On our github repository, there is the ```master``` branch. That's the
stable version of the code (it represents the last published, working version).

Also, there is the ```development``` branch. That's the branch where we develop
our code and test it until it is ready to be deployed to our users, case in
which the development branch will be merged to master.

In order to contribute, create a new branch *from development* and commit your
work on the new branch. Then, push your changes and create a merge request to
development. Your code will then be reviewed and merged. Please create a
branch for each new feature.

Branches that fix a bug in the previous version of the application (bugfix
branches) and changes to the documentation that concern the previous version
of the code should be merged to both development and master.

Name your branches sensibly.

When merging 2 branches, never fast forward (```git merge --no-ff```) such that
at every merge, a merge commit is created.
