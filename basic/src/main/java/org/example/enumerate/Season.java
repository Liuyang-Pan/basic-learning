package org.example.enumerate;

/**
 * 枚举类 javap 反编译
 * 枚举的特征:
 * 1、枚举类都是继承了枚举类型:java.lang.Enum
 * 2、枚举都是最终类，不可以被继承。
 * 3、构造器的构造器都是私有的，枚举对外不能创建对象
 * 4、枚举类的第一行默认都是罗列枚举对象的名称的。
 */
public enum Season {

    SPRING("spring", "春天"),
    SUMMER("summer", "夏天"),
    AUTUMN("autumn", "秋天"),
    WINTER("winter", "冬天");
    private String key;

    private String value;

    public static String getValue(String key) {
        Season[] seasons = values();
        for (Season season : seasons) {
            if (season.getKey().equals(key)) {
                return season.getValue();
            }
        }
        return null;
    }


    public static String getKey(String value) {
        Season[] seasons = values();
        for (Season season : seasons) {
            if (season.getValue().equals(value)) {
                return season.getKey();
            }
        }
        return null;
    }

    Season(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
