<template>
  <CustomNavbar></CustomNavbar>
  <div id="authors-table" class="container">
    <table>
      <thead>
      <tr>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Usuń</th>
        <th>Aktualizuj</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(author, index) in paginatedAuthors()" :key="author.id">
        <td>{{ author.firstName }}</td>
        <td>{{ author.lastName }}</td>
        <td>
          <button class="btn btn-danger" @click="deleteAuthor(author.id)">Usuń</button>
        </td>
        <td>
          <button class="btn btn-primary" @click="this.$router.push({ name: 'authorUpdate', params: { id: author.id } });">Aktualizuj</button>
        </td>
      </tr>
      </tbody>
    </table>

    <div class="pagination">
      <button class="btn btn-primary" :disabled="currentPage === 1" @click="currentPage--">Prev</button>
      <span class="ms-3 me-3">Page {{ currentPage }} of {{ totalPages }}</span>
      <button class="btn btn-primary" :disabled="currentPage === totalPages" @click="currentPage++">Next</button>
    </div>
    <AuthorForm :authorsSource="authors" class="mt-5"/>
  </div>
</template>

<script>
import axios from "axios";
import AuthorForm from "@/components/author/AuthorForm.vue";
import CustomNavbar from "@/components/utils/CustomNavbar.vue";

export default {
  name: 'AuthorTable',
  components: {CustomNavbar, AuthorForm},

  data() {
    return {
      authors: [],
      currentPage: 1,
      itemsPerPage: 2,
    }
  },

  mounted() {
    this.getAuthors()
  },
  methods: {
    paginatedAuthors() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.authors.slice(startIndex, endIndex);
    },
    async getAuthors() {
      try {
        const response = await fetch('http://localhost:8080/get/authors')
        const data = await response.json()
        this.authors = data
      } catch (error) {
        console.error(error)
      }
    },
    async deleteAuthor(itemId) {
      axios.delete(`http://localhost:8080/delete/author/${itemId}`)
          .then(response => {
            // Obsługa odpowiedzi serwera
            console.log(response.data);
          })
          .catch(error => {
            // Obsługa błędów
            console.error(error);
          });
      this.authors = this.authors.filter(obj => {
        return obj.id !== itemId
      });
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.authors.length / this.itemsPerPage);
    }
  },

}
</script>

<style scoped>

</style>