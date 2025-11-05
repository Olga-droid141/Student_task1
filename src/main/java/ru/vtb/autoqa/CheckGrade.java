package ru.vtb.autoqa;

import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class CheckGrade {

    private static final String HOST = "http://localhost:5352";
    private static final String ADD_PATH = HOST + "/checkGrade?grade=";
    private static final String RATE_PATH = HOST + "/educ?sum=";

    private CloseableHttpClient httpClient;

    public CheckGrade() {
        httpClient = HttpClients.createDefault();
    }

    @SneakyThrows
    public boolean addGrade(int grade) {
        HttpGet request = new HttpGet(ADD_PATH + grade);
        CloseableHttpResponse httpResponse = httpClient.execute(request);
        HttpEntity entity = httpResponse.getEntity();
        return Boolean.parseBoolean(EntityUtils.toString(entity));
    }

    @SneakyThrows
    public HttpEntity raiting(List<Integer> grades) {
        HttpGet request = new HttpGet(RATE_PATH + grades.stream().mapToInt(x -> x).sum());
        CloseableHttpResponse httpResponse = httpClient.execute(request);
        return httpResponse.getEntity();
    }
}
