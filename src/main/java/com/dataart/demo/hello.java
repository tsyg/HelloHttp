// See  original code in http://stackoverflow.com/questions/3732109/simple-http-server-in-java-using-only-java-se-api#3732328

package com.dataart.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class hello {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.start();
    }

    static class MyHandler implements HttpHandler {
	static int cnt=0;
	
	public String getMessage() {
		return String.format("Hello, HTTP!  Count=%d\n",cnt);
	}

        @Override
        public void handle(HttpExchange t) throws IOException {
	    cnt++;	
	    // Debug
	    //System.out.println( String.format("Running hello! received requests: %d",cnt) );
		
            String response = getMessage();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
