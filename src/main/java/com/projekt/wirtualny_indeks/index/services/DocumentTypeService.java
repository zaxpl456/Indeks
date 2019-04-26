package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.DocumentType;

import java.util.List;

public interface DocumentTypeService {
    List<DocumentType> getAllDocumentsTypes();


    DocumentType getDocumentType(int id);

    void deleteDocumentType(int id);

    void saveDocumentType(DocumentType documentType);
}
