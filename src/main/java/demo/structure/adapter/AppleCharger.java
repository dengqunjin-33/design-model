package demo.structure.adapter;

/**
 * 充电器接口
 */
interface Icharge{
    void charge();
}

/**
 * 苹果充电器需要适配的角色
 */
public class AppleCharger implements Icharge{

    @Override
    public void charge(){
        System.out.println("ApplePhone is charging");
    }

    public static void main(String[] args) {
        MultipleJointsCharger multipleJointsCharger = new MultipleJointsCharger();
        multipleJointsCharger.charge();
    }
}

/**
 * 定义适配器接口
 */
interface IchargeAdapter extends Icharge{
}


/**
 * 多接头充电器，支持一边充手机，一边充耳机
 */
class MultipleJointsCharger extends AppleCharger implements IchargeAdapter{
    @Override
    public void charge(){
        super.charge();
        System.out.println("Headset is charging");
    }
}