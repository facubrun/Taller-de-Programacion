document.addEventListener('DOMContentLoaded', function () {
    fetch('${pageContext.request.contextPath}/html/header.html').then(response => {
        if (!response.ok) {
            throw new Error('Error');
        } return response.text();
    }).then(data => {
        document.getElementById('header').innerHTML = data;
    }).catch(error => {
        console.error('Error: ', error);
    });
});