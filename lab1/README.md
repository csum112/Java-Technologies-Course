## Tehnologii Java - Lab 1

- [x] receives the following parameters key:String, value:int, mock:boolean and sync:boolean.
- [x] if mock is true than the servlet simply returns a confirmation message.
- [x] if mock is false, the servlet writes in a text file called repository a line containing the key, repeated value times, along with the timestamp of the request, and returns the content of the repository, as an HTML page containing all the lines that were created, ordered by key.
- [x] if sync is false, then the servlet will not use any synchronized method when writing in the file.
- [x] Write in the server log the following information about each request: the HTTP method used, the IP-address of the client, the user-agent, the client language(s) and the parameters of the request.
- [x] Invoke the service from a desktop application (Java, Python, .NET, etc.). In this case, the servlet must respond with a simple text, instead of an HTML page. 
- [ ] Analyze the performance and concurrency issues, invoking the servlet repeatedly, in an asynchronous manner (use different values for the mock and sync parameters).
