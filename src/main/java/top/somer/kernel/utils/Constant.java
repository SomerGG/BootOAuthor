package top.somer.kernel.utils;

/**
 * 常量
 *
 * @author Somer
 * @date 2018-03-05 15:36
 **/
public class Constant {

    /** 超级管理员ID */
    public static final Integer SUPER_ADMIN = 1;

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

        private Integer value;

        private MenuType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}
