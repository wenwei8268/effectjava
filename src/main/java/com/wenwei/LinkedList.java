package com.wenwei;
/**
 * Created by wenweizww on 2018/10/16.
 */


/**
 * author:zhou_wenwei
 * mail:zhou_wenwei@wuxiapptec.com
 * date:2018/10/16
 * description:
 */
public class LinkedList implements MyLinkedList {
    transient int size;
    //   头指针
    transient ItemObject head;
    transient ItemObject tail ;
    //尾部指针
    volatile int index = 0;

    public LinkedList() {
    }

    @Override
    public ItemObject find(int value) {
        ItemObject itemObject = head;
        while (head.next != null){
            itemObject = head.next;
            if(itemObject.intValue == value){
                return itemObject;
            }
        }

        return null;
    }

    @Override
    public void remove(int value) {
        ItemObject itemObject = find(value);
        itemObject.getNext();

    }

    @Override
    public void add(int value) {

        ItemObject itemObject = new ItemObject(value);
        if((index == 0 )){
             head =tail= itemObject;
        }else {
            itemObject = tail.next;
            tail = itemObject;
        }
        index++;
        size++;
    }

    @Override
    public int size() {
        return 0;
    }
}
