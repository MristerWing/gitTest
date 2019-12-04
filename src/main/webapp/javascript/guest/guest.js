function deleteCheck(root, num, currentPage) {
    var url = root + "/guest/delete.do?num=" + num + "&pageNumber=" + currentPage;
    //console.log(url);

    var value = confirm("정말로 삭제하시겠습니까?");

    if(value) {
        location.href = url;
    }
}

function updateCheck(root, num, currentPage) {
    var url = root + "/guest/update.do?num=" + num + "&pageNumber=" + currentPage;
    //console.log(url);

    location.href = url;
}