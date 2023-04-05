<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand ms-3" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active"><a class="nav-link" href="/authors">Autorzy</a></li>
        <li class="nav-item active"><a class="nav-link" href="/author-form">Formularz Autorów</a></li>
        <li class="nav-item active"><a class="nav-link" href="/books">Książki</a></li>
        <li class="nav-item active"><a class="nav-link" href="/book-form">Formularz Książki</a></li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  name: "CustomNavbar"
}
</script>

<style scoped>

</style>