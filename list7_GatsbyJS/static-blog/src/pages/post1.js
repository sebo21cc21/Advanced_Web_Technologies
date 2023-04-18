import * as React from "react"
import { Link } from "gatsby"
import { graphql } from 'gatsby'
import Layout from "../components/layout"
import Seo from "../components/seo"
import "../components/index.css"
const Post1 = ({ data, serverData }) => {
  const post1 = data.post1

  return (
    <Layout>
      <div class="center">
      <h1>
        Witaj w poście!
      </h1>
      <img
        style={{ width: "320px", borderRadius: "var(--border-radius)" }}
        alt="A random dog"
        src={serverData.message}
      />
      </div>
      <h1>
        <b>Treść posta:</b>
      </h1>
      <div class ="article-box">
      <p className="lead">{post1.frontmatter.date}</p>
      <p>{post1.frontmatter.author}</p>
      <div className="mt-5" dangerouslySetInnerHTML={{ __html: post1.html }} />
      </div>
    <div><Link to="/" className="btn btn-primary">Go back to the homepage</Link></div>
    </Layout>
  )
}

export const Head = () => <Seo title="Using SSR" />



export async function getServerData() {
  try {
    const res = await fetch(`https://dog.ceo/api/breed/shiba/images/random`)
    if (!res.ok) {
      throw new Error(`Response failed`)
    }
    return {
      props: await res.json(),
    }
  } catch (error) {
    return {
      status: 500,
      headers: {},
      props: {},
    }
  }
}

export const query = graphql`
  query {
    post1: markdownRemark(fileAbsolutePath: { regex: "/posts/post1.md/" }) {
      frontmatter {
        title
        date(formatString: "MMMM DD, YYYY")
        author
      }
      html
    }
  }
`
export default Post1