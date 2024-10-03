document.addEventListener('DOMContentLoaded', () => {
    const $setTitle = document.querySelector('#id-title');
    const $setContent = document.querySelector('#id-content');
    const $submit = document.querySelector('#id-submit');

    $submit.addEventListener('click', event => {
        event.preventDefault(); // 기본 폼 제출 동작을 막음

        const data = {
            title: $setTitle.value,
            content: $setContent.value,
        };
        
        fetch(`/board/movie`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                alert("성공");
                window.location.href = `../movie.html`; // 성공 후 카테고리 페이지로 이동
            })
            .catch(error => {
                console.error("Error:", error);
                alert("실패");
            });
    });
});