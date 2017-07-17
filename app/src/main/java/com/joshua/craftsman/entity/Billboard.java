package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/4/29.
 * 底部按钮--榜单(每个具体榜单的前2名)
 */

public class Billboard {

    /* 从服务器拿到的字段 */
    private String recordTitle_one;
    private String recordTitle_two;
    private String recordTitle_three;
    private String recordTitle_four;
    private String recordTitle_five;
    private String recordTitle_six;
    private String recordTitle_seven;
    private String recordTitle_eight;

    public String getRecordTitle_one() {
        return recordTitle_one;
    }

    public void setRecordTitle_one(String recordTitle_one) {
        this.recordTitle_one = recordTitle_one;
    }

    public String getRecordTitle_two() {
        return recordTitle_two;
    }

    public void setRecordTitle_two(String recordTitle_two) {
        this.recordTitle_two = recordTitle_two;
    }

    public String getRecordTitle_three() {
        return recordTitle_three;
    }

    public void setRecordTitle_three(String recordTitle_three) {
        this.recordTitle_three = recordTitle_three;
    }

    public String getRecordTitle_four() {
        return recordTitle_four;
    }

    public void setRecordTitle_four(String recordTitle_four) {
        this.recordTitle_four = recordTitle_four;
    }

    public String getRecordTitle_five() {
        return recordTitle_five;
    }

    public void setRecordTitle_five(String recordTitle_five) {
        this.recordTitle_five = recordTitle_five;
    }

    public String getRecordTitle_six() {
        return recordTitle_six;
    }

    public void setRecordTitle_six(String recordTitle_six) {
        this.recordTitle_six = recordTitle_six;
    }

    public String getRecordTitle_seven() {
        return recordTitle_seven;
    }

    public void setRecordTitle_seven(String recordTitle_seven) {
        this.recordTitle_seven = recordTitle_seven;
    }

    public String getRecordTitle_eight() {
        return recordTitle_eight;
    }

    public void setRecordTitle_eight(String recordTitle_eight) {
        this.recordTitle_eight = recordTitle_eight;
    }

    public Billboard(String recordTitle_one, String recordTitle_two, String recordTitle_three, String recordTitle_four, String recordTitle_five, String recordTitle_six, String recordTitle_seven, String recordTitle_eight) {
        this.recordTitle_one = recordTitle_one;
        this.recordTitle_two = recordTitle_two;
        this.recordTitle_three = recordTitle_three;
        this.recordTitle_four = recordTitle_four;
        this.recordTitle_five = recordTitle_five;
        this.recordTitle_six = recordTitle_six;
        this.recordTitle_seven = recordTitle_seven;
        this.recordTitle_eight = recordTitle_eight;
    }

    @Override
    public String toString() {
        return "Billboard{" +
                "recordTitle_one='" + recordTitle_one + '\'' +
                ", recordTitle_two='" + recordTitle_two + '\'' +
                ", recordTitle_three='" + recordTitle_three + '\'' +
                ", recordTitle_four='" + recordTitle_four + '\'' +
                ", recordTitle_five='" + recordTitle_five + '\'' +
                ", recordTitle_six='" + recordTitle_six + '\'' +
                ", recordTitle_seven='" + recordTitle_seven + '\'' +
                ", recordTitle_eight='" + recordTitle_eight + '\'' +
                '}';
    }
}
