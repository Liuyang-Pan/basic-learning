package org.example.throwable;

import java.io.Serial;

/**
 * 自定义异常的分类
 * 1、自定义编译时异常
 * 定义一个异常类继承Exception.重写构造器。
 * 在出现异常的地方用throw new自定义对象抛出
 */
public class CustomizeException extends Exception {
    @Serial
    private static final long serialVersionUID = 7408781854997126410L;

    public CustomizeException() {
        super();
    }

    public CustomizeException(String message) {
        super(message);
    }
}

/**
 * 2、自定义运行时异常
 * 定义一个异常类继承RuntimeException,重写构造器。
 * 在出现异常的地方用throw new自定义对象抛出!
 */
class CustomizeRuntimeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 370576894108284619L;

    public CustomizeRuntimeException() {
        super();
    }

    public CustomizeRuntimeException(String message) {
        super(message);
    }
}

class CustomizeExceptionTest {
    /**
     * throw :在方法内部直接创建一个异常对象，并从此点抛出
     * throws :用在方法申明上的，抛出方法内部的异常
     *
     * @param args args
     * @throws CustomizeException 自定义异常
     */
    public static void main(String[] args) throws CustomizeException {
        throw new CustomizeException("自定义异常");
    }
}
