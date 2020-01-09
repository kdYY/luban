package com.cloud.core.constant;

/**
 * @ClassName MenuConstant
 * @Description: 菜单常量
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
public class MenuConstant {
    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
