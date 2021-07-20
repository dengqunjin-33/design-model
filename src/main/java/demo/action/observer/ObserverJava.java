package demo.action.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author 86134
 * 使用java自带的等待唤醒
 */
public class ObserverJava {
    public static void main(String[] args) {
        MyNewspaper newspaper = new MyNewspaper();
        Reader zhangSan = new Reader(newspaper,"张三");
        Reader liSi = new Reader(newspaper,"李四");

        newspaper.setName("新华日报");
        newspaper.notifyObservers();
        newspaper.deleteObserver(zhangSan);
        System.out.println("-----------");
        newspaper.setChanged();
        newspaper.notifyObservers();
    }
}

class Reader implements Observer{
    private String name;

    public Reader(Observable o,String name){
        this.name = name;
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("读者名字是：" + this.name + "报社名字是:" + ((MyNewspaper)o).getName());
    }
}

class MyNewspaper extends Observable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.equals(this.name)){
            this.name = name;
            setChanged();
        }
        //notifyObservers();
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }
}
