// See  original code in http://stackoverflow.com/questions/3732109/simple-http-server-in-java-using-only-java-se-api#3732328

package com.dataart.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class hello {

    public static void main(String[] args) throws Exception {
        int port=8000;
        System.out.println( String.format("Starting hello >> received requests: %d",args.length) );
        if( args.length > 0 ) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Port argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        System.out.println(String.format("Now  starting HttpServer on port %d ", port));

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new MyHandler());
            server.start();
        } catch (SocketException e) { //
            // e.g. Exception in thread "main" java.net.SocketException: Permission denied
            System.out.println(String.format("Cannot start on port %d  (is it already in use?)", port));
            System.exit(1);
        }
    }

    static class MyHandler implements HttpHandler {
	static int cnt=0;
	
	public String getMessage() {
		return String.format("Hello, HTTP! Count=%d\n",cnt);
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
