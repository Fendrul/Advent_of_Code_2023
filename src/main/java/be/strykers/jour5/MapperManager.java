package be.strykers.jour5;

import org.javatuples.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class MapperManager {
    private static final Logger LOGGER = Logger.getLogger(MapperManager.class.getName());

    Set<Pair<Long, Long>> seeds;
    LinkedList<Mapper> mappers;
    public int count;

    public MapperManager(Set<Pair<Long, Long>> seeds) {
        this.seeds = seeds;
        mappers = new LinkedList<>();
    }

    public void addMapper(Mapper mapper) {
        mappers.add(mapper);
    }

    public Long getLowestValue() {
        Set<Long> values = new HashSet<>();
        int count = 0;

        for (Pair<Long, Long> seed : seeds) {
            values.add(seed.getValue0());
            values.add(seed.getValue1());
        }

        for (Mapper mapper : mappers) {
            values = mapper.convert(values);
        }

        return values.stream().min(Long::compareTo).get();
    }

    public long getLowestValueFromRange() {
        Set<Pair<Long, Long>> values = new HashSet<>(seeds);
        count = 0;

        for (Mapper mapper : mappers) {
//            System.out.println("Itération du mapper :" + count);
            values = mapper.convertFromRange(values);
            count++;
        }

        Set<Long> flatValues = new HashSet<>();
        for (Pair<Long, Long> value : values) {
            flatValues.add(value.getValue0());
            flatValues.add(value.getValue1());
        }

        return flatValues.stream().min(Long::compareTo).get();
    }
}
