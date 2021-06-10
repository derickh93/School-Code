/*
=======================
01-n-files-asynchronously.js
=======================
Student ID:
Comment (Required):

=======================
*/
const fs = require("fs");
const n = 5;	//input size 0 < n < 100
var count = 0;

for(let i = 0; i < n;i++) {
	var fileName = `output/${i}-output.txt`;
	fs.writeFile(fileName, 'Data-1', function (err) {
	if (err) throw err;
	console.log(`${i}-output.txt has been written`);
	count++;
	if(count == 5) {
		console.log('Writing Complete');
	}
});
}

