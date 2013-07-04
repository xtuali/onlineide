package org.webteam.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		
		serverChannel.configureBlocking(false);
		
		
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println(ia.getAddress());
		InetSocketAddress isa = new InetSocketAddress(ia,80);
		
		
		
		serverChannel.socket().bind(isa);
	}
}
