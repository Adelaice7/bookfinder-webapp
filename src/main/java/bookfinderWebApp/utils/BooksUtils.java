package bookfinderWebApp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bookfinderWebApp.model.Book;

public class BooksUtils {

    private static final String API_KEY = "";

    private static final String API_URI = "https://www.googleapis.com/books/v1/volumes?q=";

    private static final String DEFAULT_AUTHOR = "Not specified";
    
    private static final Logger logger = LoggerFactory.getLogger(BooksUtils.class);

    public static List<Book> fetchData(String searchTerms) throws IOException, ServletException {
        String jsonResponse = null;
        String urlString;

        urlString = new StringBuilder(API_URI)
                .append(URLEncoder.encode(searchTerms, "UTF-8"))
                .append("&langRestrict=").append("en")
                .append("&filter=").append("ebooks")
                .append("&key=").append(API_KEY)
                .toString();

        URI uri = URI.create(urlString);
        jsonResponse = httpGetRequest(uri);

        List<Book> books = extractBookFromJson(jsonResponse);

        return books;
    }

    private static List<Book> extractBookFromJson(String volumeJson) throws IOException, ServletException {

        if (volumeJson.isEmpty()) {
            return null;
        }

        List<Book> books = new ArrayList<Book>();

        JSONObject jsonObject = new JSONObject(volumeJson);
        JSONArray items = null;

        if (!jsonObject.isNull("items")) {
            items = jsonObject.getJSONArray("items");
        }

        String title = null, subtitle = null, formattedListPrice = null, formattedRetailPrice = null;
        int listPrice = 0, retailPrice = 0;
        String date = null, previewLink = null, description = null, publisher = null;

        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                JSONObject currentVolume = items.getJSONObject(i);
                JSONObject currentVolumeInfo = currentVolume.getJSONObject("volumeInfo");
                JSONObject currentSaleInfo = currentVolume.getJSONObject("saleInfo");
                JSONObject currentListPrice = null;
                JSONObject currentRetailPrice = null;

                if (!currentSaleInfo.isNull("listPrice")) {
                    currentListPrice = currentSaleInfo.getJSONObject("listPrice");
                }
                if (!currentSaleInfo.isNull("retailPrice")) {
                    currentRetailPrice = currentSaleInfo.getJSONObject("retailPrice");
                }

                if (!currentVolumeInfo.isNull("title")) {
                    title = currentVolumeInfo.getString("title");
                }

                if (!currentVolumeInfo.isNull("subtitle")) {
                    subtitle = currentVolumeInfo.getString("subtitle");
                }

                if (currentSaleInfo.getString("saleability").equals("FOR_SALE")) {
                    listPrice = currentListPrice.getInt("amount");
                    retailPrice = currentRetailPrice.getInt("amount");
                    String listCurrency = currentListPrice.getString("currencyCode");
                    String retailCurrency = currentRetailPrice.getString("currencyCode");

                    formattedListPrice = concatPrice(listPrice, listCurrency);
                    formattedRetailPrice = concatPrice(retailPrice, retailCurrency);

                }

                if (!currentVolumeInfo.isNull("publishedDate")) {
                    date = currentVolumeInfo.getString("publishedDate");
                }

                if (!currentVolumeInfo.isNull("previewLink")) {
                    previewLink = currentVolumeInfo.getString("previewLink");
                }

                if (!currentVolumeInfo.isNull("description")) {
                    description = currentVolumeInfo.getString("description");
                }
                
                if (!currentVolumeInfo.isNull("publisher")) {
                    publisher = currentVolumeInfo.getString("publisher");
                }

                if (!currentVolumeInfo.isNull("authors")) {
                    JSONArray authors = currentVolumeInfo.getJSONArray("authors");
                    books.add(new Book(title, concatAuthors(authors), date, subtitle, previewLink, listPrice,
                            formattedListPrice, retailPrice, formattedRetailPrice,
                            description, publisher));
                }
                /*
                 * books.add(new Book(title, DEFAULT_AUTHOR, date, subtitle, previewLink, listPrice,
                 * retailPrice, description));
                 */
            }
        } else {
            return null;
        }

        return books;
    }

    private static String concatPrice(int amt, String currency) {
        StringBuilder builder = new StringBuilder();
        if (currency.equals("HUF")) {
            builder.append(amt);
            builder.append(" ");
            builder.append("Ft");
        }
        return builder.toString();
    }

    private static String concatAuthors(JSONArray authors) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < authors.length(); i++) {
            builder.append(authors.get(authors.length() - 1));
        }

        return builder.toString();
    }

    private static String httpGetRequest(URI url) throws IOException, ServletException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest;
        CloseableHttpResponse response = null;
        InputStream in = null;
        String jsonResponse = null;
        try {
            getRequest = new HttpGet(url);

            getRequest.addHeader("accept", "application/json");

            response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() == 200) {
                in = response.getEntity().getContent();
                jsonResponse = readFromStream(in);
            } else {
                logger.error("ERROR! Response code: " + response.getStatusLine().getStatusCode());
                throw new ServletException("ERROR! Response code: " + response.getStatusLine().getStatusCode());
            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }

            response.close();
            httpClient.close();
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
        }
        return output.toString();
    }

}
