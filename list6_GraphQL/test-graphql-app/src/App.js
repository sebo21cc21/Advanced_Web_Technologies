const { createServer } = require('node:http')
const { createYoga, createSchema } = require('graphql-yoga')

const schema = createSchema({
    typeDefs: /* GraphQL */ `
        type Query {
            demo: String!
        }
    `,
    resolvers: {
        Query: {
            demo: () => 'Witaj, GraphQL działa!',
        }
    }
})

// Create a Yoga instance with a GraphQL schema.
const yoga = createYoga({ schema })

// Pass it into a server to hook into request handlers.
const server = createServer(yoga)

// Start the server and you're done!
server.listen(4000, () => {
    console.info('Server is running on http://localhost:4000/graphql')
})
