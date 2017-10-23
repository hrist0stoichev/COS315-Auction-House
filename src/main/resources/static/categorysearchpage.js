window.onload = function() {
    httpGetAsync("/category/", function callback(response) {
        var auctions = JSON.parse(response);
        var list = document.getElementById('auctions');
        list.innerHTML = '';
        auctions.forEach(function (auction) {
            var listItem = document.createElement("LI");
            var listText = document.createTextNode(auction.name);
            listItem.appendChild(listText);
            list.appendChild(listItem);
        });
    });
};

function searchCategory() {
    var categoryInput = document.getElementById('categoryInput').value;
    httpGetAsync("/category/" + categoryInput, function callback(response) {
        var auctions = JSON.parse(response);
        var list = document.getElementById('auctions');
        list.innerHTML = '';
        auctions.forEach(function (auction) {
            var listItem = document.createElement("LI");
            var listText = document.createTextNode(auction.name);
            listItem.appendChild(listText);
            list.appendChild(listItem);
        });
    });
}

function httpGetAsync(theUrl, callback) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200)
            callback(xmlHttp.responseText);
    };
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}
