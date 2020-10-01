# Duke Chat Bot User Guide  
  
Duke is a **Command Line Interface** (CLI) app which allows you to store 3 types of task; *todo*, *deadline*, *event*.   
Designed for fast typer which allows you to **store**, **find**, and **mark** personal tasks effortlessly.  
  
- - -  
## Content Table  
1. [Setting up](#setting-up)    
  
2. [Features](#features)  
    2.1 [Adding To-do Task](#todo) [**todo**]  
    2.2 [Adding Event Task](#event) [**event**]  
    2.3 [Adding Task with a Deadline](#deadline) [**deadline**]  
    2.4 [Listing out all your tasks](#list) [**list**]  
    2.5 [Deleting a Task from list](#delete) [**delete**]  
    2.6 [Completing a task](#done) [**done**]  
    2.7 [Finding tasks](#find) [**find**]  
    2.8 [Terminate the program](#bye) [**bye**]  
  
3. [Commands Summary](#commands-summary)  
- - -  
  
### Setting up  
#### Prerequisites  
- JDK 11 or above  
- Download the latest release [here](https://github.com/fanceso/ip/releases "duke.jar").   
- Permission rights to create a file and folder in machine  
  
##### 1. Start up your terminal and locate the file path of *duke.jar*  
  
##### 2. Enter the following commands to run the app 
```bat  
java -Dfile.encoding=UTF-8 -jar duke.jar  
```

###### Correct Output Result: 
```shell script  
-----------------------------------------------------------  
Version: DUKE 0.2
Hello! I'm Duke.
What can I do for you?  
-----------------------------------------------------------  
Enter command:  
```  
  
##### 3. You should see a new folder named "data-folder" and inside folder will contain a "tasks.txt" which have been created by Duke app. Your data will be store in the file.  
  
> *Refer to the Features section for the different command features of the Duke app.*
  
- - -  
  
### <h3 id="features"> Features  </h3>

#### <h4 id="todo">Adding To-do Task: `todo`  </h4>
Adding a *To-Do* type of task to the list with its description.  
```bat  
Format: todo <description>  
```

Example:  
* `todo submission CS2113 project`
* `todo 3 math assignments`
<br />

#### <h4 id="event"> Adding Event Task: `event` </h4>
Adding a *Event* type of task to the list with its description, together with the event date and time.  
Date and time entered must follow the following format accordingly. 
```bat  
Format: event <description> /at <dd/MM/YYYY HH:mm>  
```

Example:  
* `event Tom's Wedding Dinner /at 04/10/2020 19:30`  
* `event Math Project dicussion /at 01/10/2020 10:00` 
<br />

#### <h4 id="deadline"> Adding Task with a Deadline: `deadline`  </h4>
Adding a *Deadline* type of task to the list with its description and its due date. 
Date format must follow the following format accordingly.  
```bat  
Format: deadline <description> /by <dd/MM/YYYY>  
```

Example:  
* `deadline online submission /by 11/11/2020`  
* `deadline Capstone Project presentation /by 04/03/2022 `  
<br /> 

#### <h4 id="list">Listing out all your tasks: `list` </h4>
List out all tasks which added in the list or stored earlier in the data file. The numbering is the index of each task.
 
> [T] represents To-do task type
> [E] represents Event task type
> [D] represents Deadline task type
> [✘] represents the status of the task is uncompleted
> [✓] represents the status of the task is completed

Tasks are indexed according to the order in which the tasks were added to the list.  
```bat  
 Format: list
```  
<br />

#### <h4 id="delete">Deleting a Task from list: `delete`  </h4>
Removing an existing task from the list with the given index in numerical value. 
```bat  
Format: delete <index>
```

Example:  
* `delete 3`  
* `delete 1`  
<br />
                           
#### <h4 id="done">Completing a task: `done` </h4>
Changing the status to done with the selected task index in numerical value.
```bat  
 Format: done <index>
```

Example:  
* `done 2`  
* `done 1`  
<br /> 

####  <h4 id="find">Finding tasks: `find` </h4>
Finding all tasks which contain the keyword required.
```bat  
 Format: find <keyword>
```

Example:  
* `find submission`  
* `find math projects`  
<br /> 

#### <h4 id="bye"> Terminate the program: `bye`</h4>
Terminating and exits the app. 
```bat  
 Format: bye
```

##  <h2 id="commands-summary"> Commands Summary </h2>
| **Command** | **Format**                                      | **Example**                              |
|-------------|-------------------------------------------------|------------------------------------------|
| *todo*      | `todo `                                         | todo submit assignment                   |
| *event*     | `event <description> /at <dd/MM/YYYY HH:mm>  `  | event Tom's Wedding /at 04/10/2020 19:30 |
| *deadline*  | `deadline <description> /by <dd/MM/YYYY>  `     | deadline Final submission /by 03/10/2022 |
| *delete*    | `delete `                                       | delete 1                                 |
| *done*      | `done `                                         | done 3                                   |
| *find*      | `find `                                         | find submission                          |
| *bye*       | `bye`                                           | bye                                      |