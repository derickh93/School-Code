/*
=======================
02-n-files-synchronously.js
=======================
Student ID:
Comment (Required):

=======================
*/
const fs = require("fs");
const n = 5;	//input size 0 < n < 100
let output_dir = 'output/';


write(0);

function write(file_index) {
	let fileName = `${file_index.toString().padStart(2,'0')}-output.txt`;
	let path = `${output_dir}${fileName}`;
	fs.writeFile(path, 'Data-2', (err) => writeToFile(err, path, file_index));
}

function writeToFile(err, path, fileIndex) {
	if (err) {
		console.log(`Could not write file ${fileIndex}`);
		return;
	}
	if (fileIndex === n) {
		console.log('Writing Complete');
	}
	else {
		console.log(`file ${fileIndex} written`);
		write(fileIndex + 1);
	}
};