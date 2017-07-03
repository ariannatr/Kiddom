package kiddom.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kiddom.model.SingleEventEntity;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by Christos on 3/7/2017.
 */
@Service("elasticService")
public class ElasticServiceImpl implements ElasticService {

    @Override
    public Client getTransportClient(){

        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        }
        catch (Exception e){
            System.out.println("Exception at PreBuiltTransportClient");
            System.out.println(e.getMessage());
        }

        return client;
    }

    @Override
    public void createRecord(Client client, String id, String name, String date, String descr, String cat, String subcat1, String subcat2, String subcat3
            , String town, String area, String address, String number, String start, String end, String avail, String price, String postcode){

        try {
            IndexResponse response = client.prepareIndex("index", "event", id)
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("name", name)
                            .field("date", date)
                            .field("description", descr)
                            .field("category", cat)
                            .field("subCat1", subcat1)
                            .field("subCat2", subcat2)
                            .field("subCat3", subcat3)
                            .field("town", town)
                            .field("area", area)
                            .field("address", address)
                            .field("number", number)
                            .field("start_time", start)
                            .field("end_time", end)
                            .field("availability", avail)
                            .field("price", price)
                            .field("postcode", postcode)
                            .endObject()
                    ).get();
        }
        catch (Exception e){
            System.out.println("Exception at restClient.prepareIndex");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public SingleEventEntity getEntry(Client client, String id){
        GetResponse response;
        Map<String, String> result = new HashMap<String, String>();
        ObjectMapper mapper = new ObjectMapper();
        SingleEventEntity singleEvent = null;

        try {
            response = client.prepareGet("index", "event", id).get();
            result = mapper.readValue(response.getSourceAsString(), new TypeReference<HashMap<String, String>>(){});

            singleEvent.setName(result.get("name"));
            singleEvent.setDescription(result.get("description"));
            singleEvent.setCategory(result.get("category"));
            singleEvent.setSub1(result.get("subCat1"));
            singleEvent.setSub2(result.get("subCat2"));
            singleEvent.setSub3(result.get("subCat3"));
            singleEvent.setArea(result.get("area"));
            singleEvent.setTown(result.get("town"));
            singleEvent.setPostcode(Integer.parseInt(result.get("postcode")));
            singleEvent.setNumber(Integer.parseInt(result.get("number")));
            singleEvent.setPrice(Integer.parseInt(result.get("price")));

            singleEvent.setPhotos(result.get("photos"));
            singleEvent.setRating(Float.parseFloat(result.get("rating")));
            singleEvent.setRatings_sum(Float.parseFloat(result.get("rating_sum")));
        }
        catch (Exception e){
            System.out.println("Exception at restClient.performRequest()");
            System.out.println(e.getMessage());
        }


        return singleEvent;
    }

    @Override
    public void deleteIndex(RestClient restClient){
        try{
            Response response = restClient.performRequest("DELETE", "/index", Collections.singletonMap("pretty", "true"));
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
        catch (Exception e){
            System.out.println("Exception at restClient.performRequest()");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteRecord(Client restClient, String id){
        try{
            DeleteResponse response = restClient.prepareDelete("index", "event", id).get();
        }
        catch (Exception e){
            System.out.println("Exception at restClient.prepareDelete()");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateRecord(Client restClient, String id,String name, String date, String descr, String cat, String subcat1, String subcat2, String subcat3
            , String town, String area, String address, String number, String start, String end, String avail, String price, String postcode){

        try {
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index("index");
            updateRequest.type("event");
            updateRequest.id(id);
            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("name", name)
                    .field("date", date)
                    .field("description", descr)
                    .field("category", cat)
                    .field("subCat1", subcat1)
                    .field("subCat2", subcat2)
                    .field("subCat3", subcat3)
                    .field("town", town)
                    .field("area", area)
                    .field("address", address)
                    .field("number", number)
                    .field("start_time", start)
                    .field("end_time", end)
                    .field("availability", avail)
                    .field("price", price)
                    .field("postcode", postcode)
                    .endObject());
            restClient.update(updateRequest).get();
        }
        catch (Exception e){
            System.out.println("Exception at update");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void printResponse(Response response){

        String s;
        try{

            s = EntityUtils.toString(response.getEntity());
            //System.out.println(EntityUtils.toString(response.getEntity()));
            System.out.println(s);
        }
        catch (Exception e){
            System.out.println("EntityUtils.toString(response.getEntity()");
            System.out.println(e.getMessage());
        }

    }


    @Override
    public ArrayList<SingleEventEntity> fullTextSearch(Client client, String query, int priceLimit, String dateFrom, String dateTo, Integer distance, double lat, double lon){

        SearchResponse r;
        Map<String, String> result = new HashMap<String, String>();
        ArrayList<SingleEventEntity> resultList = new ArrayList<>();
        SingleEventEntity singleEvent = null;
        RangeQueryBuilder filter = null;
        RangeQueryBuilder filter2 = null;
        GeoDistanceQueryBuilder filter3 = null;

        if(priceLimit != -1)
            filter = QueryBuilders.rangeQuery("price").to(priceLimit);
        if(!dateFrom.isEmpty())
            filter2 = QueryBuilders.rangeQuery("date").from(dateFrom).to(dateTo);
        if(distance != -1)
            filter3 = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance.toString(), DistanceUnit.KILOMETERS);

        try {
            r = client.prepareSearch("index")
                    .setTypes("event")
                    .setQuery(QueryBuilders.multiMatchQuery(query, "name", "category", "description", "subCat1", "subCat2", "subCat3").analyzer("search").fuzziness("AUTO").prefixLength(2))                 // Query
                    .setPostFilter(filter).setPostFilter(filter2).setPostFilter(filter3)     // Filter
                    //.setFrom(0).setSize(60).setExplain(true)
                    .get();



            SearchHit[] searchHits = r.getHits().getHits();
            ObjectMapper mapper = new ObjectMapper();

            int length = searchHits.length;

            for (SearchHit hit:searchHits) {
                result = mapper.readValue(hit.getSourceAsString(), new TypeReference<HashMap<String, String>>(){});

                singleEvent.setName(result.get("name"));
                singleEvent.setDescription(result.get("description"));
                singleEvent.setCategory(result.get("category"));
                singleEvent.setSub1(result.get("subCat1"));
                singleEvent.setSub2(result.get("subCat2"));
                singleEvent.setSub3(result.get("subCat3"));
                singleEvent.setArea(result.get("area"));
                singleEvent.setTown(result.get("town"));
                singleEvent.setPostcode(Integer.parseInt(result.get("postcode")));
                singleEvent.setNumber(Integer.parseInt(result.get("number")));
                singleEvent.setPrice(Integer.parseInt(result.get("price")));

                singleEvent.setPhotos(result.get("photos"));
                singleEvent.setRating(Float.parseFloat(result.get("rating")));
                singleEvent.setRatings_sum(Float.parseFloat(result.get("rating_sum")));

                resultList.add(singleEvent);
            }
        }
        catch (Exception e){
            System.out.println("Exception at restClient.performRequest()");
            System.out.println(e.getMessage());
        }

        return resultList;
    }

    @Override
    public void closeClient(RestClient restClient){
        try {
            restClient.close();
        }
        catch (Exception e){
            System.out.println("Exception at restClient.close()");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void closeClient(Client client){
        try {
            client.close();
        }
        catch (Exception e){
            System.out.println("Exception at restClient.close()");
            System.out.println(e.getMessage());
        }
    }

    @Override
    @Deprecated
    public void createIndex(Client client){

        XContentBuilder settingsBuilder = null;
        try {
            settingsBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("number_of_shards", 1)
                    .field("number_of_replicas", 0)
                    .startObject("analysis")
                    .startObject("filter")
                    .startObject("greek_stop")
                    .field("type", "stop")
                    .field("stopwords","_greek_")
                    .endObject()
                    .startObject("greek_lowercase")
                    .field("type", "lowercase")
                    .field("language", "greek")
                    .endObject()
                    .startObject("greek_stemmer")
                    .field("type", "stemmer")
                    .array("language", "greek")
                    .endObject()
                    .startObject("ngram_filter")
                    .field("type", "nGram")
                    .field("min_gram", 4)
                    .field("max_gram", 4)
                    .endObject()
                    .endObject()
                    .startObject("analyzer")
                    .startObject("greek")
                    .field("type", "custom")
                    .field("tokenizer", "icu_tokenizer")
                    .array("filter", "greek_lowercase", "greek_stop", "greek_stemmer", "icu_folding", "ngram_filter")
                    .endObject()
                    .startObject("search")
                    .field("type", "custom")
                    .field("tokenizer", "icu_tokenizer")
                    .array("filter", "greek_lowercase", "greek_stop", "greek_stemmer", "icu_folding")
                    .endObject()
                    .endObject()
                    .endObject()
                    .startObject("mappings")
                    .startObject("event")
                    .startObject("properties")
                    .startObject("name")
                    .field("type", "text")
                    .field("term_vector", "yes")
                    .field("analyzer", "greek")
                    .endObject()
                    .startObject("date")
                    .field("type", "date")
                    .field("format", "yyyy-MM-dd")
                    .endObject()
                    .startObject("description")
                    .field("type", "text")
                    .field("term_vector", "yes")
                    .field("analyzer", "greek")
                    .endObject()
                    .startObject("category")
                    .field("type", "text")
                    .field("term_vector", "yes")
                    .field("analyzer", "greek")
                    .endObject()
                    .startObject("subCat1")
                    .field("type", "text")
                    .field("analyzer", "greek")
                    .endObject()
                    .startObject("subCat2")
                    .field("type", "text")
                    .field("analyzer", "greek")
                    .endObject()
                    .startObject("subCat3")
                    .field("type", "text")
                    .field("analyzer", "greek")
                    .endObject()
                    .startObject("town")
                    .field("type", "text")
                    .field("analyzer", "greek")
                    .endObject()
                    .startObject("area")
                    .field("type", "text")
                    .field("analyzer", "greek")
                    .endObject()
                    .startObject("address")
                    .field("type", "text")
                    .field("analyzer", "greek")
                    .endObject()
                    .startObject("number")
                    .field("type", "integer")
                    .endObject()
                    .startObject("postcode")
                    .field("type", "Integer")
                    .endObject()
                    .startObject("start_time")
                    .field("type", "text")
                    .endObject()
                    .startObject("end_time")
                    .field("type", "text")
                    .endObject()
                    .startObject("availability")
                    .field("type", "integer")
                    .endObject()
                    .startObject("price")
                    .field("type", "integer")
                    .endObject()
                    .startObject("photos")
                    .field("type", "text")
                    .endObject()
                    .startObject("rating")
                    .field("type", "float")
                    .endObject()
                    .startObject("rating_sum")
                    .field("type", "float")
                    .endObject()
                    .startObject("location")
                    .field("type", "geo_point")
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject();

        }
        catch (Exception e){
            System.out.println("Exception at restClient.performRequest()");
            System.out.println(e.getMessage());
        }

        IndicesAdminClient indicesAdminClient = client.admin().indices();

        client.admin().indices().prepareCreate("index")
                .setSettings(settingsBuilder)
                .get();
    }
}
