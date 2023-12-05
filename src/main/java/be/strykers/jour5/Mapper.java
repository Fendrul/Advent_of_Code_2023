package be.strykers.jour5;

import org.javatuples.Pair;

import java.util.HashSet;
import java.util.Set;

public class Mapper {
    Set<MapComponent> mapEntities;

    public Mapper() {
        mapEntities = new HashSet<>();
    }

    public void addMapEntity(MapComponent mapEntity) {
        mapEntities.add(mapEntity);
    }

    public boolean isEmpty() {
        return mapEntities.isEmpty();
    }

    public Set<Long> convert(Set<Long> sourcesToConvert) {
        Set<Long> convertedSources = new HashSet<>();
        boolean conversionFound;

        for (Long toConvert : sourcesToConvert) {
            conversionFound = false;

            for (MapComponent mapEntity : mapEntities) {

                if (mapEntity.containsInRange(toConvert)) {
                    convertedSources.add(mapEntity.getConversion(toConvert));
                    conversionFound = true;
                }
            }

            if (!conversionFound) {
                convertedSources.add(toConvert);
            }
        }

        //return the lowest value
        return convertedSources;
    }

    public Set<Pair<Long, Long>> convertFromRange(Set<Pair<Long, Long>> valuesToConvert) {
        Set<Pair<Long, Long>> convertedValues = new HashSet<>();

        for (Pair<Long, Long> toConvert : valuesToConvert) {

            for (MapComponent mapEntity : mapEntities) {

                if (mapEntity.contains(toConvert)) {
                    PairWrapper<Long, Long> toConvertWrapped = new PairWrapper<>(toConvert);

                    Pair<Long, Long> conversionFromRange = mapEntity.getConversionFromRange(toConvertWrapped);
                    System.out.println("Converting " + toConvert + " to " + conversionFromRange + "\n");
                    toConvert = toConvertWrapped.getPair();
                    convertedValues.add(conversionFromRange);

                }
            }

            if (toConvert.getValue0() != 0L && toConvert.getValue1() != 0L) {
                System.out.println("Adding " + toConvert + "\n");
                convertedValues.add(toConvert);
            }
        }

        return convertedValues;
    }
}
