package be.strykers.jour5;

import org.javatuples.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MapperManager {
    Set<Long> seeds;
    Set<Pair<Long, Long>> seedsRanged;
    LinkedList<Mapper> mappers;

    public MapperManager(Set<Long> seeds, Set<Pair<Long, Long>> seedsRanged) {
        this.seedsRanged = seedsRanged;
        this.seeds = seeds;
        mappers = new LinkedList<>();
    }

    public void addMapper(Mapper mapper) {
        mappers.add(mapper);
    }

    public Long getLowestValue() {
        Set<Long> values = new HashSet<>(seeds);

        for (Mapper mapper : mappers) {
            values = mapper.convert(values);
        }

        return values.stream().min(Long::compareTo).get();
    }

    public long getLowestValueFromRange() {
        Set<Pair<Long, Long>> values = new HashSet<>(seedsRanged);
        int count = 0;

        for (Mapper mapper : mappers) {
            System.out.println("Mapper " + count++ + " : ");
            values = mapper.convertFromRange(values);
        }

        values.forEach(System.out::println);

        return values.stream()
                .mapToLong(Pair::getValue0)
                .min().getAsLong();
    }
}
