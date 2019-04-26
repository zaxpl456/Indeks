package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Document;

import java.util.List;

public interface DocumentService {

    List<Document> getAllDocuments();


    Document getDocument(int id);

    void deleteDocument(int id);

    void saveDocument(Document document);
}
