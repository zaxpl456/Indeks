package com.projekt.wirtualny_indeks.index.services;


import com.projekt.wirtualny_indeks.index.models.DocumentType;
import com.projekt.wirtualny_indeks.index.repositories.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentType> getAllDocumentsTypes() {
        return documentTypeRepository.findAll();
    }

    @Override
    public DocumentType getDocumentType(int id) {
        return documentTypeRepository.findById(id).get();
    }

    @Override
    public void deleteDocumentType(int id) {
        documentTypeRepository.deleteById(id);
    }

    @Override
    public void saveDocumentType(DocumentType documentType) {
        documentTypeRepository.save(documentType);

    }
}
