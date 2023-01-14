package com.example.lab8.controllers;

import com.example.lab8.entities.Document;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/api")
public class JaxRsController {
    @Inject
    private DocumentController documentController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document> getDocuments() {
        return documentController.getDocuments();
    }

    @GET
    @Path("/{id}")
    public Document getDocument(@PathParam("id") int id) {
        return documentController.getDocument(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Document document) throws IOException {
        documentController.addDocumentJax(document);
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Document document) throws IOException {
        documentController.updateDocumentJax(document);
    }

    @DELETE
    public void delete(@QueryParam("id") int id) throws IOException {
        documentController.deleteDocument(id);
    }
}
