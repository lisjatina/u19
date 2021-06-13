package jtm.activity05;

import jtm.activity04.Road;

public class Amphibia extends Vehicle {
    private byte sails;

    public Amphibia(String id, float consumption, int tankSize, byte sails, int wheels) {
        super(id, consumption, tankSize, wheels);
        this.sails = sails;
    }

    @Override
    public String move(Road road) {
        if (road instanceof WaterRoad){
            return super.getType()+" is sailing on " + road + " with " + sails
                    + " sails";
        } else {
            return super.move(road).replace("Vehicle","Amphibia");
        }
    }
}
