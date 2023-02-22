<template>
    <div class="q-mt-md" style="text-align: center; ">Completed destinations by day</div>
    <q-page class="row items-center justify-evenly">
        <div>
            <apexchart width="500" type="bar" :options="options" :series="series"></apexchart>
        </div>
    </q-page>
</template>
  
<script setup lang="ts">
import { api } from 'src/boot/axios';
import { ref } from 'vue';

var catgs = ref([]);
var graphData = ref([])

api.get("destinations").then((response: any) => {
    response.data.forEach((element: any) => {
        element["checked"] = false
    });

    response.data.forEach((element: any) => {
        if (element["checked"] === false) {
            (catgs.value as string[]).push(element["arriveDate"])
            var savedArriveDate = element["arriveDate"]
            var count = 0
            response.data.forEach((elNested: any) => {
                if (savedArriveDate == elNested["arriveDate"]) {
                    elNested["checked"] = true
                    count += 1
                }
            });
            (graphData.value as string[]).push(count.toString())
        }
    });
})

var options = {
    chart: {
        id: 'vuechart-example'
    },
    xaxis: {
        categories: catgs.value
    }
}

var series = [{
    name: 'Number or completed destinations',
    data: graphData.value
}]


</script>