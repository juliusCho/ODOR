<template>
  <div class="gridBody">
    <table>
      <thead>
      <tr>
        <th v-for="col in columns" :key="col.label"
            :style="`width: ${col.width === '*' ? getAstaWidth : (col.width + 'px')};`">
          {{ col.label | capitalize }}
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(entry, idx) in filteredData" :key="idx">
        <td v-for="col in columns" :key="col.label">
          {{ entry[col.key] }}
        </td>
      </tr>
      </tbody>
    </table>
    <GridPaging

    ></GridPaging>
  </div>
</template>

<script>
import GridPaging from "@/views/components/GridPaging";

export default {
  name: "DataGrid",
  components: {
    GridPaging
  },
  props: {
    data: {
      type: Array,
      default: () => [],
      required: false
    },
    columns: {
      type: Array,
      default: () => [],
      required: true
    },
    filterKey: {
      type: String,
      default: "",
      required: false
    }
  },
  computed: {
    filteredData() {
      let filterKey = this.filterKey && this.filterKey.toLowerCase();
      let data = this.data;

      if (filterKey) {
        data = data.filter(row =>
            Object.keys(row).some(key =>
                String(row[key])
                    .toLowerCase()
                    .indexOf(filterKey) > -1
            )
        );
      }
      return data;
    }
  },
  filters: {
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    }
  },
  methods: {
    getAstaWidth() {
      let width = 0;
      for (let col in this.columns) {
        if (col.width !== '*') {
          width += Number(col.width);
        }
      }
      return 'calc(100% - ' + width + 'px)';
    }
  }
}
</script>

<style scoped>
.gridBody {
  font-family: Helvetica Neue, Arial, sans-serif;
  font-size: 14px;
  color: #444;
  width: calc(100% - 20px);
}

table {
  background-color: #fff;
  border-spacing: 0;
  width: 100%;
}

th {
  background-color: #4d7df5;
  color: #ffffff;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  font-weight: 400;
}

td {
  background-color: #f9f9f9;
}

th,
td {
  min-width: 20px;
  padding: 10px 20px;
}
</style>