package be.strykers.jour5;

import org.javatuples.Pair;

public class PairWrapper<T, U> {
    Pair<T, U> pair;

    public PairWrapper(T first, U second) {
        pair = new Pair<>(first, second);
    }

    public PairWrapper(Pair<T, U> pair) {
        this.pair = pair;
    }

    public Pair<T, U> getPair() {
        return pair;
    }

    public void setPair(Pair<T, U> pair) {
        this.pair = pair;
    }


}
