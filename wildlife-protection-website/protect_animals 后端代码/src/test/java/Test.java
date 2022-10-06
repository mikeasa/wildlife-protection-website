import com.github.pagehelper.PageInfo;
import com.lecheng.protectAnimals.controller.WeiXinController;
import com.lecheng.protectAnimals.dao.AdminReleaseDao;
import com.lecheng.protectAnimals.dao.MoreNewsDao;
import com.lecheng.protectAnimals.dao.UserDao;
import com.lecheng.protectAnimals.dao.VolunteerOrgDao;
import com.lecheng.protectAnimals.pojo.*;
import com.lecheng.protectAnimals.service.*;
import com.lecheng.protectAnimals.service.impl.SearchServiceImpl;
import com.lecheng.protectAnimals.utils.BaiduApi;
import com.lecheng.protectAnimals.utils.CreateUUID;
import com.lecheng.protectAnimals.utils.PasswordUtil;
import com.lecheng.protectAnimals.utils.TencentApi;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.util.HtmlUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Test {
    public static void main(String[] args) {
isMessyCode("ddsaf烦烦烦");
log.info("");

    }
@org.junit.Test
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = 0 ;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
                chLength++;
            }
        }
        float result = count / chLength ;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

}
