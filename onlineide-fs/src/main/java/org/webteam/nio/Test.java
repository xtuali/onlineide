package org.webteam.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Test {
	public static void main(String[] args) throws IOException{
		String host = "";
		InetSocketAddress socketAddress = new InetSocketAddress(host,80);
		
		SocketChannel channel = SocketChannel.open();
		
		channel.connect(socketAddress);
		
		channel.configureBlocking(false);
		Selector selector = Selector.open();
		SelectionKey acceptKey = channel.register(selector, SelectionKey.OP_ACCEPT);
		selector.select();
	}
}
