package com.ghouse.meilisearchexplorer.controller;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private Client meiliClient;

    @GetMapping("/indexes")
    public List<String> getIndexes() {
        System.out.println("Getting Indexes");
        Index[] indexes = meiliClient.getIndexes().getResults();
        List<String> indexNames = new ArrayList<>(indexes.length);
        for (Index index : indexes){
            indexNames.add(index.getUid());
        }
        return indexNames;
    }

    @GetMapping("/indexes/{indexName}")
    public void createIndex(@PathVariable String indexName) {
        JSONArray array = new JSONArray();
        List items = new ArrayList();

        array.put(items);

        String documents = array.getJSONArray(0).toString();

        Index index = meiliClient.index(indexName);
        index.addDocuments(documents);
    }

    @DeleteMapping("/indexes/{indexName}")
    public void deleteIndex(@PathVariable String indexName) {
        meiliClient.deleteIndex(indexName);
    }

}
