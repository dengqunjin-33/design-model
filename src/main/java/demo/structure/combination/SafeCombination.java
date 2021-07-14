package demo.structure.combination;

import java.util.ArrayList;
import java.util.List;
//安全式组合模式的结构

/**
 * 抽象构件角色类
 */
abstract class Component{
    /**
     * 输出组建自身的名称
     * @param preStr
     */
    abstract void printStruct(String preStr);

    /**
     * 聚集管理方法 添加一个子构建对象
     * @param child
     */
    public void addChild(Component child){
        throw new UnsupportedOperationException("不支持此功能");
    }

    /**
     * 聚集管理方法 删除一个子构建对象
     */
    public void removeChild(int index){
        throw new UnsupportedOperationException("对象不支持此功能");
    }

    /**
     * 聚集管理方法，返回所有子构件对象
     */
    public List<Component> getChild(){
        throw new UnsupportedOperationException("对象不支持此功能");
    }
}

/**
 * 树枝构件角色类
 */
class Composite extends Component{
    private List<Component> childList = new ArrayList<>();

    private String name;

    public Composite(String name){
        this.name = name;
    }

    @Override
    public void addChild(Component child) {
        childList.add(child);
    }

    @Override
    public void removeChild(int index) {
        childList.remove(index);
    }

    @Override
    public List<Component> getChild() {
        return childList;
    }

    @Override
    void printStruct(String preStr) {
        System.out.println(preStr + "-" + this.name);
        if (null != childList){
            for(Component child : childList){
                child.printStruct(preStr);
            }
        }
    }
}

/**
 * 树叶构建角色类
 */
class Leaf extends Component{
    private String name;

    Leaf(String name){
        this.name = name;
    }

    @Override
    void printStruct(String preStr) {
        System.out.println(preStr + "-" + name);
    }
}

/**
 * @author 86134
 */
public class SafeCombination {
    public static void main(String[] args) {
        Component root = new Composite("服装");
        Component c1 = new Composite("男装");
        Component c2 = new Composite("女装");

        Component leaf1 = new Leaf("衬衫");
        Component leaf2 = new Leaf("夹克");
        Component leaf3 = new Leaf("裙子");
        Component leaf4 = new Leaf("套装");

        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(leaf1);
        c1.addChild(leaf2);
        c2.addChild(leaf3);
        c2.addChild(leaf4);

        root.printStruct("");
    }
}