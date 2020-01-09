package com.cloud.luban.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignalTypeEnum {


    CONTROL(0x00, "control type"),//控制类型
    INTERRUPT(0x01, "interrupt type"),//中断类型/命中响应/成绩
    RESPONSE(0x02, "response type"),//响应类型
    QUERY(0x03, "query type");//查询类型

    private int type;
    private String describe;


    public static SignalTypeEnum valueOf(int type) {
        for (SignalTypeEnum typeVar : SignalTypeEnum.values()) {
            if (typeVar.getType() == type) {
                return typeVar;
            }
        }
        return CONTROL;
    }

//    public static void main(String[] args) {
//        System.out.println(SignalTypeEnum.valueOf(2));
//    }
}
