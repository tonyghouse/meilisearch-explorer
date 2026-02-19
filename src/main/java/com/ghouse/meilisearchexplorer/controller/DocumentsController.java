package com.ghouse.meilisearchexplorer.controller;

import com.ghouse.meilisearchexplorer.util.JsonFormatterUtil;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.Results;
import com.meilisearch.sdk.model.SearchResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class DocumentsController {

    @Autowired
    private Client client;


    //@GetMapping("/documents")
    public void addDocuments() {
        JSONArray array = new JSONArray();
        ArrayList items = new ArrayList() {{
            add(new JSONObject().put("id", "1").put("title", "Carol").put("genres", new JSONArray("[\"Romance\",\"Drama\"]")));
            add(new JSONObject().put("id", "2").put("title", "Wonder Woman").put("genres", new JSONArray("[\"Action\",\"Adventure\"]")));
            add(new JSONObject().put("id", "3").put("title", "Life of Pi").put("genres", new JSONArray("[\"Adventure\",\"Drama\"]")));
            add(new JSONObject().put("id", "4").put("title", "Mad Max: Fury Road").put("genres", new JSONArray("[\"Adventure\",\"Science Fiction\"]")));
            add(new JSONObject().put("id", "5").put("title", "Moana").put("genres", new JSONArray("[\"Fantasy\",\"Action\"]")));
            add(new JSONObject().put("id", "6").put("title", "Philadelphia").put("genres", new JSONArray("[\"Drama\"]")));
        }};

        array.put(items);
        String documents = array.getJSONArray(0).toString();

        Index index = client.index("movies");

        index.addDocuments(documents);
    }

    @GetMapping("/documents/{indexName}")
    public String getDocuments(@PathVariable String indexName) {
        Index index = client.index(indexName);

        String documents = index.getRawDocuments();
        return JsonFormatterUtil.formatJson(documents);
    }

    @GetMapping("/documents/{indexName}/search")
    public String searchDocument(@PathVariable String indexName, @RequestParam String value) {
        Index index = client.index(indexName);

        SearchResult result = index.search(value);
        ArrayList<HashMap<String, Object>> hits = result.getHits();

        return hits.toString();
    }

}
