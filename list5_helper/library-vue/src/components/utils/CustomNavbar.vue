<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
      <a class="navbar-brand ms-3" href="/">Home</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02"
              aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item active">
            <a class="nav-link" href="/books">Lista książek</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="/book-form">Dodaj książkę</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="/authors">Lista autorów</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="/author-form">Dodaj autora</a>
          </li>
        </ul>
      </div>
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