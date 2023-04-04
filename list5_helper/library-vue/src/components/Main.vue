<template>
  <CustomNavbar></CustomNavbar>
  <div class="d-flex justify-content-center p-5 container">
    <div class="text-center">
      <h1 class="text-primary">Witamy w bibliotece</h1>
      <h2 class="text-secondary">Wybierz opcję u góry</h2>
      <img src="biblioteka.jpg" alt="Biblioteka" width="800">
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