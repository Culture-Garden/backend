// Vue 3 애플리케이션 인스턴스 생성 및 마운트
const app = Vue.createApp({
    data() {
        return {
            items: []
        };
    },
    mounted() {
        // 서버에서 데이터를 가져오는 함수
        fetch('/board/movie')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // JSON 데이터로 변환
            })
            .then(data => {
                this.items = data; // Vue 데이터에 할당
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
    }
});

app.mount('#app');



