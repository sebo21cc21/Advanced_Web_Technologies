<template>
  <CustomNavbar></CustomNavbar>
  <div class="d-flex justify-content-center p-5 container">
    <div class="text-center">
      <p class="display-1 text-primary">Biblioteka</p>
      <h2 class="text-secondary">Wybierz opcję z dostępnych u góry</h2>
    </div>
  </div>
</template>

<script>
import BookForm from "@/components/book/BookForm.vue";
import AuthorTable from "@/components/author/AuthorTable.vue";
import CustomNavbar from "@/components/utils/CustomNavbar.vue";

export default {
  name: "Main",
  components: {CustomNavbar, AuthorTable, BookForm}
}
</script>

<style scoped>
img {
  border-radius: 30px;
}
</style>