package top.xiaorang.simple.common.utils.enums;

/**
 * @author liulei
 * @version 1.0
 * @since 2022/8/15 21:23
 */
public interface NameValueEnum<T> extends ValueEnum<T> {
    /**
     * 获取枚举名称
     *
     * @return 枚举名称
     */
    String getName();
}
