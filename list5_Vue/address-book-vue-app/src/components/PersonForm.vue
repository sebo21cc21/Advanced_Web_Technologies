<template>
    <div id="person-form">
      <form @submit.prevent="addPerson">
        <label>Imię i nazwisko</label>
        <input v-model="person.name" type="text" required />
        <label>Email</label>
        <input v-model="person.email" type="email" required />
        <label>Telefon</label>
        <input v-model="person.phone" type="tel" required />
        <button type="submit">Dodaj kontakt</button>
      </form>
    </div>
  </template>
  <script>
  export default {
    name: "person-form",
    data() {
      return {
        person: {
          name: "",
          email: "",
          phone: "",
        },
      };
    },
    methods: {
      addPerson() {
        const newPerson = {
          id: Date.now(),
          name: this.person.name,
          email: this.person.email,
          phone: this.person.phone,
        };
        this.$emit("addPerson", newPerson);
        this.person.name = "";
        this.person.email = "";
        this.person.phone = "";
      },
    },
  };
  </script>
  <style scoped>
  form {
    margin-bottom: 2rem;
  }
  </style> 