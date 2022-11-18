package org.example.file.io;

import java.io.*;
import java.util.Date;

/**
 * 字节流:
 * InputStream/OutputStream(输入/输出)
 * 字符流:
 * Reader/Writer(输入/输出)
 */
public class IOStream {
    public static void main(String[] args) {
        try {
            fileOutputStreamMethod();
            fileInputStreamMethodOne();
            fileCopy("/IdeaProjects/basic-learning/basic/src/main/resources/write.txt",
                    "/IdeaProjects/basic-learning/basic/src/main/resources/write2.txt",
                    false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件拷贝
     *
     * @param source 原路径
     * @param target 目标路径
     * @param flag   追加标识
     */
    public static void fileCopy(String source, String target, boolean flag) {
        //try with resource
        {
            try (InputStream inputStream = new FileInputStream(source); OutputStream outputStream = new FileOutputStream(target, flag)) {
                byte[] inputBytes = new byte[1024];
                int len;
                while ((len = inputStream.read(inputBytes)) != -1) {
                    outputStream.write(inputBytes, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //try catch finally
        {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                inputStream = new FileInputStream(source);
                outputStream = new FileOutputStream(target, flag);
                byte[] inputBytes = new byte[1024];
                int len;
                while ((len = inputStream.read(inputBytes)) != -1) {
                    outputStream.write(inputBytes, 0, len);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != inputStream)
                        inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (null != outputStream)
                        outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void fileOutputStreamMethod() throws IOException {
        {
            //默认会将文件内容清空再重新写入，带上append的true参数可追加
            OutputStream outputStream = new FileOutputStream("/IdeaProjects/basic-learning/basic/src/main/resources/write.txt", true);
            outputStream.write('a');
            //刷新流，写入立即生效
            outputStream.flush();
            outputStream.write('a');
            outputStream.write("测试".getBytes());
            //关闭流，也会刷新
            outputStream.close();
        }
    }

    public static void fileInputStreamMethodOne() throws IOException {
        {
            InputStream inputStream = new FileInputStream("/IdeaProjects/basic-learning/basic/src/main/resources/basic.log");
            //read()每次读取一个字节返回,读取完返回-1
            int readByte;
            while ((readByte = inputStream.read()) != -1) {
                System.out.print(readByte);
            }
            System.out.println("");
            inputStream.close();
        }

        {
            InputStream inputStreamTwo = new FileInputStream("/IdeaProjects/basic-learning/basic/src/main/resources/basic.log");
            byte[] bytes = new byte[1024];
            //每次读取1024个字节放入bytes中,若是文件内的字节小于当前缓存的大小会从前面填充并保留上次的在数组后
            int read;
            while ((read = inputStreamTwo.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, read));
            }
            inputStreamTwo.close();
        }

        {
            File file = new File("/IdeaProjects/basic-learning/basic/src/main/resources/basic.log");
            InputStream inputStreamMax = new FileInputStream(file);
            //一次性读取全部字节流，文件过大可能会引起内存溢出异常
            byte[] byteMax = new byte[(int) file.length()];
            int readMax;
            while ((readMax = inputStreamMax.read(byteMax)) != -1) {
                System.out.print(new String(byteMax, 0, readMax));
            }
            inputStreamMax.close();
        }
    }
}

class CharacterIOStream {
    public static void main(String[] args) {
        fileReader();
        fileWriter();
    }

    public static void fileReader() {
        {
            try (
                    //字符输入流
                    Reader reader = new FileReader("/IdeaProjects/basic-learning/basic/src/main/resources/basic.log")
            ) {
                Date start = new Date();
                //每次读取一个字符，读取完返回-1
                int read;
                while ((read = reader.read()) != -1) {
                    System.out.print(((char) read));
                }
                Date end = new Date();
                System.out.println("----------------------------------------------无缓存----------------------------------------------");
                System.out.println(end.getTime() - start.getTime() + "ms");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        {
            try (
                    //字符输入流
                    Reader reader = new FileReader("/IdeaProjects/basic-learning/basic/src/main/resources/basic.log")
            ) {
                Date start = new Date();
                char[] chars = new char[1024];
                int read;
                while ((read = reader.read(chars)) != -1) {
                    System.out.print(new String(chars, 0, read));
                }
                Date end = new Date();
                System.out.println("----------------------------------------------有缓存----------------------------------------------");
                System.out.println(end.getTime() - start.getTime() + "ms");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileWriter() {
        {
            try (
                    Writer writer = new FileWriter("/IdeaProjects/basic-learning/basic/src/main/resources/write.txt", true)
            ) {
                writer.write("测试\r\n");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}