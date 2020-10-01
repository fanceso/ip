
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

####### Correct Output Result: 
```shell script  
-----------------------------------------------------------  
Version: DUKE 0.2Hello! I'm Duke.What can I do for you?  
-----------------------------------------------------------  
Enter command:  
```  
  
##### 3. You should see a new folder named "data-folder" and inside folder will contain a "tasks.txt" which have been created by Duke app. Your data will be store in the file.  
  
####### Refer to the Features section for the different command features of the Duke app.  
  
- - -  
  
### Features  
<h5 id="todo"></h5>

#### Adding To-do Task: `todo`  
Adding a *To-Do* type of task to the list with its description.  
```bat  
Format: todo <description>  
```

Example:  
* `todo submission CS2113 project`
* `todo 3 math assignments`


<h5 id="event"></h5><br />

#### Adding Event Task: `event` 
Adding a *Event* type of task to the list with its description, together with the event date and time.  
Date and time entered must follow the following format accordingly. 
```bat  
Format: event <description> /at <dd/MM/YYYY HH:mm>  
```

Example:  
* `event Tom's Wedding Dinner /at 04/10/2020 19:30`  
* `event Math Project dicussion /at 01/10/2020 10:00` 


<h5 id="deadline"></h5><br />

#### Adding Task with a Deadline: `deadline`  
Adding a *Deadline* type of task to the list with its description and its due date. 
Date format must follow the following format accordingly.  
```bat  
Format: deadline <description> /by <dd/MM/YYYY>  
```

Example:  
* `deadline online submission /by 11/11/2020`  
* `deadline Capstone Project presentation /by 04/03/2022 `  


<h5 id="list"></h5><br /> 

#### Listing out all your tasks: `list` 
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

<h5 id="delete"></h5><br />

#### Deleting a Task from list: `delete`  
Removing an existing task from the list with the given index in numerical value. 
```bat  
Format: delete <index>
```

Example:  
* `delete 3`  
* `delete 1`  


<h5 id="done"></h5><br />
                           
#### Completing a task: `done` 
Changing the status to done with the selected task index in numerical value.
```bat  
 Format: done <index>
```

Example:  
* `done 2`  
* `done 1`  

<h5 id="find"></h5><br /> 

#### Finding tasks: `find` 
Finding all tasks which contain the keyword required.
```bat  
 Format: find <keyword>
```

Example:  
* `find submission`  
* `find math projects`  

<h5 id="find"></h5><br /> 

#### Terminate the program: `bye`
Terminating and exits the app. 
```bat  
 Format: bye
```

<h5 id="commands-summary"></h5><br /> 

## Commands Summary
| **Command** | **Format**        | **Example**                              |
|-------------|-------------------|------------------------------------------|
| *todo*      | `todo `           | todo submit assignment                   |
| *event*     | `event  /at  `    | event Tom's Wedding /at 04/10/2020 19:30 |
| *deadline*  | `deadline  /by  ` | deadline Final submission /by 03/10/2022 |
| *delete*    | `delete `         | delete 1                                 |
| *done*      | `done `           | done 3                                   |
| *find*      | `find `           | find submission                          |
| *bye*       | `bye`             | bye                                      |