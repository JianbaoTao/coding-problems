package com.tao;

import org.apache.commons.io.IOUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeTypes;
import org.junit.Test;

import javax.activation.MimeType;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdhocTest {
    static class IntegerComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            if (a.equals(b)) return 0;
            List<String> list = Arrays.asList(a + b, b + a);
            Collections.sort(list);
            if (list.get(0).equals(a + b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    @Test
    public void test_StringSort() throws Exception {
        List<String> list = Arrays.asList("34", "32", "43", "4", "3", "3", "40");
        Collections.sort(list, new IntegerComparator());
        Collections.reverse(list);
        System.out.println("sorted list: " + list);
    }

    @Test
    public void testMatcher() throws Exception {
        String userAgent = "user agent;__JOB_ID__:12345-234:1;";
        Pattern pattern = Pattern.compile(";__JOB_ID__:(.*?);");
        Matcher matcher = pattern.matcher(userAgent);

        matcher.find();

        String match = matcher.group(1);
        System.out.println("match - " + match);

        String newUserAgent = matcher.replaceFirst("");
        System.out.println("new - " + newUserAgent);

    }

    @Test
    public void testCharset() throws Exception {
        String contentType = "text/html";
        String charset = new MimeType(contentType).getParameter("charset");
//        Charset set = Charset.availableCharsets().get(charset);
        System.out.println(charset);
        System.out.println(new Date(1426729607037L));
//        assertEquals(set, Charset.availableCharsets().get("ISO-8859-4"));
    }

    @Test
    public void testMimeType() throws Exception {
        String dir = "/Users/jianbao/yahoo/tns/git/ct/ct_proxy_server/src/test/resources/data/mime-test";

        String content = IOUtils.toString(new FileReader(dir + "/html-002.tmp"));

        InputStream stream = new ByteArrayInputStream(content.getBytes());

        MimeTypes mimeTypes = new MimeTypes();
        Metadata metadata = new Metadata();
        metadata.add(Metadata.CONTENT_TYPE, "application/xml");
        System.out.println(mimeTypes.detect(stream, metadata));
    }
}
