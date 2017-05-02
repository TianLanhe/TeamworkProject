package teamwork.updater;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class NewsUpdater {
  private Document doc;

  public boolean connect(String url) {
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  public String getContent() {
    if (doc == null) {
      return null;
    }
    String str =
        doc.html().replace("&nbsp;", "$").replace("<strong>", "*").replace("</strong>", "");
    doc = Jsoup.parse(str);

    Element content = null;

    if (doc.getElementById("articleContent") != null) {
      content = doc.getElementById("articleContent");
    } else {
      content = doc.getElementById("page_a");
    }

    String text =
        content.text().replace(" ", "$").replace("$$$$$", "\n    ").replace("$", " ")
            .replace("    *", "")
            + "\n";
    return text;
  }
}
