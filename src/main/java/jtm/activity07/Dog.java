package jtm.activity07;

public class Dog extends Mammal{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        System.out.println("setName: " + name);
        if (name.matches("^\\p{IsUppercase}\\p{IsLowercase}*$"))
            this.name = name;
        else
        this.name = "";
    }
}
