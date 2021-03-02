package io.github.donespeak.springbootsamples.jpa;

import com.pivovarit.function.ThrowingFunction;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author DoneSpeak
 */
public class HttpTest {
    private static final AtomicReference<CloseableHttpClient> defaultHttpClient = new AtomicReference<>();

    /**
     * 获取当前资源池所使用的 {@linkplain CloseableHttpClient}.
     *
     * @return 当前资源池所使用的 {@linkplain CloseableHttpClient} 的引用.
     */
    @Getter
    private final CloseableHttpClient httpClient;

    public HttpTest() {
        httpClient = getDefaultHttpClient();
    }

    /**
     * Determines the timeout in milliseconds until a connection is established
     */
    private static final int DEFAULT_CONNECT_TIMEOUT = 5 * 60 * 1000;

    /**
     * Defines the socket timeout ({@code SO_TIMEOUT}) in milliseconds, which is the
     * timeout for waiting for data or, put differently, a maximum period inactivity
     * between two consecutive data packets).
     */
    private static final int DEFAULT_SOCKET_TIMEOUT = 60 * 1000;

    /**
     * Returns the timeout in milliseconds used when requesting a connection from
     * the connection manager.
     */
    private static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 5 * 60 * 1000;

    private static CloseableHttpClient getDefaultHttpClient() {
        CloseableHttpClient httpClient = defaultHttpClient.get();
        if (httpClient == null) {
            httpClient = HttpClients.custom().setRedirectStrategy(new DefaultRedirectStrategy() {
                @Override
                protected boolean isRedirectable(String method) {
                    return true;
                }
            }).setDefaultRequestConfig(RequestConfig.custom().setExpectContinueEnabled(true)
                    .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT).setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                    .setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT).build()).build();
            defaultHttpClient.compareAndSet(null, httpClient);
        }
        return defaultHttpClient.get();
    }

    public String get(URI uri) {
        HttpGet req = new HttpGet(uri);
        return exchange(req, this::returnContent);
    }

    private <T> T exchange(HttpUriRequest req, ThrowingFunction<HttpResponse, T, IOException> handler) {
        try (CloseableHttpResponse resp = httpClient.execute(req)) {
            return handler.apply(resp);
        } catch (HttpResponseException e) {
            if (e.getStatusCode() == 404) {
                throw new NoSuchElementException(req.getMethod() + " " + req.getURI() + " not found.");
            } else if (e.getStatusCode() >= 400 && e.getStatusCode() < 500) {
                throw new IllegalArgumentException(e);
            } else {
                throw new IllegalStateException(e);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private int throwErrorStatus(HttpResponse resp) throws HttpResponseException {
        final int statusCode = resp.getStatusLine().getStatusCode();
        if (statusCode < 200 || statusCode > 299) {
            // 这里不可以转化为ErrorMessage对象，因为HttpResourceClient不仅仅处理资源服务器的url（仅有资源服务器的才会返回ErrorMessage）
            throw new HttpResponseException(statusCode, resp.getStatusLine().getReasonPhrase());
        }
        return statusCode;
    }

    private String returnContent(HttpResponse resp) throws IOException {
        throwErrorStatus(resp);
        return IOUtils.toString(resp.getEntity().getContent(), "utf-8");
    }

}
