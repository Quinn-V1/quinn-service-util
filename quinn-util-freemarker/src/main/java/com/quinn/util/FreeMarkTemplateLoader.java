package com.quinn.util;

import com.quinn.util.constant.StringConstant;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * FreeMark模板解析工具类
 *
 * @author Qunhua.Liao
 * @since 2020-02-12
 */
public class FreeMarkTemplateLoader implements TemplateLoader {

    private static final String DEFAULT_TEMPLATE_KEY = "_default_template_key";

    private Map templates = new HashMap();

    public FreeMarkTemplateLoader(String defaultTemplate) {
        if (defaultTemplate != null && !defaultTemplate.equals("")) {
            templates.put(DEFAULT_TEMPLATE_KEY, defaultTemplate);
        }
    }

    public void AddTemplate(String name, String template) {
        if (name == null || template == null || name.equals("")
                || template.equals("")) {
            return;
        }
        if (!templates.containsKey(name)) {
            templates.put(name, template);
        }
    }

    @Override
    public void closeTemplateSource(Object templateSource) {

    }

    @Override
    public Object findTemplateSource(String name) {
        if (name == null || name.equals("")) {
            name = DEFAULT_TEMPLATE_KEY;
        }
        return templates.get(name);
    }

    @Override
    public long getLastModified(Object templateSource) {
        return 0;
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) {
        return new StringReader((String) templateSource);
    }

    /**
     * 解析模板
     *
     * @param temp "欢迎：${user}"
     * @param cond cond.put("user", "廖群华")
     * @return 解析后的值
     */
    public static String invoke(String temp, Map<String, Object> cond) {
        if (temp == null || StringConstant.STRING_EMPTY.equals(temp)) {
            return temp;
        }

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setTemplateLoader(new FreeMarkTemplateLoader(temp));
        cfg.setDefaultEncoding(StringConstant.SYSTEM_DEFAULT_CHARSET);
        cfg.setClassicCompatible(true);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        try {
            Template template = cfg.getTemplate(StringConstant.STRING_EMPTY);
            StringWriter writer = new StringWriter();
            template.process(cond, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("消息模板解析失败" + e.getMessage(), e);
        }
    }
}
