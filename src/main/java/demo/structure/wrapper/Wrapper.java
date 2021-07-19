package demo.structure.wrapper;

/**
 * @author 86134
 */
public class Wrapper {

    public static void main(String[] args) {
        DogAnimal dogAnimal = new DogAnimal();
        System.out.println("------装饰前：-------");
        dogAnimal.function();
        DogExtend dogExtend = new DogExtend(dogAnimal);
        System.out.println("------装饰后：-------");
        dogExtend.function();
    }
}

interface Animal{
    void function();
}

class DogAnimal implements Animal{

    @Override
    public void function() {
        System.out.println("基本功能：呼吸+觅食+睡觉");
    }
}

class DogWrapper implements Animal{

    private Animal dogAnimal;

    public DogWrapper(Animal animal){
        this.dogAnimal = animal;
    }

    @Override
    public void function() {
        dogAnimal.function();
    }
}

class DogExtend extends DogWrapper{

    public DogExtend(Animal animal) {
        super(animal);
    }

    @Override
    public void function(){
        super.function();;
        System.out.println("附加功能");
        this.eat();
        this.bellow();
    }
    private void eat() {
        System.out.println("吃肉");
    }

    private void bellow() {
        System.out.println("吼叫");
    }

}

