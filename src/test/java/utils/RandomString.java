package utils;

import java.util.Random;

public class RandomString {
    public static Random rand = new Random();

    public static String randomString = rand.ints(48, 123)

            .filter(num -> (num<58 || num>64) && (num<91 || num>96))

            .limit(15)

            .mapToObj(c -> (char)c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)

            .toString();
}
