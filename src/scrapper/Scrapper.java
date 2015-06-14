/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author DiegoFernando
 */
public class Scrapper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException, IOException, MalformedURLException {
        URL url;
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<Thread> tlist = new ArrayList<Thread>();
        String temp = "";
        int id;
        url = new URL("https://bugzilla.mozilla.org/buglist.cgi?field0-0-0=bug_status&type0-0-0=changedto&value0-0-0=ASSIGNED&bug_severity=enhancement");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                if (line.contains("bz_bugitem")) {
                    Pattern p = Pattern.compile("-?\\d+");
                    Matcher m = p.matcher(line);
                    while (m.find()) {
                        lines.add(m.group());
                    }
                }
            }
        }
        for (int k = 0; k < lines.size(); k++) {
            id = Integer.parseInt(lines.get(k));
            url = new URL("https://bugzilla.mozilla.org/show_bug.cgi?id=" + lines.get(k));
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    temp = temp + line;
                }
            }
            Document doc = Jsoup.parse(temp);
            ArrayList<Elements> grap = new ArrayList<Elements>();
            grap.add(doc.select("#bz_field_status"));
            grap.add(doc.select("#field_container_product"));
            grap.add(doc.select("#field_container_component"));
            Elements users = doc.select(".bz_comment_user");
            Elements time = doc.select(".bz_comment_time");
            Elements comments = doc.select(".bz_comment_text");
            ArrayList<Comment> comlist = new ArrayList<Comment>();
            for (int i = 0; i < users.size(); i++) {
                comlist.add(new Comment(users.get(i).text(), time.get(i).text(), comments.get(i).text()));
            }
            tlist.add(new Thread(id, grap.get(0).text(), grap.get(1).text(), grap.get(2).text(), comlist));
        }
    }

}
