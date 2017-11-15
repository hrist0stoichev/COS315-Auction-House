window.onload = function() {
    httpGetAsync("/category/", function callback(response) {
        var auctions = JSON.parse(response);
        var list = document.getElementById('auctions');
        list.innerHTML = '';

        auctions.forEach(function (auction) {
            var container = document.createElement("div");
            container.setAttribute("class", "auction");
            container.setAttribute("id", auction.id);

            var img = document.createElement("img");
            img.setAttribute("src", auction.image);
            img.setAttribute("alt", "Image");

            var content = document.createElement("div");
            content.innerHTML = `Name: ${auction.name}<br />
                                 Price: ${auction.price}<br />
                                 Start date: ${auction.startDate}<br />
                                 End date: ${auction.endDate}`;

            container.appendChild(img);
            container.appendChild(content);

            list.appendChild(container);
        });
    });
};

// var listText = document.createTextNode(auction.name);
// listItem.appendChild(listText);

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
