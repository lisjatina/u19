package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Amphibia extends Transport {
    private Ship ship;
    private Vehicle vehicle;


    public Amphibia(String id, float consumption, int tankSize, byte sails, int wheels) {
        super(id, consumption, tankSize);
        ship = new Ship (id, sails);
        vehicle = new Vehicle(id, consumption,tankSize,wheels);
    }

    @Override
    public String move(Road road) {
        if (road instanceof WaterRoad){
            return ship.move(road);
        } else {
            return vehicle.move(road).replace("Vehicle","Amphibia");
        }
    }
}
