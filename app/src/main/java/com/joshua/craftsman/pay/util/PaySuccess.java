package com.joshua.craftsman.pay.util;

import java.io.Serializable;

/**
 * 支付成功的回调
 *
 * Created by wangqiang on 2017/4/16.
 */

public interface PaySuccess extends Serializable{
    void onSuccess(String orderNo);
}
