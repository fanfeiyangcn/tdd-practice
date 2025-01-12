package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgsTest {

    // HAPPY
    // TODO bool-l

    // TODO int -p 8080

    // TODO string: -d "/usr/logs"

    // SAD PATH:
    // TODO -l t/ -l t f
    // TODO -p / -p 8080 8081
    // TODO -d / -d "/usr/logs" "/usr/admin"

    //  default value:
    // TODO bool: false
    // TODO int:0
    // TODO string: ""


    @Test
    @Disabled
    void should_example_1() {

        SingleOption option = Args.parse(SingleOption.class,"-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(option.logging());
        assertEquals(8080, option.port());
        assertEquals("/usr/logs", option.dir);
    }

    static record SingleOption(@Option("l") boolean logging, @Option("p") int port, @Option("d") String dir) {

    }


    @Test
    @Disabled
    void should_example_2() {
        MultiOption option = Args.parse(MultiOption.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");

        assertArrayEquals(new String[]{"this", "is", "a", "list"}, option.strings);
        assertArrayEquals(new int[]{1, 2, -3, 5}, option.integers);
    }

    record MultiOption(@Option("g") String[] strings, @Option("d") int[] integers) {

    }
  
}