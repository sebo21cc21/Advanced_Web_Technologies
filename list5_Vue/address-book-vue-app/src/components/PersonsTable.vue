<template>
  <div id="persons-table">
    <table>
      <!-- ...thead... -->
      <thead>
        <tr>
          <th>Imię i nazwisko</th>
          <th>email</th>
          <th>telefon</th>
          <th>Usuwanie</th>
          <th>Edytowanie</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="person in personsSource" :key="person.id">
          <td>{{ person.name }}</td>
          <td>{{ person.email }}</td>
          <td>{{ person.phone }}</td>
          <td><button @click="removePerson(person.id)">Usuń</button></td>
          <td><button @click="$emit('edit-person', person)">Edytuj</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
export default {
  name: "persons-table",
  props: {
    personsSource: Array,
  },
  data() {
    return {
      persons: [...this.personsSource],
    };
  },
  methods: {
    removePerson(id) {
      const index = this.persons.findIndex((person) => person.id === id);
      if (index !== -1) {
        const newPersons = [...this.persons];
        newPersons.splice(index, 1);
        this.persons = newPersons;
        this.$emit("remove-person", id); // emit the event to the parent component
      }
    },
  },
  
};
</script>
<style scoped></style>

