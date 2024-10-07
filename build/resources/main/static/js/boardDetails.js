const app = Vue.createApp({
    data() {
        return {
            items: []
        };
    },
    mounted() {
         fetch('board/movie/1')
            .then(response => response.json())
            .then(data => {
                 this.items = data;
             });
    }
});

app.mount('#app');
