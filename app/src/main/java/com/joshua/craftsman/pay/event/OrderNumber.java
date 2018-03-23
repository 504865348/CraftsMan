package com.joshua.craftsman.pay.event;

/**
 * ============================================================
 * <p>
 * 版 权 ： 吴奇俊  (c) 2018
 * <p>
 * 作 者 : 吴奇俊
 * <p>
 * 版 本 ： 1.0
 * <p>
 * 创建日期 ： 2018/3/22 0:28
 * <p>
 * 描 述 ：
 * <p>
 * ============================================================
 **/

public class OrderNumber {
    private String message;
    public  OrderNumber(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
