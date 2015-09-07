# About
Now that you know what Just-Do is [about](../README.md)(haven't update), you can follow this guide to learn how to use Just-Do effectively.

# Table of Contents
- [Quick Start](#quick-start)
- [Feature Details](#feature-details)
  - [Marking code](#marking-code)
  - [Collating](#collating)
    - [Omitting sub folders](#omitting-sub-folders)
    - [Collating only certain types of files](#collating-only-certain-types-of-files)
  - [Viewing an author's statistics](#viewing-an-authors-statistics)
  - [Viewing the summary](#viewing-the-summary)
  - [Exiting](#exiting)
  - [Using the Text UI](#using-the-text-ui)
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

## Exiting
Simply close the window or type the following command in the command bar.

`exit`

# Cheatsheet
Command | Description
--------| ------------
`add <event> <XX:XX> <DD/MM/YY>` | Add a new event with deadline to users' to-do list
`add <event>`| Add a new event without details
`add <event> <XX:XX> <XX:XX> <DD/MM/YY>` | Add a new event with specific do-time
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
