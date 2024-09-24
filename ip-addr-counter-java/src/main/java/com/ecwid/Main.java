package com.ecwid;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //TODO: make sure to  adjust the path
        String filePath = "/Users/adjioev/Documents/ip_addresses";
        IpAddrCounter ipAddrCounter = new IpAddrCounter();
        long total = ipAddrCounter.count(filePath);
        System.out.println("Total unique ips: " + total);
    }
}
