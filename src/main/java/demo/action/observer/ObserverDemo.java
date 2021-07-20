package demo.action.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86134
 */
public class ObserverDemo {

    public static void main(String[] args) {
        Newspaper newspaper = new Newspaper("新华日报");
        Readers readers = new Readers("张三", newspaper);
        Readers readers2 = new Readers("李四", newspaper);
        newspaper.addObserver(readers);
        newspaper.addObserver(readers2);
        newspaper.notifyObservers();
        newspaper.removeObserver(readers);
        newspaper.notifyObservers();
    }
}

//观察者类
abstract class Company{

    private List<Person> peoples = new ArrayList<>();

    private String name;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addObserver(Person observer){
        peoples.add(observer);
    }

    public void removeObserver(Person observer){
        peoples.remove(observer);
    }

    public void notifyObservers(){
        for (Person person : peoples) {
            person.update();
        }
    }
}

//被观察者类
abstract class Person{
    private String name;
    private Company company;

    public Person(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public abstract void update();
}

/**
 * 具体的消息通知者（报社）
 *
 * */
class Newspaper extends Company{

    public Newspaper(String name){
        super(name);
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

/**
 * 具体的消息接收者（读者）
 *
 * */
class Readers extends Person{

    public Readers(String name, Company company) {
        super(name, company);
    }

    @Override
    public void update() {
        System.out.println(super.getName() +
                "...." +
                super.getCompany().getName() +
                "....通知....");
    }
}