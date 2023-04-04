<template>
  <div id="persons-table">
    <table>
      <!-- ...thead... -->
      <thead>
         <tr>
          <th>Imię i nazwisko</th>
          <th>email</th>
          <th>telefon</th>
          <th>Akcje</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="person in personsSource" :key="person.id">
          <td>{{ person.name }}</td>
          <td>{{ person.email }}</td>
          <td>{{ person.phone }}</td>
          <td><button @click="removePerson(person)">Usuń</button></td>
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
    removePerson(person) {
      const index = this.persons.indexOf(person);
      if (index !== -1) {
        this.persons.splice(index, 1);
      }
    },
  },
};
</script>
<style scoped></style>

