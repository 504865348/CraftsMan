package com.joshua.craftsman.http;

/**
 * ============================================================
 * <p>
 * 版 权 ： 吴奇俊  (c) 2017
 * <p>
 * 作 者 : 吴奇俊
 * <p>
 * 版 本 ： 1.0
 * <p>
 * 创建日期 ： 2017/5/6 14:06
 * <p>
 * 描 述 ：
 * <p>
 * ============================================================
 **/

public class ResponseInfo {
    private boolean isAlive;
    private boolean isError;
    private String result;

    public ResponseInfo(boolean isAlive, boolean isError, String result) {
        this.isAlive = isAlive;
        this.isError = isError;
        this.result = result;
    }

    public boolean isAlive() {
        return !isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
