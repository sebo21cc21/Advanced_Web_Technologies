# GraphQL

This repository provides an implementation of a GraphQL server that allows you to manage users and their todos.

## Setup

To run the GraphQL server, follow these steps:

Install dependencies by running ```npm install```
- In terminal 1, run ```node src/staticApp.js```
- In terminal 2, run ```npm start```
- In terminal 3, run ```node src/databaseApp.js```
The server should now be up and running.
## Usage

You can use a GraphQL client to interact with the server. Here are some example queries and mutations:

## Queries

+ Get a user's login, name, and todos

```
query {
  user(id: 1) {
    login
    name
    todos {
      title
    }
  }
}

```
+ Get all todos and their users
```
query {
  todos{
    title
    users {
      name
    }
  }
}

```
+ Get all users and their todos
```
query {
  users {
    id
    login
    name
    todos {
      id
      title
    }
  }
}

```

## Mutations
+ Create a user
```
mutation {
  createUser(input: { name: "Jan Kowalski", email: "jan.kowalski@example.com", login: "jankow" }) {
    id
    name
    email
    login
  }
}
```
+ Update a user
```
mutation {
  updateUser(input: { id:11, name: "Janek Kowal", email: "jan12.kowalski@example.com", login: "djankow" }) {
    id
    name
    email
    login
  }
}

```
+ Delete a user

```
mutation {
  deleteUser(id:11)
}

```
+ Create a todo

```
mutation {
  createTodo(
    input: {
      title: "New todo",
      completed: false,
      userId: 1
    })
  {
    id
    title
    completed
    user {
      id
    }
  }
}
```
+ Update a todo
```
mutation {
  updateTodo(
    id: 1,
    input: {
      title: "Updated todo",
      completed: true
      userId: 1
    }
  ) {
    id
    title
    completed
    user {
      id
      name
    }
  }
}

```
+ Delete a todo
```
mutation {
  deleteTodo(id: 205)
}
```

## Note
This implementation assumes that you have a running database instance. Please make sure to provide the correct configuration in src/config.js. 
Be sure to run ``` npm install ```
