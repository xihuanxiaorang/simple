package top.xiaorang.simple.common.utils.enums;

import cn.hutool.core.util.StrUtil;

/**
 * 枚举常用工具类
 *
 * @author liulei
 * @version 1.0
 * @since 2022/8/15 17:13
 */
public final class EnumUtil {
    /**
     * 判断枚举值是否存在于指定枚举数组中
     *
     * @param enums 枚举数组
     * @param value 枚举值
     * @param <T>   枚举值类型
     * @return 是否存在
     */
    public static <T> boolean isExist(ValueEnum<T>[] enums, T value) {
        if (value == null) return false;
        for (ValueEnum<T> e : enums) {
            if (value.equals(e.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据枚举值获取其对应的名称
     *
     * @param enums 枚举数组
     * @param value 枚举值
     * @param <T>   枚举值类型
     * @return 枚举名称
     */
    public static <T> String getNameByValue(NameValueEnum<T>[] enums, T value) {
        if (value == null) return null;
        for (NameValueEnum<T> e : enums) {
            if (value.equals(e.getValue())) {
                return e.getName();
            }
        }
        return null;
    }

    /**
     * 根据枚举名称获取其对应的值
     *
     * @param enums 枚举数组
     * @param name  枚举名称
     * @param <T>   枚举值类型
     * @return 枚举值
     */
    public static <T> T getValueByName(NameValueEnum<T>[] enums, String name) {
        if (StrUtil.isEmpty(name)) return null;
        for (NameValueEnum<T> e : enums) {
            if (name.equals(e.getName())) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * 根据枚举值获取对应的枚举对象
     *
     * @param enums 枚举数组
     * @param value 枚举值
     * @param <T>   枚举值类型
     * @return 枚举对象
     */
    public static <T> ValueEnum<T> getEnumByValue(ValueEnum<T>[] enums, T value) {
        if (value == null) return null;
        for (ValueEnum<T> e : enums) {
            if (value.equals(e.getValue())) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据枚举名称获取对应的枚举对象
     *
     * @param enums 枚举数组
     * @param name  枚举名称
     * @param <T>   枚举值类型
     * @return 枚举对象
     */
    public static <T> NameValueEnum<T> getEnumByName(NameValueEnum<T>[] enums, String name) {
        if (StrUtil.isEmpty(name)) return null;
        for (NameValueEnum<T> e : enums) {
            if (name.equals(e.getName())) {
                return e;
            }
        }
        return null;
    }
}
