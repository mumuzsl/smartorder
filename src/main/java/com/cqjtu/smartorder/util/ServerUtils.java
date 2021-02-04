package com.cqjtu.smartorder.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

/**
 * @author mumu
 * @date 2020/8/1
 */
public class ServerUtils {

    public static final SocketAddress LOCALHOST = new InetSocketAddress("127.0.0.1", 61616);

    public static void main(String[] args) {
        try (AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()) {


        } catch (Exception e) {

        }
    }

}
