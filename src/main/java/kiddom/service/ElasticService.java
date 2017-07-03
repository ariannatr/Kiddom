package kiddom.service;


import kiddom.model.SingleEventEntity;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Christos on 3/7/2017.
 */
@Service("elasticService")
public interface ElasticService {

    public Client getTransportClient();
    public void createRecord(Client restClient, String id, String name, String date, String descr, String cat, String subcat1, String subcat2, String subcat3
            , String town, String area, String address, String number, String start, String end, String avail, String price, String postcode);
    public SingleEventEntity getEntry(Client client, String id);
    public void deleteIndex(RestClient restClient);
    public void deleteRecord(Client restClient, String id);
    public void updateRecord(Client restClient, String id,String name, String date, String descr, String cat, String subcat1, String subcat2, String subcat3
            , String town, String area, String address, String number, String start, String end, String avail, String price, String postcode);
    public void printResponse(Response response);
    public ArrayList<SingleEventEntity> fullTextSearch(Client client, String query, int priceLimit, String dateFrom, String dateTo, Integer distance, double lat, double lon);
    public void closeClient(RestClient restClient);
    public void closeClient(Client restClient);
    public void createIndex(Client client);

}

