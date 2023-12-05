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

    public MapperBuilder addMapComponent(MapComponent mapEntity) {
        mapper.addMapEntity(mapEntity);
        return this;
    }

    public void addMapComponent(long source, long destination, long range) {
        mapper.addMapEntity(new MapComponent(source, destination, range));
    }

    public void build() {
        mapperManager.addMapper(mapper);
        reset();
    }

    public boolean isEmpty() {
        return mapper.isEmpty();
    }
}
