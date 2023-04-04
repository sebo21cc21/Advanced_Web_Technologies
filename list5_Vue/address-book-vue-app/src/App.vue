<template>
    <div id="app" class="small-container">
      <h1>Znajomi</h1>
  
      <person-form @add-person="addPerson" />
      <persons-table :personsSource="persons" />
      
    </div>
  </template>
  
  <script>
  import PersonsTable from "@/components/PersonsTable.vue";
  import PersonForm from "@/components/PersonForm.vue";
  export default {
    name: "app",
    components: {
      PersonsTable,
      PersonForm
    },
    data() {
      return {
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
          name: '',
          email: '',
          phone: ''
        }
      };
    },
    methods: {
      addPerson() {
        this.person.id = this.persons.length + 1;
        this.persons.push({...this.person});
        this.person.name = '';
        this.person.email = '';
        this.person.phone = '';
      }
    }
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
  