const fs = require("fs");
const url = require("url");
const http = require("http");
const querystring = require("querystring");
const port = 3000;
const server = http.createServer();
server.on("request", request_handler);
function request_handler(req, res){
    console.log(`New Request from ${req.socket.remoteAddress} for ${req.url}`);
    if(req.url === "/"){
        res.writeHead(200, {"Content-Type": "text/html"});
        const html_stream = fs.createReadStream("html/form.html");
		html_stream.pipe(res);
    }
    else if (req.url.startsWith("/enter")){
        res.writeHead(200, {"Content-Type": "text/html"});
		let user_input_body = "";
		req.on('data', function (chunk) {
			user_input_body += chunk;
		});
		req.on('end', function(){
			let user_input = querystring.parse(user_input_body);
			console.log(user_input);
			if(user_input.password === "ABRACADABRA"){
				res.writeHead(200, {"Content-Type": "text/html"});
				res.end("<h1>Welcome</h1>");
			}
			else{
				res.writeHead(403, "Forbidden", {"Content-Type": "text/html"});
				res.end(`<h1>403 Forbidden</h1>`);
			}
		});
    }
    else{
        res.writeHead(404, "Not Found", {"Content-Type": "text/html"});
        res.end(`<h1>404 Not Found</h1>`);
    }
}
server.on("listening", listen_handler)
function listen_handler(){
    console.log(`Now Listening on Port ${port}`)    
}
server.listen(port);