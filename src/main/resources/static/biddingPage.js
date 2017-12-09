function makeBid(index, auctionId) {
    var bidAmount = document.getElementById('bidamount'+index).value;
    var currentBid = document.getElementById('currentbid'+index).innerHTML;

    console.log("bid amount: " + bidAmount);
    console.log("current bid: " + currentBid);

    if(isNaN(bidAmount)) {
        alert("Bid amount is not a number. Please input a number higher than the current bid.");
        return;
    }
    if(isNaN(currentBid)) {
        console.log("Error: Price of auction is not a number");
        return;
    }

    bidAmount = parseFloat(bidAmount);
    currentBid = parseFloat(currentBid);


    if(bidAmount < currentBid) {
        alert("Bid amount is lower than the current bid. Please input a number higher than the current bid.");
        console.log("in if, is bid less than current?");
        console.log(bidAmount < currentBid);
        return;
    }
    console.log("out if, is bid less than current?");
    console.log(bidAmount < currentBid);

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            location.reload();
        }
    };
    xmlHttp.open("POST", "/bid/" + auctionId + "/" + bidAmount, true); // true for asynchronous
    xmlHttp.send(null);
}
