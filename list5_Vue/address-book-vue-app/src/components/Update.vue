<template>
  <div id="update-person">
    <form @submit.prevent="handleSubmit">
      <label>Imię i nazwisko</label>
      <input
        v-model="person.name"
        type="text"
        :class="{ 'has-error': submitting && invalidName }"
        @focus="clearStatus"
        @keypress="clearStatus"
      />
      <label>Email</label>
      <input
        v-model="person.email"
        type="text"
        :class="{ 'has-error': submitting && invalidEmail }"
        @focus="clearStatus"
      />
      <label>Telefon</label>
      <input
        v-model="person.phone"
        type="text"
        :class="{ 'has-error': submitting && invalidPhone }"
        @focus="clearStatus"
        @keypress="clearStatus"
      />
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">
        Dane poprawnie zaktualizowano
      </p>
      <button>Edytuj kontakt</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "update-person",
  props: {
    personId: {
      type: Number,
      required: true,
    },
    personsSource: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      person: {
        id: null,
        name: "",
        email: "",
        phone: "",
      },
    };
  },
  computed: {
    personIndex() {
      return this.personsSource.findIndex(
        (person) => person.id === this.personId
      );
    },
    invalidName() {
      return this.person.name === "";
    },
    invalidEmail() {
      return !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.person.email);
    },
    invalidPhone() {
      return !/^\+\d{2}\s\d{3}\s\d{3}\s\d{3}$/.test(this.person.phone);
    },
  },
  watch: {
    personId: function () {
      const person = this.personsSource.find(
        (person) => person.id === this.personId
      );
      this.person = { ...person };
    },
  },
  methods: {
    handleSubmit() {
      this.submitting = true;
      this.clearStatus();
      //check form fields
      if (this.invalidName || this.invalidEmail || this.invalidPhone) {
        this.error = true;
        return;
      }
      const updatedPerson = { ...this.person };
      this.personsSource.splice(this.personIndex, 1, updatedPerson);
      //clear form fields
      this.person = {
        id: null,
        name: "",
        email: "",
        phone: "",
      };
      this.error = false;
      this.success = true;
      this.submitting = false;
    },

    clearStatus() {
      this.success = false;
      this.error = false;
    },
  },
};
</script>
  
  <style scoped>
  form {
    margin-bottom: 2rem;
  }
  </style>
  