package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgsTest {

    @Test
    void should_set_to_true_if_flag_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");
        assertTrue(option.logging);
    }

    @Test
    void should_set_to_false_if_flag_not_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "");
        assertFalse(option.logging);
    }


    record BooleanOption(@Option("l") boolean logging) {

    }

    @Test
    void should_set_int() {
        IntOption parse = Args.parse(IntOption.class, "-p", "8080");
        assertEquals(8080, parse.port());
    }

    record IntOption(@Option("p") int port) {

    }


    // TODO string: -d "/usr/logs"
    @Test
    void should_set_string() {
        StringOption parse = Args.parse(StringOption.class, "-d","/usr/logs");
        assertEquals("/usr/logs",parse.directory);
    }

    record StringOption(@Option("d") String directory) {

    }

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

        Options option = Args.parse(Options.class,"-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(option.logging());
        assertEquals(8080, option.port());
        assertEquals("/usr/logs", option.dir);
    }


    @Test
    @Disabled
    void should_example_2() {
        ListOptions option = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");

        assertArrayEquals(new String[]{"this", "is", "a", "list"}, option.strings);
        assertArrayEquals(new int[]{1, 2, -3, 5}, option.integers);
    }

    static record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String dir) {

    }

    record ListOptions(@Option("g") String[] strings, @Option("d") int[] integers) {
    }
}