module.exports = { 
  siteMetadata: { 
  title: 'Static Blog', 
  description: `This is example of static Gatsby blog`, 
  author: `@pwr`, 
  }, 
  plugins: [ 
  'gatsby-transformer-remark', 
  `gatsby-plugin-image`,
  `gatsby-plugin-sharp`,
  `gatsby-transformer-sharp`,
  { 
  resolve: `gatsby-source-filesystem`, 
  options:{ 
  name: `src`, 
  path: `${__dirname}/src/`
  } 
  }, 
  ], 
 }; 