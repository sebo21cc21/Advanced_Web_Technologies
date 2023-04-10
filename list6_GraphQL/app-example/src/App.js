const { GraphQLServer } = require('graphql-yoga');
const axios = require("axios");

async function getRestUsersList() {
    try {
        const users = await axios.get("https://jsonplaceholder.typicode.com/users");
        console.log(users);
        return users.data.map(({ id, name, email, username }) => ({
            id: id,
            name: name,
            email: email,
            login: username,
        }))
    }
    catch (error) {
        throw error
    }
}

async function getRestUserById(id) {
    try {
        const user = await axios.get(`https://jsonplaceholder.typicode.com/users/${id}`);
        return {
            id: user.data.id,
            name: user.data.name,
            email: user.data.email,
            login: user.data.username,
        };
    }
    catch (error) {
        throw error;
    }
}

async function getRestTodosList() {
    try {
        const users = await axios.get("https://jsonplaceholder.typicode.com/todos");
        console.log(users);
        return users.data.map(({ id, title, completed, userId }) => ({
            id: id,
            title: title,
            completed: completed,
            user_id: userId
        }))
    }
    catch (error) {
        throw error
    }
}

async function getRestTodosById(id) {
    try {
        const user = await axios.get(`https://jsonplaceholder.typicode.com/todos/${id}`);
        return {
            id: user.data.id,
            title: user.data.title,
            completed: user.data.completed,
            user_id: user.data.userId,
        };
    }
    catch (error) {
        throw error;
    }
}


const resolvers = {
    Query: {
        demo: () => 'Witaj, GraphQL działa',
        users: async () => getRestUsersList(),
        todos: async () => getRestTodosList(),
        todo: async (parent, args, context, info) => getRestTodosById(args.id),
        user: async (parent, args, context, info) => getRestUserById(args.id)
    },
    User: {
        todos: async (parent, args, context, info) => {
            try {
                const todos = await axios.get(`https://jsonplaceholder.typicode.com/todos?userId=${parent.id}`);
                return todos.data;
            } catch (error) {
                throw error;
            }
        }
    },
    ToDoItem: {
        user: async (parent, args, context, info) => getRestUserById(parent.user_id)
    }
}

const server = new GraphQLServer({
    typeDefs: './src/schema.graphql',
    resolvers,
});

server.start(() => console.log(`Server is running on http://localhost:4000`));
