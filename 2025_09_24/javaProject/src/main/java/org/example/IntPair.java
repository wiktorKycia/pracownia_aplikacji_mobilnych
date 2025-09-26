package org.example;

public class IntPair {
    private final int a;
    private final int b;
    private final int hash;

    public IntPair(int a, int b) {
        this.a = a;
        this.b = b;
        this.hash = 31 * a + b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPair other = (IntPair) o;
        return this.a == other.a && this.b == other.b;
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
