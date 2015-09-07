# About
Now that you know what Just-Do is [about](../README.md)(haven't update), you can follow this guide to learn how to use Just-Do effectively.

# Table of Contents
- [Quick Start](#quick-start)
- [Feature Details](#feature-details)
  - [Add Events](#add-event)
    - [Simple add](#simple-add)
    - [Add events with details](#add-events-with-details)
    - [Add events with start and end time](#add-events-with-start-and-end-time)
  - [Show Events](#show-events)
  - [Mark Completed](#mark-completed)
  - [Comment Events](#comment-events)
  - [Update and Deltet Events](#update-and-delete-events)
  - [Search Event](#search-event)
  - [Undo Redo](#undo-redo)
  - [Import Export the data](#import-export-the-data)
  - [Recurring Tasks](#recurring-tasks)
  - [Help List](#help-list)
  - [Exiting](#exiting)
- [Cheatsheet](#cheatsheet)

# Quick Start
1. **Install JDK 8u40 or later**: Earlier Java version will not work. You can get it 
   [here] (http://www.oracle.com/technetwork/java/javase/downloads/index.html).
2. **Download Just_Do**: You can then download `Just_Do.jar` from the latest release 
   here: https://github.com/Just_Do/releases   (Haven't put up yet)
3. **Launch Just-Do** Simply double-click on the `Just_Do-GUI.jar` file to start Just_Do. 
   You will be greeted with a simple interface that has a command bar. 
   This command bar is where you enter short commands to tell Collate what to do. <br>
4. **Add new events**. You can type `add <event>` to add a new event to your to-do list.
5. **Show event list**: The event list can be shown by typing `show`. 
6. **Try more commands**: 
     * `comment <Your Comment>` - Add comments to your added events
     * `-help` - See all the commands available
     * `exit` - Exit Just_Do using the command bar

To learn more details of Just_Do features, refer to the 'Feature Details' section below. 

# Feature Details
## Add Events
There are three formats to add new events to users' to-do list
### Simple add
Use command `add <event>` to add a simple event quick.

Examples:
* `add have a CS2013T class`
* `add dating with girl friend`
* `add having lunch with Mao`

### Add events with details
Use command `add <event> <XX:XX> <DD/MM/YY>` to add an event with the start time

Examples:
* `add have CS2103T class 9:00 tomorrow`
* `add have a dating 20:00 05/10/2015`
* `add have lunch with Mao 12:00 today`

### Add events with start and end time
Use command `add <event> <XX:XX> to <XX:XX> <DD/MM/YY>` to add an event with start time and deadline

Example:
* `add have CS2103T class 9:00 to 11:00 tomorrow`
* `add have a dating 20:00 to 24:00 05/10/2015`
* `add have lunch with Mao 12:00 to 13:00 today`

## Show Events
Show all the events added by typing following command. These events would be shown in a table.

`show`

## Mark Completed
Mark the event as completed by typing following command. Then, the event would not be shown in your reminder list any more.

`mark`
> Tip: the function can only be used when an event has been chosed.

## Comment Events
Use `comment <Your Comment>` to add comments to your selected event. You can add some details for the event.

Example:
* `comment E3-06-09`
* `comment remember to take flower`
* `comment Do not eat eggs`

> Tip: the function can only be used when an event has been chosed.

## Update and Deltet Events

## Search Event

## Undo Redo

## Import Export the data

## Recurring Tasks

## Help List

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
`locate` | Show the file in the file explorer to allow users to import/export or backup
`update` | Update the details of users' events
`undo` | Undo the previous operation (This is also able to do by Ctrl + Z)
`redo` | Redo the undo operation
`delete` | Delete the event
`-help` | Call the help menu to view all the commands available
`outline` | Show today and tomorrow outlines to users
`recur <days>` | Creat recuring tasks
`search <Key Word>` | Search for the event in your list
`exit` | Exit Just_Do
