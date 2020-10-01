<img src="https://www.ismartcom.com/hs-fs/hubfs/ai%20gif.gif?width=600&amp;name=ai%20gif.gif" alt="Duke" style="float:right;width:220px;height:auto;">
# Duke Chat Bot User Guide  
  
Duke is a **Command Line Interface** (CLI) app which allows you to store 3 types of task; *todo*, *deadline*, *event*.   
Designed for fast typer which allows you to **store**, **find**, and **mark** personal tasks effortlessly.  
  
- - -  
## Content Table  
1. [Setting up](#setting-up)    
  
2. [Features](#features)  
    2.1 [Adding To-do Task](#Adding-To-do-Task-todo) [**todo**]  
    2.2 [Adding Event Task](#Adding-Event-Task-event) [**event**]  
    2.3 [Adding Task with a Deadline](#Adding-Task-with-a-Deadline-deadline) [**deadline**]  
    2.4 [Listing out all your tasks](#Listing-out-all-your-tasks-list) [**list**]  
    2.5 [Deleting a Task from list](#Deleting-a-Task-from-list-delete) [**delete**]  
    2.6 [Completing a task](#Completin- a-task-done) [**done**]  
    2.7 [Finding tasks](#Finding-tasks-find) [**find**]  
    2.8 [Terminate the program](#Terminate-the-program-bye) [**bye**]  
  
3. [Commands Summary](#commands-summary)  
- - -  
  
### Setting up  
#### Prerequisites  
- JDK 11 or above  
- Download the latest release [here](https://github.com/fanceso/ip/releases/download/v0.2/duke.jar).   
- Permission rights to create a file and folder in machine  
  
##### 1. Start up your terminal and locate the file path of *duke.jar*  
  
##### 2. Enter the following commands to run the app 
```bat  
java -Dfile.encoding=UTF-8 -jar duke.jar  
```

###### Correct Output Result: 
```bat  
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
  
### Features 

#### Adding To-do Task: `todo` 
Adding a *To-Do* type of task to the list with its description.  
```bat  
Format: todo <description>  
```

Example:  
* `todo submission CS2113 project`
* `todo 3 math assignments`
<br />

#### Adding Event Task: `event`
Adding a *Event* type of task to the list with its description, together with the event date and time.  
Date and time entered must follow the following format accordingly. 
```bat  
Format: event <description> /at <dd/MM/YYYY HH:mm>  
```

Example:  
* `event Tom's Wedding Dinner /at 04/10/2020 19:30`  
* `event Math Project dicussion /at 01/10/2020 10:00` 
<br />

#### Adding Task with a Deadline: `deadline`
Adding a *Deadline* type of task to the list with its description and its due date. 
Date format must follow the following format accordingly.  
```bat  
Format: deadline <description> /by <dd/MM/YYYY>  
```

Example:  
* `deadline online submission /by 11/11/2020`  
* `deadline Capstone Project presentation /by 04/03/2022 `  
<br /> 

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
<br />

#### Deleting a Task from list: `delete`
Removing an existing task from the list with the given index in numerical value. 
```bat  
Format: delete <index>
```

Example:  
* `delete 3`  
* `delete 1`  
<br />
                           
#### Completing a task: `done`
Changing the status to done with the selected task index in numerical value.
```bat  
 Format: done <index>
```

Example:  
* `done 2`  
* `done 1`  
<br /> 

#### Finding tasks: `find` 
Finding all tasks which contain the keyword required.
```bat  
 Format: find <keyword>
```

Example:  
* `find submission`  
* `find math projects`  
<br /> 

#### Terminate the program: `bye`
Terminating and exits the app. 
```bat  
 Format: bye
```

## Commands Summary 

<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  overflow:hidden;padding:10px 5px;word-break:normal;}
.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
.tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
</style>
<table class="tg" style="undefined;table-layout: fixed; width: 683px">
<colgroup>
<col style="width: 80px">
<col style="width: 326px">
<col style="width: 277px">
</colgroup>
<thead>
  <tr>
    <th class="tg-0pky"><span style="font-weight:bold">Command</span></th>
    <th class="tg-0pky"><span style="font-weight:bold">Format</span></th>
    <th class="tg-0pky"><span style="font-weight:bold">Example</span></th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0pky"><span style="font-style:italic">todo</span></td>
    <td class="tg-0pky"><span style="color:#905;background-color:#DDD">todo &lt;description&gt; </span></td>
    <td class="tg-0pky">todo submit assignment</td>
  </tr>
  <tr>
    <td class="tg-0pky"><span style="font-style:italic">event</span></td>
    <td class="tg-0pky"><span style="color:#905;background-color:#DDD">event &lt;description&gt; /at &lt;dd/MM/yyyy HH:mm&gt;</span><br></td>
    <td class="tg-0pky">event Tom's Wedding /at 04/10/2020 19:30</td>
  </tr>
  <tr>
    <td class="tg-0pky"><span style="font-style:italic">deadline</span></td>
    <td class="tg-0pky"><span style="color:#905;background-color:#DDD">deadline &lt;description&gt; /by &lt;dd/MM/yyyy&gt;</span></td>
    <td class="tg-0pky">deadline Final submission /by 03/10/2022</td>
  </tr>
  <tr>
    <td class="tg-0pky"><span style="font-style:italic">delete</span></td>
    <td class="tg-0pky"><span style="color:#905;background-color:#DDD">delete &lt;index&gt;</span></td>
    <td class="tg-0pky">delete 1</td>
  </tr>
  <tr>
    <td class="tg-0pky"><span style="font-style:italic">done</span></td>
    <td class="tg-0pky"><span style="color:#905;background-color:#DDD">done &lt;index&gt;</span></td>
    <td class="tg-0pky">done 3</td>
  </tr>
  <tr>
    <td class="tg-0pky"><span style="font-style:italic">find</span></td>
    <td class="tg-0pky"><span style="color:#905;background-color:#DDD">find &lt;keyword&gt;</span></td>
    <td class="tg-0pky">find submission</td>
  </tr>
  <tr>
    <td class="tg-0pky"><span style="font-style:italic">bye</span></td>
    <td class="tg-0pky"><span style="color:#905;background-color:#ddd">`bye`</span></td>
    <td class="tg-0pky">bye</td>
  </tr>
</tbody>
</table>