<template>
  <div id="app" class="small-container">
    <h1>Znajomi</h1>

    <person-form @add-person="addPerson" />
    <persons-table :personsSource="persons" @remove-person="removePerson" />
  </div>
</template>
  
  <script>
import PersonsTable from "@/components/PersonsTable.vue";
import PersonForm from "@/components/PersonForm.vue";
export default {
  name: "app",
  components: {
    PersonsTable,
    PersonForm,
  },
  data() {
    return {
      //static data
      persons: [
        {
          id: 1,
          name: "Adam Słodowy",
          email: "adam.slodowy@zrobtosam.pl",
          phone: "+48 787 774 664",
        },
        {
          id: 2,
          name: "Michał Studencki",
          email: "ms@student.pwr.edu.pl",
          phone: "+48 600 565 454",
        },
        {
          id: 3,
          name: "Kamila Napokaz",
          email: "kami2003@h2.pl",
          phone: "+48 609 554 987",
        },
      ],
      person: {
        id: null,
        name: "",
        email: "",
        phone: "",
      },
    };
  },
  //async get data from .json
  // mounted() {
  //   this.getPersons();
  // },

  methods: {
    addPerson(newPerson) {
      console.log("uruchomiono addPerson");

      const maxId = Math.max(...this.persons.map((person) => person.id));
      newPerson.id = maxId + 1;
      this.persons.push(newPerson);
      this.person.name = "";
      this.person.email = "";
      this.person.phone = "";
    },
    removePerson(id) {
      const index = this.persons.findIndex((person) => person.id === id);
      if (index !== -1) {
        this.persons.splice(index, 1);
      }
    },

    async getPersons() {
      try {
        const response = await fetch(
          "https://jsonplaceholder.typicode.com/users"
        );
        const data = await response.json();
        this.persons = data;
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
  
  <style>
button {
  background: #009435;
  border: 1px solid #009435;
}
.small-container {
  max-width: 680px;
}
</style>
  