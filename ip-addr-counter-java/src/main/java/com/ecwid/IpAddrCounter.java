package com.ecwid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.BitSet;

public class IpAddrCounter {

    /**
     * Converts IP address string to long unsigned value
     *
     * @param ipAddress
     * @return
     * @throws UnknownHostException
     */
    public long convertIpStringToNumber(String ipAddress) throws UnknownHostException {
        InetAddress inet = InetAddress.getByName(ipAddress);
        byte[] bytes = inet.getAddress();
        return ((bytes[0] & 0xFFL) << 24) | ((bytes[1] & 0xFFL) << 16)
                | ((bytes[2] & 0xFFL) << 8) | (bytes[3] & 0xFFL);
    }

    /**
     * Counts number of unique ips in the file
     *
     * @param filepath
     * @return
     * @throws IOException
     */
    public long count(String filepath) throws IOException {
        BufferedReader reader =
                new BufferedReader(new FileReader(filepath));

        String ipString;
        BitSet lowBitSet = new BitSet(Integer.MAX_VALUE);
        BitSet highBitSet = new BitSet(Integer.MAX_VALUE);

        while ((ipString = reader.readLine()) != null) {
            long ip = convertIpStringToNumber(ipString);
            if (ip <= Integer.MAX_VALUE) {
                lowBitSet.set((int) ip);
            } else {
                int shiftedIp = (int) (ip - (Integer.MAX_VALUE + 1L));
                highBitSet.set(shiftedIp);
            }
        }

        return lowBitSet.cardinality() + highBitSet.cardinality();
    }
}
