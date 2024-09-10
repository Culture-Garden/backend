const app = Vue.createApp({
    data() {
        return {
            title: '',
            content: ''
        };
    },
    mounted() {
         fetch('board/movie/1')
            .then(response => response.json())
            .then(data => {
                 this.title = data.title;
                 this.content = data.content;
             });
    }
});

app.mount('#app');
