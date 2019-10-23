package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private int PAGE_VALUE = 0;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        Document document;
        try {
            document = getDocument(searchString, PAGE_VALUE);
            while (true) {
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0 || PAGE_VALUE == 100) {
                    PAGE_VALUE = 0;
                    break;
                }
                for (Element element : elements) {
                    if (element != null) {
                        Vacancy vac = new Vacancy();
                        vac.setTitle(element.getElementsByAttributeValueContaining("data-qa", "title").text());
                        vac.setCity(element.getElementsByAttributeValueContaining("data-qa", "address").text());
                        vac.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                        vac.setSiteName(URL_FORMAT);
                        String urlPage = element.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
                        vac.setUrl(urlPage);
                        String salary = element.getElementsByAttributeValueContaining("data-qa", "compensation").text();
                        vac.setSalary(salary.length() == 0 ? "" : salary);
                        result.add(vac);
                    }
                }
                ++PAGE_VALUE;
                document = getDocument(searchString, PAGE_VALUE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String myURL = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(myURL)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1.1 Safari/605.1.15")
                .referrer("")
                .get();
    }
}
