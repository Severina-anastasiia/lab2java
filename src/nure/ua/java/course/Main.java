package nure.ua.java.course;

import nure.ua.java.course.presistence.entity.Computer;
import nure.ua.java.course.presistence.entity.Type;
import nure.ua.java.course.presistence.repository.ComputerRepository;
import nure.ua.java.course.presistence.repository.TypeRepository;
import nure.ua.java.course.service.ComputerService;
import nure.ua.java.course.service.TypeService;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        ComputerRepository computerRepository = new ComputerRepository();
        ComputerService computerService = new ComputerService(computerRepository);

        TypeRepository typeRepository = new TypeRepository();
        TypeService typeService = new TypeService(typeRepository);

        Type type1 = new Type();
        type1.setId(1);
        type1.setName("CPU");

        Type type2 = new Type();
        type2.setId(2);
        type2.setName("RAM");

        Type type3 = new Type();
        type3.setId(3);
        type3.setName("CD");

        typeService.create(type1);
        typeService.create(type2);
        typeService.create(type3);

        Computer computer1 = new Computer();
        computer1.setId(1);
        computer1.setName("HP");
        computer1.setId_type(type1.getId());

        Computer computer2 = new Computer();
        computer2.setId(2);
        computer2.setName("Lenovo");
        computer2.setId_type(type3.getId());

        computerService.create(computer1);
        computerService.create(computer2);

        List<Computer> computers = computerService.get("CPU");

        System.out.println("The list of computers which have CPU(component type):");
        for (Computer c : computers) {
            System.out.println(c);
        }

        System.out.println();

        computer1.setName("LG");
        computerService.update(computer1);

        computers = computerService.get("CPU");

        System.out.println("The list of computers which have CPU(component type):");
        for (Computer c : computers) {
            System.out.println(c);
        }

        type3.setName("HDD");
        typeService.update(type3);

        System.out.println();

        List<Type> types = typeService.get("Lenovo");

        System.out.println("The list of component types in Lenovo:");
        for (Type t : types) {
            System.out.println(t);
        }
    }
}
