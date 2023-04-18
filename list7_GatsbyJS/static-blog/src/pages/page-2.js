import * as React from "react"
import { Link, graphql } from "gatsby"
import { getImage, GatsbyImage } from "gatsby-plugin-image"
import Layout from "../components/layout"
import Seo from "../components/seo"
import "bootstrap/dist/css/bootstrap.min.css"
import '../components/index.css'
import { StaticImage } from "gatsby-plugin-image"

const SecondPage = ({ data }) => {
  const [searchQuery, setSearchQuery] = React.useState('')
  const post1 = data.post1
  const post2 = data.post2

  const filteredPosts = [post1, post2].filter(post => {
    const title = post.frontmatter.title.toLowerCase()
    const query = searchQuery.toLowerCase()
    return title.includes(query)
  })

  return (
    <Layout>
      <Seo title="Page two" />
      <div className="search-bar">
  <input
    type="text"
    className="form-control"
    placeholder="Search titles"
    value={searchQuery}
    onChange={e => setSearchQuery(e.target.value)}
  />
  <button type="submit">Search</button>
</div>


      {filteredPosts.map(post => (
        <div className="article-box">
          {post.frontmatter.featuredImage ? (
            <GatsbyImage
              image={getImage(post.frontmatter.featuredImage)}
              alt="My Image"
              width={500}
            />
          ) : (
            <StaticImage
              src="../images/Astronaut.jpg"
              alt="My Image"
              width={500}
            />
          )}

          <h1 className="display-4">
            <Link to={`/${post.frontmatter.slug}`}>{post.frontmatter.title}</Link>
          </h1>
          <p className="lead">{post.frontmatter.date}</p>
          <p>{post.frontmatter.author}</p>
          <p>{post.excerpt}</p>
        </div>
      ))}

      <Link to="/" className="btn btn-primary">Go back to the homepage</Link>
    </Layout>
  )
}

export const query = graphql`
  query {
    post1: markdownRemark(fileAbsolutePath: { regex: "/posts/post1.md/" }) {
      frontmatter {
        title
        date(formatString: "MMMM DD, YYYY")
        author
        slug
      }
      excerpt
      html
    }
    post2: markdownRemark(fileAbsolutePath: { regex: "/posts/post2.md/" }) {
      frontmatter {
        title
        date(formatString: "MMMM DD, YYYY")
        author
        slug
        featuredImage {
          childImageSharp {
            gatsbyImageData(
              width: 500
              placeholder: BLURRED
              formats: [AUTO, WEBP, AVIF]
            )
          }
        }
      }
      excerpt
      html
    }
  }
`

export default SecondPage
