<template>
  <div id="person-form" class="container mt-5">
    <h1>Dodaj nowego autora</h1>
    <form @submit.prevent="handleSubmit">
      <label>Imię</label>
      <input
          v-model="author.firstName"
          type="text"
          :class="{ 'has-error': submitting && invalidFirstName }"
          @focus="clearStatus"
          @keypress="clearStatus"
      />
      <label>Nazwisko</label>
      <input
          v-model="author.lastName"
          type="text"
          :class="{ 'has-error': submitting && invalidLastName }"
          @focus="clearStatus"
      />
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">
        Dane poprawnie zapisano
      </p>
      <button class="btn btn-primary">Dodaj autora</button>
    </form>
  </div>
</template>
<script>

import axios from "axios";

export default {
  name: 'AuthorForm',
  props: {
    authorsSource: Array,
  },
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      author: {
        firstName: '',
        lastName: '',
      },
    }
  },
  methods: {

    async postData() {
      try {
        const response = await axios.post('http://localhost:8080/post/author', {
          firstName: this.author.firstName,
          lastName: this.author.lastName,
        })
        console.log(response.data)
        const savedAuthors = await fetch('http://localhost:8080/get/authors')
        const authors = await savedAuthors.json()
        this.authorsSource.push(authors[authors.length - 1])
      } catch (error) {
        console.error(error)
      }
    },

    handleSubmit() {
      this.submitting = true
      this.clearStatus()

      if (this.invalidFirstName || this.invalidLastName) {
        this.error = true
        return
      }
      this.postData()
      this.author = {
        firstName: '',
        lastName: '',
      }
      this.error = false
      this.success = true
      this.submitting = false
    },
    clearStatus() {
      this.success = false
      this.error = false
    },
  },
  computed: {
    invalidFirstName() {
      return this.author.firstName === ''
    },
    invalidLastName() {
      return this.author.lastName === ''
    },
  },
}
</script>
<style scoped>
form {
  margin-bottom: 2rem;
}

[class*='-message'] {
  font-weight: 500;
}

.error-message {
  color: #d33c40;
}

.success-message {
  color: #32a95d;
}
</style>