package Medwards11_JH7.Webcrawler;

import edu.uci.ics.crawler4j.crawler.WebCrawler;

public class Crawler extends WebCrawler{

    private String url;
    private String[] keywords;
    private int threadID;
    public Crawler(String url, String[] keyword) {
        threadID = 0;
    }

    public void search() {
        run();
        threadID++;
    }

    @Override
    public void run() {
        int id = threadID;
        //webCrawlerCode
    }

    public int getNumberOfThreads() {
        return threadID;
    }

    @Override
    public boolean shouldVisit() {
        String href =
    }
}
