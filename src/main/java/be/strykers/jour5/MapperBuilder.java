package be.strykers.jour5;

public class MapperBuilder {
    private Mapper mapper;
    private MapperManager mapperManager;

    public MapperBuilder(MapperManager mapperManager) {
        this.mapperManager = mapperManager;
        mapper = new Mapper();
    }

    public void reset() {
        mapper = new Mapper();
    }

    public MapperBuilder addMapEntity(MapComponent mapEntity) {
        mapper.addMapEntity(mapEntity);
        return this;
    }

    public MapperBuilder addMapEntity(long source, long destination, long range) {
        mapper.addMapEntity(new MapComponent(source, destination, range));
        return this;
    }

    public MapperBuilder addMapEntity(int source, int destination) {
        mapper.addMapEntity(new MapComponent(source, destination));
        return this;
    }

    public void build() {
        mapperManager.addMapper(mapper);
        reset();
    }

    public boolean isEmpty() {
        return mapper.isEmpty();
    }
}
