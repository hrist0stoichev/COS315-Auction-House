window.onload = function() {
    httpGetAsync("/categories/", function callback(response) {
        var categories = JSON.parse(response);
        var list = document.getElementById('categories');
        list.innerHTML = '';
        categories.forEach(function (category) {
            var listItem = document.createElement("LI");
            var listText = document.createTextNode(category.name);
            listItem.appendChild(listText);
            list.appendChild(listItem);
        });
    });
};

function httpGetAsync(theUrl, callback) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            callback(xmlHttp.responseText);
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}
