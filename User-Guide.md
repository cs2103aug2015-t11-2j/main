# About
Now that you know what Yui is [about](../README.md)(haven't update), you can follow this guide to learn how to use Yui effectively.

# Table of Contents
- [Quick Start](#quick-start)
- [Feature Details](#feature-details)
  - [Add Events](#add-events)
    - [Simple add](#simple-add)
    - [Add events with deadlines](#add-events-with-deadlines)
    - [Add events with start and end time](#add-events-with-start-and-end-time)
  - [Show Events](#show-events)
  - [Outline](#outline)
  - [Mark Completed](#mark-completed)
  - [Comment Events](#comment-events)
  - [Update and Delete Events](#update-and-delete-events)
  - [Search Event](#search-event)
  - [Undo and Redo](#undo-and-redo)
  - [Import Export the data](#import-export-the-data)
  - [Recurring Tasks](#recurring-tasks)
  - [Help List](#help-list)
  - [Exiting](#exiting)
- [Cheatsheet](#cheatsheet)

# Quick Start
1. **Install JDK 8u40 or later**: Earlier Java version will not work. You can get it 
   [here] (http://www.oracle.com/technetwork/java/javase/downloads/index.html).
2. **Download Yui**: You can then download `Yui.jar` from the latest release 
   here: https://github.com/Yui/releases   (Haven't put up yet)
3. **Launch Yui** Simply double-click on the `Yui.jar` file to start Yui. 
   You will be greeted with a simple interface that has a command bar. 
   This command bar is where you enter short commands to tell Yui what to do. <br>
4. **Add new events**. You can type `add <event>` to add a new event to your to-do list.
5. **Show event list**: The event list can be shown by typing `show`. 
6. **Try more commands**: 
     * `comment <Your Comment>` - Add comments to your added events
     * `-help` - See all the commands available
     * `exit` - Exit Yui using the command bar

To learn more details of Yui's features, refer to the 'Feature Details' section below. 

# Feature Details
## Add Events
There are three formats to add new events to users' to-do list
### Simple add
Use command `add <event>` to add a simple event quickly.

Examples:
* `add have a CS2013T class`
* `add date with girl friend`
* `add have lunch with Mao`

### Add events with deadlines
Use command `add <event> by <XX:XX> <DD/MM/YY>` to add an event with the deadlines

Examples:
* `add finish CS2103T tutorial by 9:00 tomorrow`
* `add buy flower for girl friend by 20:00 05/10/2015`
* `add cook lunch by 12:00 today`

### Add events with start and end time
Use command `add <event> from <XX:XX> to <XX:XX> <DD/MM/YY>` to add an event with start time and deadline

Example:
* `add have CS2103T class from 9:00 to 11:00 tomorrow`
* `add date with girl friend from 20:00 to 24:00 05/10/2015`
* `add have lunch with father from 12:00 to 13:00 today`

## Show Events
Show all the events added by typing following command. These events would be shown in a table.

`show`

## Outline
Show all the events which need to be done today or tomorrow by typing following command. These events would be shown in a table.

`outline`

## Mark Completed
Mark the event as completed by typing following command. Then, the event would not be shown in your reminder list any more.

`mark`
> Tip: the function can only be used when an event has been chosen.

## Comment Events
Use `comment <Your Comment>` to add comments to your selected event. You can add some details for the event.

Example:
* `comment E3-06-09`
* `comment remember to buy flower`
* `comment do not eat eggs`

> Tip: the function can only be used when an event has been chosen.

## Update and Delete Events
You can delete the event with the following command.

`delete`
> Tip: the function can only be used when an event has been chosen.

You can update the information of the event with the following command.

`update`
> Tip: the function can only be used when an event has been chosen. Also, when you update the event, the original information would be shown in the command window and original event would be deleted. The user can proceed to add the event after modification.

## Search Event
Use `search <Key Word>` to search events from your to-do lists. These events would be shown in a table.

Example:
* `search class`
* `search dating`
* `search lunch`

## Undo and Redo
You can use the following command to undo your last operation.

`undo`
> Tip: Users can also use `Ctrl + Z` to achieve the undo operation.

You can use the following command to redo the operation "undo"ed.

`redo`
> Tip: these operations can only be saved before closing the software.

## Import Export the data
You can use the following command to open the folder where the data file is saved.

`locate`
> Tip: As the folder has been opened, you can backup the date file and copy it to another computer. Also, you can copy the file from other computers to this computer.

## Recurring Tasks
Use `recur <days>` to set this event as a recurring task.

Example:
* `recur 7`
* `recur 30`

> Tip: the function can only be used when an event has been chosen.

## Help List
Show the help list and see all the commands available. Type the following command in the command bar.

`-help`
> Tip: The available commands are shown in the cheatsheet below.

## Exiting
Simply close the window or type the following command in the command bar.

`exit`

# Cheatsheet
Command | Description
--------| ------------
`add <event> <XX:XX> <DD/MM/YY>` | Add a new event with deadline to users' to-do list
`add <event>`| Add a new event without details
`add <event> <XX:XX> to <XX:XX> <DD/MM/YY>` | Add a new event with specific do-time
`mark`| Mark event as done
`comment <Your Comment>`| Comment on users' current event
`show` | Show all events
`locate` | Show the data file in the file explorer which allows users to import/export or backup
`update` | Update the details of users' events
`undo` | Undo the previous operation (This can also be achieved by Ctrl + Z)
`redo` | Redo the undo operation
`delete` | Delete the event
`-help` | Call the help menu to view all the commands available
`outline` | Show today and tomorrow outlines to users
`recur <days>` | Creat recuring tasks
`search <Key Word>` | Search for the event in your list
`exit` | Exit Yui
