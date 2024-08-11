package uk.zwfield.jx3bot.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.bonigarcia.wdm.WebDriverManager;
import jx3api.api.http.data.LuckAdventureData;
import jx3api.api.http.data.RoleAttributeData;
import jx3api.api.http.data.ServerSandData;
import jx3api.api.http.data.TradeRecordData;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Field
 * @date 2024-08-09 21:42
 **/
public class HTML2PNGUtil {

    private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_33);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        cfg.setClassForTemplateLoading(HTML2PNGUtil.class, "/templates");
    }

    /**
     * 奇遇记录
     */
    public static String luckAdventure(String server, String name, List<LuckAdventureData> luckAdventureDataList) throws IOException, TemplateException, ParserConfigurationException, SAXException, URISyntaxException {
        Template template = cfg.getTemplate("角色奇遇.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("server", server);
        Map<Integer, List<LuckAdventureData>> listMap = luckAdventureDataList.stream().collect(Collectors.groupingBy(LuckAdventureData::getLevel));
        // 普通
        List<LuckAdventureData> level1List = listMap.getOrDefault(1, new ArrayList<>());
        level1List.addAll(listMap.getOrDefault(2, new ArrayList<>()));
        level1List.sort(Comparator.comparingLong(LuckAdventureData::getTime).reversed());
        data.put("level1", level1List);
        data.put("level1Size", level1List.size());
        // 宠物
        List<LuckAdventureData> level2List = listMap.getOrDefault(3, new ArrayList<>());
        level2List.sort(Comparator.comparingLong(LuckAdventureData::getTime).reversed());
        data.put("level2", level2List);
        data.put("level2Size", level2List.size());
        Writer out = new StringWriter();
        template.process(data, out);
        return convert(out.toString());
    }

    public static String roleAttribute(RoleAttributeData roleAttributeData) throws IOException, TemplateException, URISyntaxException {
        Template template = cfg.getTemplate("角色装备.ftl");
        Writer out = new StringWriter();
        template.process(objectMapper.readValue(objectMapper.writeValueAsString(roleAttributeData), Map.class), out);
        return convert(out.toString());
    }

    public static String tradeRecord(TradeRecordData tradeRecordData) throws IOException, TemplateException, URISyntaxException {
        Template template = cfg.getTemplate("物品价格.ftl");
        Writer out = new StringWriter();
        Map<String, Object> map = objectMapper.readValue(objectMapper.writeValueAsString(tradeRecordData), new TypeReference<>() {
        });
        template.process(map, out);
        return convert(out.toString());
    }

    public static String serverSand(ServerSandData sandData) throws IOException, TemplateException, URISyntaxException {
        Template template = cfg.getTemplate("沙盘.ftl");
        Writer out = new StringWriter();
        Map<String, Object> map = objectMapper.readValue(objectMapper.writeValueAsString(sandData), new TypeReference<>() {
        });
        Map<String, String> sandMap = sandData.getData().stream().distinct().collect(Collectors.toMap(ServerSandData.CastleInfo::getCastleName, e -> "浩气盟".equals(e.getCampName()) ? "h" : "e"));
        map.put("map", sandMap);
        template.process(map, out);
        return convert(out.toString());
    }

    public static String newsAnnounce(String url) {
        RemoteWebDriver driver = getDriver();
        driver.get(url);
        WebElement element = driver.findElement(By.className("allnews_list_container"));
        int height = ((Long) ((JavascriptExecutor) driver).executeScript("return document.documentElement.scrollHeight")).intValue();
        // Set the window size to the dimensions of the entire page
        driver.manage().window().setSize(new Dimension(750, height));
        return element.getScreenshotAs(OutputType.BASE64);
    }


    private static String convert(String html) throws IOException {
        RemoteWebDriver driver = getDriver();
        // 创建临时文件
        File tempHtmlFile = File.createTempFile("tempHtml", ".html");
        try (FileWriter writer = new FileWriter(tempHtmlFile)) {
            writer.write(html);
        }
        try {
            // 加载 HTML 文件
            driver.get(tempHtmlFile.toURI().toString());
            // Get the dimensions of the entire page
            int width = ((Long) ((JavascriptExecutor) driver).executeScript("return document.documentElement.scrollWidth")).intValue();
            int height = ((Long) ((JavascriptExecutor) driver).executeScript("return document.documentElement.scrollHeight")).intValue();

            // Set the window size to the dimensions of the entire page
            driver.manage().window().setSize(new Dimension(width, height));

            // 截图
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } finally {
            // 关闭 WebDriver
            driver.quit();
            // 删除临时文件
            if (tempHtmlFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                tempHtmlFile.delete();
            }
        }
    }

    private static RemoteWebDriver getDriver() {
        // 设置 WebDriver
        if (System.getProperty("os.name").toLowerCase().contains("nux")) {
            // Linux 系统
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1320,800");
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(options);
        } else {
            // windows 自己下
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1320,800");
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        }
    }
}
