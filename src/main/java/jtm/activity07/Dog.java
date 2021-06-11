package jtm.activity07;

public class Dog extends Mammal{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        Character.toUpperCase(name.charAt(0));

        this.name = name;
    }
}
