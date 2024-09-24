package com.ecwid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class IpAddrCounterTest {

    private IpAddrCounter conv; // s = new IpAddrCounter();

    @BeforeEach
    void setUp() {
        conv = new IpAddrCounter();
    }

    @Test
    void shouldConvertIpToInt() throws IOException {
        IpAddrCounter conv = new IpAddrCounter();
        assertEquals(0, conv.convertIpStringToNumber("0.0.0.0"));
        assertEquals(1, conv.convertIpStringToNumber("0.0.0.1"));
        assertEquals( 4294967295L, conv.convertIpStringToNumber("255.255.255.255"));
        assertEquals(Integer.MAX_VALUE, conv.convertIpStringToNumber("127.255.255.255"));
        assertEquals(2147483648L, conv.convertIpStringToNumber("128.0.0.0"));
    }

    // Use direct file paths
    private String getFilePath(String filename) {
        // Adjust the path to point to the correct test data directory
        return Paths.get("src", "test", "resources", "test-data", filename).toAbsolutePath().toString();
    }

    @Test
    void testIpAddressList() throws IOException {
        long total = conv.count(getFilePath("ip_addresses_small"));
        assertEquals(150, total);
    }
    @Test
    void testDuplicates() throws IOException {
        long total = conv.count(getFilePath("duplicates"));
        assertEquals(1, total);
    }

    @Test
    void testEmptyFile() throws IOException {
        long total = conv.count(getFilePath("empty"));
        assertEquals(0, total);
    }

    @Test
    void testEdgeCaseIps() throws IOException {
        long total = conv.count(getFilePath("edge_case_ips"));
        assertEquals(4, total);
    }
}