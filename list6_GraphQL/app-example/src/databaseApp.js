const { GraphQLServer } = require('graphql-yoga');
const mysql = require('mysql');

// Tworzenie połączenia z bazą danych
const connection = mysql.createConnection({
    host: 'localhost',
    user: 'admin',
    password: 'password',
    database: 'graph'
});

// Funkcja pomocnicza do wykonywania zapytań SQL
function query(sql, args) {
    return new Promise((resolve, reject) => {
        connection.query(sql, args, (error, results) => {
            if (error) {
                reject(error);
            } else {
                resolve(results);
            }
        });
    });
}

const resolvers = {
    Query: {
        demo: () => 'Witaj, GraphQL działa',
        users: async () => query('SELECT * FROM users'),
        todos: async () => query('SELECT * FROM todos'),
        todo: async (parent, { id }, context, info) => {
            const todos = await query('SELECT * FROM todos WHERE id = ?', [id]);
            return todos[0];
        },
        user: async (parent, { id }, context, info) => {
            const users = await query('SELECT * FROM users WHERE id = ?', [id]);
            return users[0];
        }
    },
    User: {
        todos: async (parent, args, context, info) => {
            const todos = await query('SELECT * FROM todos WHERE user_id = ?', [parent.id]);
            return todos;
        }
    },
    TodoItem: {
        user: async (parent, args, context, info) => {
            const users = await query('SELECT * FROM users WHERE id = ?', [parent.user_id]);
            return users[0];
        }
    },
    Mutation: {
        createUser: async (parent, { input }, context, info) => {
            const { name, email, login } = input;
            const sql = 'INSERT INTO users (name, email, login) VALUES (?, ?, ?)';
            const args = [name, email, login];
            const result = await query(sql, args);
            const userId = result.insertId;
            const user = await query('SELECT * FROM users WHERE id = ?', [userId]);
            return user[0];
        },

        updateUser: async (parent, { input }, context, info) => {
            const { id, name, email, login } = input;
            const update = await query('UPDATE users SET name = ?, email = ?, login = ? WHERE id = ?', [name, email, login, id]);
            const updatedUser = await query('SELECT * FROM users WHERE id = ?', [id]);
            return updatedUser[0];
        },

        deleteUser: async (parent, { id }, context, info) => {
            const sql = 'DELETE FROM users WHERE id = ?';
            const args = [id];
            await query(sql, args);
            return id;
        },
        createTodo: async (parent, { input }, context, info) => {
            const { title, completed, userId } = input;
            const sql = 'INSERT INTO todos (title, completed, user_id) VALUES (?, ?, ?)';
            const args = [title, completed, userId];
            const result = await query(sql, args);
            const todoId = result.insertId;
            const todo = await query('SELECT * FROM todos WHERE id = ?', [todoId]);
            return todo[0];
        },

        updateTodo: async (parent, { id, input }, context, info) => {
            const { title, completed } = input;
            await query('UPDATE todos SET title = ?, completed = ? WHERE id = ?', [title, completed, id]);
            const todo = await query('SELECT * FROM todos WHERE id = ?', [id]);
            return todo[0];
        },

        deleteTodo: async (parent, { id }, context, info) => {
            const sql = 'DELETE FROM todos WHERE id = ?';
            const args = [id];
            await query(sql, args);
            return id;
        }
    }
};

const server = new GraphQLServer({
    typeDefs: './src/databaseSchema.graphql',
    resolvers,
});

server.start({ port: 4002 }, () => console.log(`Server is running on http://localhost:4002`));
