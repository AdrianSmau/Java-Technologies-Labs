package com.example.lab9.controllers;

import com.example.lab9.entities.Document;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
@RolesAllowed({"admin", "reviewer", "author", "user"})
public class JaxRsController {
    @Inject
    private DocumentController documentController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<Document> getDocuments() {
        return documentController.getDocuments();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("admin")
    public Document getDocument(@PathParam("id") int id) {
        return documentController.getDocument(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("author")
    public void add(Document document) {
        documentController.addDocumentJax(document);
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "reviewer"})
    public void update(Document document) {
        documentController.updateDocumentJax(document);
    }

    @DELETE
    @RolesAllowed({"admin", "reviewer"})
    public void delete(@QueryParam("id") int id) {
        documentController.deleteDocumentJax(id);
    }
}
