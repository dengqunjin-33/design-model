package demo.RBTree;

import java.util.HashMap;

/**
 * 红黑树实战
 * @author 86134
 */
public class RbTree {

    /**根节点*/
    private RbNode root;

    /**红色*/
    private static final boolean RED = false;
    /**黑色色*/
    private static final boolean BLACK = true;

    public RbTree(RbNode root) {
        this.root = root;
    }


    public class RbNode{
        boolean color;
        int val;
        RbNode parent;
        RbNode left;
        RbNode right;
        public RbNode(int val,boolean color,RbNode parent,RbNode left,RbNode right){
            this.val = val;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    /**左旋 假设x为左边的节点*/
    private void leftRotate(RbNode x){
        RbNode right = x.right;
        RbNode parent = x.parent;
        x.right = right.left;
        if (null != right.left){
            right.left.parent = x;
        }
        right.parent = parent;
        if (null == parent){
            this.root = right;
        }else {
            if (parent.left == x){
                parent.left = right;
            }else {
                parent.right = right;
            }
        }
        right.left = x;
        x.parent = right;
    }

    /**右旋  假设x为右边的节点*/
    private void rightRotate(RbNode x){
        RbNode left = x.left;
        RbNode parent = x.parent;
        x.left = left.right;
        if (null != left.right){
            left.right.parent = x;
        }
        left.parent = parent;
        if (null == parent){
            this.root = left;
        }else {
            if (parent.left == x){
                parent.left = left;
            }else {
                parent.right = left;
            }
        }
        left.right = x;
        x.parent = left;
    }

    private void insert(RbNode node){
        //插入节点的位置
        RbNode insertNode = null;
        //临时节点，用于找到插入节点的位置
        RbNode temp = this.root;

        //查找要插入的节点
        while (null != temp){
            insertNode = temp;
            if (node.val < temp.val){
                temp = temp.left;
            }else {
                temp = temp.right;
            }
        }

        //插入
        node.parent = insertNode;
        if (null != insertNode){
            if (node.val < insertNode.val){
                insertNode.left = node;
            }else {
                insertNode.right = node;
            }
            //不存在就说明当前树没有节点
        }else {
            this.root = node;
        }

        //染色
        node.color = RED;

        //将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }

    private void insertFixUp(RbNode node){
        //父节点
        RbNode parent = node.parent;

        while ((null !=  parent) && RED == parent.color){
            //祖父节点
            RbNode grandParent = parent.parent;
            if (null != grandParent) {
                //如果父节点是祖父节点的左孩子
                if (parent == grandParent.left) {
                    RbNode uncle = grandParent.right;
                    //case 1条件：叔叔的节点的颜色是红色
                    if (null != uncle && RED == uncle.color) {
                        uncle.color = BLACK;
                        parent.color = BLACK;
                        grandParent.color = RED;
                        node = grandParent;
                        continue;
                    }

                    //case 2条件：叔叔是黑色
                    if (node == parent.right) {
                        RbNode temp;
                        leftRotate(parent);
                        temp = parent;
                        parent = node;
                        node = temp;
                    }

                    //case 3条件：叔叔是黑色，并且当前节点是左孩子
                    parent.color = BLACK;
                    grandParent.color = RED;
                    rightRotate(grandParent);
                } else {//如果父节点是祖父节点的右孩子
                    RbNode uncle = grandParent.left;
                    if (null != uncle && RED == uncle.color) {
                        uncle.color = BLACK;
                        parent.color = BLACK;
                        grandParent.color = RED;
                        node = grandParent;
                        continue;
                    }

                    //case 2条件：叔叔是黑色
                    if (node == parent.right) {
                        RbNode temp;
                        rightRotate(parent);
                        temp = parent;
                        parent = node;
                        node = temp;
                    }

                    //case 3条件：叔叔是黑色，并且当前节点是右孩子
                    parent.color = BLACK;
                    grandParent.color = RED;
                    leftRotate(grandParent);
                }
            }
        }

        this.root.color = BLACK;
    }

}
