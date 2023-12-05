package be.strykers.jour5;

import org.javatuples.Pair;

import java.util.HashSet;
import java.util.Set;

public class MapComponent {
    private final long source;
    private final long destination;
    private final long range;

    public MapComponent(long source, long destination, long range) {
        this.source = source;
        this.destination = destination;
        this.range = range;
    }

    public MapComponent(int source, int destination) {
        this.source = source;
        this.destination = destination;
        this.range = 0;
    }

    public Long getConversion(Long convert) {
        if (!(destination <= convert && convert <= destination + range))
            throw new IllegalArgumentException("Conversion not possible");

        return source + (convert - destination);
    }

    public Pair<Long, Long> getConversionFromRange(PairWrapper<Long, Long> toConvertWrapped) {
        Pair <Long, Long> toConvert = toConvertWrapped.getPair();

//        System.out.println("source " + source + ", destination " + destination + ", range " + range + " & toConvert : " + toConvert);

        if (!contains(toConvert))
            throw new IllegalArgumentException("Conversion not possible");

        if (toConvert.getValue0() <= destination) {
            toConvertWrapped.setPair(
                    toConvert.setAt0(destination)
            );
            return new Pair<>(source, getConversion(toConvert.getValue1()));

        } else if (toConvert.getValue1() >= destination + range) {
            toConvertWrapped.setPair(
                    toConvert.setAt1(destination + range)
            );
            return new Pair<>(getConversion(toConvert.getValue0()), source + range);

        } else {
            toConvertWrapped.setPair(
                    new Pair<>(0L, 0L)
            );
            Pair<Long, Long> rangeToReturn = new Pair<>(getConversion(toConvert.getValue0()), getConversion(toConvert.getValue1()));
            return rangeToReturn;
        }
    }

    public boolean containsInRange(Long convert) {
        return destination <= convert && convert <= destination + range;
    }

    public boolean contains(Pair<Long, Long> toConvert) {
//        System.out.println("vérification de la convertion : " + toConvert + " avec les valeurs : " + this.destination + " et " + this.range);
        return containsInRange(toConvert.getValue0()) || containsInRange(toConvert.getValue1());
    }
}
