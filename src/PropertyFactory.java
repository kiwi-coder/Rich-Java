public class PropertyFactory {

    static public Property duplicateLandFromProperty(Property property){
        Property land = new Land();
        land.setIndex(property.index);
        land.setMap(property.map);
        land.setPlayer(property.player);
        land.setPrice(property.price);

        property.map.setSite(property.index, land);
        return land;
    }

    static public Property duplicateCabinFromProperty(Property property){
        Property cabin = new Cabin();
        cabin.setIndex(property.index);
        cabin.setMap(property.map);
        cabin.setPlayer(property.player);
        cabin.setPrice(property.price);

        property.map.setSite(property.index, cabin);
        return cabin;
    }

    static public Property duplicateHouseFromProperty(Property property){
        Property house = new House();
        house.setIndex(property.index);
        house.setMap(property.map);
        house.setPlayer(property.player);
        house.setPrice(property.price);

        property.map.setSite(property.index, house);
        return house;
    }

    static public Property duplicateSkyscraperFromProperty(Property property){
        Property skyscraper = new Skyscraper();
        skyscraper.setIndex(property.index);
        skyscraper.setMap(property.map);
        skyscraper.setPlayer(property.player);
        skyscraper.setPrice(property.price);

        property.map.setSite(property.index, skyscraper);
        return skyscraper;
    }
}
