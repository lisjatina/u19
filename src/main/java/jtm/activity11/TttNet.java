package jtm.activity11;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import jtm.testsuite.AllTests;

/**
 * This class works as a wrapper for TttCli class and allows to send data
 * through TCP socket instead of standard input/output
 *
 */
public class TttNet {
	static final int port = AllTests.port; // port to listen on
	static final String host = "localhost"; // host to connect to

	public static void main(String[] args) {
		int size = 0;
		if (args != null && args.length == 1)
			size = Integer.parseInt(args[0]);

		ServerSocket server; // server socket, which listens to new connections
		Socket socket; // client socket, which connects to the server
		TttCli tttCli; // Standard CLI as an internal part of TttNet object

		try {
			InputStream input;
			OutputStream output;
			server = new ServerSocket(port);
			socket = server.accept();
			input = socket.getInputStream();
			output =socket.getOutputStream();
			tttCli = new TttCli(input, output, size);
			tttCli.play();

		} catch (Exception e) {
			// catching BindException is OK, if second instance is executed, just continue
			// then, catching other exceptions may not be OK
		}
		// 2. If could not run as a server, try to run as a client
		try {
			BufferedReader stdin, srvin;
			PrintWriter srvout, stdout;

			socket = new Socket(host, port);
			srvin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stdout = new PrintWriter(System.out);
			stdin = new BufferedReader(new InputStreamReader(System.in));
			srvout = new PrintWriter(socket.getOutputStream());
			/*  While game is not ended:
			 *      1. read lines from srvin
			 *      2. print them to stdout
			 *      3. read line from stdin
			 *      4. print it to srvout
			 */
			String serverContent;
			boolean stop = false;
			while (!stop){
				do {
					serverContent = srvin.readLine();
					stdout.println(serverContent);
					stdout.flush();
					if ("Game ended!".equals(serverContent)){
						stop = true;
						break;
					}
				}while (!"Enter place:".equals(serverContent));
					if (stop)
						break;
					serverContent = stdin.readLine();
					srvout.println(serverContent);
					srvout.flush();
			}
			 /*  Hints:
			 *  1. To be sure that all needed lines are read from server, check for signatures, e.g.:
			 *      "Game ended!"
			 *      "Enter place:"
			 *  2. Don't forget to flush buffers!
			 *  3. Don't forget to close socket!
			 */
			socket.close();
			stdout.close();
			stdin.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
