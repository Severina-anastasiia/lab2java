package nure.ua.java.course.presistence.entity;

public class Computer extends AbstractEntity{

    private int id_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", id_type=" + id_type +
                '}';
    }
}
