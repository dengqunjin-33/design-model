package demo.action.visitor;

import java.util.LinkedList;
import java.util.List;

abstract class Action{

    public abstract void getManResult(Man man);

    public abstract void getWomanResult(Woman woman);
}

class Success extends Action{

    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价该歌手很成功");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价该歌手很成功");
    }
}

class Fail extends Action{

    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价该歌手很失败");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价该歌手很失败");
    }
}

class Wait extends Action{

    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价该歌手待定");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价该歌手待定");
    }
}

abstract class Person{
    /**操作*/
    abstract void accept(Action action);
}

class Man extends Person{
    @Override
    void accept(Action action) {
        action.getManResult(this);
    }
}

class Woman extends Person{
    @Override
    void accept(Action action) {
        action.getWomanResult(this);
    }
}

class ObjectStructure {

    private final List<Person> persons = new LinkedList<>();

    public void attach(Person p) {
        persons.add(p);
    }

    public void detach(Person p) {
        persons.remove(p);
    }

    public void display(Action action){
        for(Person person:persons) {
            person.accept(action);
        }
    }
}

/**
 * @author 86134
 */
public class VisitorClient {
    public static void main(String[] args) {
        ObjectStructure structure = new ObjectStructure();
        Man man = new Man();
        Woman woman = new Woman();
        structure.attach(man);
        structure.attach(woman);

        //成功
        structure.display(new Success());
        System.out.println("-------------------");
        structure.detach(woman);
        //失败
        structure.display(new Fail());
        System.out.println("-------------------");
        //待定
        structure.display(new Wait());
    }
}
