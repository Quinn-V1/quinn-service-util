package com.quinn.util.base;

import com.quinn.util.base.exception.ParameterShouldNotEmpty;
import com.quinn.util.base.enums.CommonMessageEnum;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 流操作工具
 *
 * @author Qunhua.Liao
 * @since 2020-04-03
 */
public final class StreamUtil {

    private StreamUtil() {
    }

    /**
     * 静默关闭
     *
     * @param input 输入流
     */
    public static void closeQuietly(InputStream... input) {
        if (input != null) {
            for (InputStream in : input) {
                if (in == null) {
                    continue;
                }
                try {
                    in.close();
                } catch (IOException var2) {
                }
            }
        }

    }

    /**
     * 静默关闭
     *
     * @param output 输入流
     */
    public static void closeQuietly(OutputStream... output) {
        if (output != null) {
            for (OutputStream out : output) {
                if (out == null) {
                    continue;
                }
                try {
                    out.close();
                } catch (IOException var2) {
                }
            }
        }

    }

    /**
     * 静默关闭
     *
     * @param writers 输入流
     */
    public static void closeQuietly(Writer... writers) {
        if (writers != null) {
            for (Writer writer : writers) {
                if (writer == null) {
                    continue;
                }
                try {
                    writer.close();
                } catch (IOException var2) {
                }
            }
        }

    }

    /**
     * 静默关闭
     *
     * @param readers 输入流
     */
    public static void closeQuietly(Reader... readers) {
        if (readers != null) {
            for (Reader reader : readers) {
                if (reader == null) {
                    continue;
                }
                try {
                    reader.close();
                } catch (IOException var2) {
                }
            }
        }
    }

    /**
     * 静默关闭
     *
     * @param scanners 浏览器
     */
    public static void closeQuietly(Scanner... scanners) {
        if (scanners != null) {
            for (Scanner scanner : scanners) {
                try {
                    if (scanner == null) {
                        continue;
                    }
                    scanner.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static void closeQuietly(FileChannel... channels) {
        if (channels != null) {
            for (FileChannel channel : channels) {
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    /**
     * 静默关闭
     *
     * @param input 输入流
     */
    public static void closeQuietly(InputStream input, OutputStream output) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException var2) {
        }

        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException var2) {
        }
    }

    /**
     * 流拷贝
     *
     * @param in  输入流
     * @param out 输出流
     * @return 返回大小
     * @throws IOException IO异常
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        int byteCount = 0;
        if (in == null || out == null) {
            throw new ParameterShouldNotEmpty()
                    .addParam(CommonMessageEnum.PARAM_SHOULD_NOT_NULL.paramNames[0], "in or out")
                    .exception()
                    ;
        }

        byte[] buffer = new byte[4096];
        int bytesRead;

        for (; (bytesRead = in.read(buffer)) != -1; byteCount += bytesRead) {
            out.write(buffer, 0, bytesRead);
        }

        out.flush();
        return byteCount;
    }

    /**
     * 流转为字符串
     *
     * @param inputStream 字符串输入流
     * @return 字符串
     */
    public static String asString(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().parallel().collect(Collectors.joining("\n"));
    }

}
