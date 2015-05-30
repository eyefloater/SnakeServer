function fireAlert() {
	alert("hell yes this alert sure has fired!");
}

function doPoll() {
	$.post('../../AdminPanel.jsp').done(function(data) {
		/* get most recent data from db */ 
		console.log("polling..");
		alert("lala, getting db data");
		}).always(function() {
			setTimeout(doPoll, 5000); 
		});
	}
var data = "testdata";

function testPost() {
	$.post('AdminPanel.jsp', $("#test")).done(function(data) {
		alert("here's your data: " + data);
	});
}