document.addEventListener('DOMContentLoaded', () => {
    const $setTitle = document.querySelector('#id-title');
    const $setContent = document.querySelector('#id-content');
    const $setCategory = document.querySelector('#id-category');
    const $setImgSrc = document.querySelector('#id-img-src')
    const $submit = document.querySelector('#id-submit');

    $submit.addEventListener('click', event => {
        event.preventDefault(); // 기본 폼 제출 동작을 막음

        const data = {
            title: $setTitle.value,
            content: $setContent.value,
            imgSrc: $setImgSrc.value
        };

        // 선택된 카테고리 값에 맞춰 요청 경로 동적으로 설정
        const category = $setCategory.value;
        const apiUrl = `/board/${category}`;

        fetch(apiUrl, {
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
                window.location.href = `../${category}.html`; // 성공 후 카테고리 페이지로 이동
            })
            .catch(error => {
                console.error("Error:", error);
                alert("실패");
            });
    });
});
