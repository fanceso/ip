# duke.Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()`. If the setup is correct, you should see something like the below:



#### Level 0 
Create Hello message

#### Level 1 
Create Simple User input command

#### Level 2. List, Add Contents 
Allow user to add contents into the list

#### Level 3. Tasks Class, MarkAsDone
Create New duke.task.Task Class

#### Level 4. ToDos, Events, Deadlines
Add support for tracking three types of tasks:

##### 1. ToDos: tasks without any date/time attached to it e.g., visit new theme park
##### 2. Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
##### 3. Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm

#### Level 5. Error Handling
