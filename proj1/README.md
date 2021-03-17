# README

## Overview

TodoMk2 is a todo app that holds two lists that pass objects between them. This allows users to have one list for everything that comes up, and one list for actionable next steps.

On startup, an API call is made to a server that holds some example tidbits. These tidbits are loaded into the appropriate list if and when a reply is recieved. This is done on a seperate thread to avoid lag. Users are brought to the main activity, which contains two fragment todo lists. Users can add new tidbits to the 'Backlog' list, and can move tidbits between lists with a single tap. Users can delete tidbits from either list with a long tap.

**Author**: Akiah Tullis

**Overview video link**: https://youtu.be/rz-k_Wpx0Jc

## Features

### Required features:

* [x] **3 Activities** - At least 2 Activities that are used in the app, 1+ activities launched
* [x] **2 custom Fragments on the same Activiy** - 2+ fragments on a single activity that work together. Do not put any code that controls that fragment into the Activity
* [x] **Web Service** - Use Retrofit or similar library to make a web service API call to a server on the internet
* [x] **Camera** - Use the Android camera activity to take a photo ... and display that image in an ImageView
* [x] **Design Patterns** - Implement the Observer and Singleton patterns
* [x] **Readme**
* [x] **Video**

### Additional features:
* [x] **Recycler View** - implement a RecyclerView that holds multiple types of Views
* [ ] **CRUD** - Users can create, read, update, and delete tidbits *3/4*
* [x] **Database** - implement an online database to store app data
* [x] **XML Values** - use XML to store UI-related values and colors

### Next Steps
* [ ] upload tidbits to server
* [ ] save todo lists on device, and load them on startup
* [ ] use toasts and/or snackbars for better feedback
* [ ] select photo already on phone for image tidbit
* [ ] implement 'voice' tidbit
* [ ] allow editing tasks, implement edit task activity
