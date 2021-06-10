package jtm.activity06;

public class Martian implements Humanoid,Alien,Cloneable {
    Object stomach;

    public Martian() {
    }

    @Override
    public void eat(Object item) {
        if (stomach == null) {
            if (item instanceof Human) {
                Human humanToEat = (Human) item;
                if (humanToEat.alive) {
                    humanToEat.killHimself();
                    stomach = humanToEat;
                }
            }
                else
                    stomach = item;
                }
            }

    @Override
    public void eat(Integer food) {
        eat((Object) food);

    }

    @Override
    public Object vomit() {
        Object foodToVomit = stomach;
        stomach = null;
        return foodToVomit;
    }

    @Override
    public String isAlive() {
        return "I AM IMMORTAL!";
    }

    @Override
    public String killHimself() {
        return isAlive();
    }

    @Override
    public int getWeight() {
        int stomachWeight = 0;
        if (stomach instanceof Integer) // Integer is eaten
            stomachWeight = (Integer) stomach;
        else if (stomach instanceof Humanoid) // Humans and aliens are eaten (implements Humanoids)
            stomachWeight = ((Humanoid) stomach).getWeight();
        return Alien.BirthWeight + stomachWeight;
    }

    @Override
    public String toString() {
        return "Martian: " + getWeight() +
                " [" + stomach + "]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //return super.clone(); - test passing with this line only
        return clone(this);
    }

    private Object clone(Object current) {
        if (current == null)
            return null;
        if (current instanceof Integer) {
            return Integer.valueOf((Integer) current);
        }
        if (current instanceof Human) {
            Human tmp = (Human) current;
            Human clone = new Human();
            clone.eat((Integer) clone(tmp.stomach)); // recursion for human stomach
        return clone;}
        if (current instanceof Martian) {
            Martian tmp = (Martian) current;
            Martian clone = new Martian();
            clone.eat(clone(tmp.stomach));
            return clone;// recursion for martian stomach
        }
        return null;
    }
}

