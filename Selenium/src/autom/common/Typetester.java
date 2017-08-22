package autom.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Typetester {

    void printType(byte x) {
        System.out.println(x + " is a byte");
    }
    void printType(int x) {
        System.out.println(x + " is an int");
    }
    void printType(float x) {
        System.out.println(x + " is a float");
    }
    void printType(double x) {
        System.out.println(x + " is a double");
    }
    void printType(short x) {
        System.out.println(x + " is a short");
    }
    void printType(char x) {
        System.out.println(x + " is a char");
    }
    void printType(String x) {
        System.out.println(x + " is a String");
    }
    <E> void printType(Set<E> x) {
        System.out.println(x + " is a Set");
    }
    <E> void printType(List<E> x) {
        System.out.println(x + " is a Set");
    }
    <K, V> void printType(Map<K,V> x) {
        System.out.println(x + " is a Map");
    }
    <K, V> void printType(HashMap<K,V> x) {
        System.out.println(x + " is a HashMap");
    }
}
