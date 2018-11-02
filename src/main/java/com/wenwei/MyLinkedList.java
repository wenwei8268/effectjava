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
public interface MyLinkedList {
    public ItemObject find(int value);

    public void remove(int value);

    public void add(int value);

    public int size();
}
    class ItemObject {
        Integer intValue;
        ItemObject next;

        public ItemObject(int _intValue) {
            intValue = _intValue;
        }

        public ItemObject getNext() {
            return next;
        }

        public void setNext(ItemObject _next) {
            next = _next;
        }

        public int getItemValue() {
            return intValue;
        }

        public boolean isEqual(int _intValue) {
            return (intValue == _intValue) ? true : false;
        }
    }


