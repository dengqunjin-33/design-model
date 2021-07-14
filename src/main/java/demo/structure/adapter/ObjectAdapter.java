package demo.structure.adapter;

public class ObjectAdapter {
    public static void main(String[] args) {
        UniversalCharger universalCharger = new UniversalCharger(new ObjAndroidCharger());
        universalCharger.charge();
        universalCharger = new UniversalCharger(new ObjAppleCharger());
        universalCharger.charge();
    }
}

/**
 * 充电器接口
 */
interface ObjIcharge{
    void charge();
}

/**
 * 安卓充电器
 */
class ObjAndroidCharger implements ObjIcharge{
    @Override
    public void charge(){
        System.out.println("AndroidPhone is charging ...");
    }
}

/**
 *苹果充电器
 */
class ObjAppleCharger implements ObjIcharge{

    @Override
    public void charge(){
        System.out.println("ApplePhone is charging ...");
    }
}

/**
 * 对特殊的充电器进行适配，适配器接口
 */
interface ObjIchargeAdapter{
    void charge();
}

/**
 * 万能充电器接口
 */
class UniversalCharger implements ObjIchargeAdapter{
    private ObjIcharge objIcharge;

    public UniversalCharger(ObjIcharge objIcharge){
        this.objIcharge = objIcharge;
    }

    @Override
    public void charge(){
        objIcharge.charge();
    }
}