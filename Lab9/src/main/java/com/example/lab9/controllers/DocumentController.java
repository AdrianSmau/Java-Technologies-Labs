package com.example.lab9.controllers;

import com.example.lab9.annotations.Loggable;
import com.example.lab9.annotations.RegistrationNumber;
import com.example.lab9.entities.Document;
import com.example.lab9.entities.User;
import com.example.lab9.entities.UserRoles;
import com.example.lab9.events.Subject;
import com.example.lab9.repositories.DocumentRepository;
import com.example.lab9.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Named("DocumentController")
@ApplicationScoped
@Transactional
public class DocumentController implements BusinessHoursRestrictedSubmission {
    @Inject
    private DocumentRepository documentRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private Subject subject;

    @Inject
    @RegistrationNumber
    private String generatedRegistrationNumber;

    private String documentContent;
    private String documentName;
    private String additionalAuthors;
    private int documentId;

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    @Override
    @Loggable
    public void addDocument() throws IOException {
        if (documentContent == null || documentContent.length() < 1 || documentName == null || documentName.length() < 1) {
            System.err.println("Invalid Document!");
            return;
        }
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] cookies = request.getCookies();
        String username = null;
        String[] authorsString = additionalAuthors.split(",");
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currentUsername")) {
                    username = cookie.getValue();
                }
            }
        }
        List<String> authors = new ArrayList<>();
        Optional<User> optUser = userRepository.getByUsername(username);
        if (optUser.isPresent()) {
            authors.add(username);
            if (optUser.get().getRole() != UserRoles.AUTHOR) {
                System.err.println("You are not logged in or you are not an Author!");
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                return;
            }
            for (String authorUsername : authorsString) {
                if (userRepository.getByUsername(authorUsername).isPresent()) {
                    authors.add(authorUsername);
                }
                documentRepository.save(new Document(documentName, String.join(",", authors), documentContent, generatedRegistrationNumber));
                subject.documentAdded(documentName);
                FacesContext.getCurrentInstance().getExternalContext().redirect("author.xhtml");
            }
        } else {
            System.err.println("You are not logged in or you are not an Author!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

    public void addDocumentJax(Document document) {
        if (document.getContent() == null || document.getContent().length() < 1 || document.getName() == null || document.getName().length() < 1) {
            System.err.println("Invalid Document!");
            return;
        }
        String[] authorsString = document.getAuthors().split(",");
        List<String> authors = new ArrayList<>();
        Collections.addAll(authors, authorsString);
        documentRepository.save(new Document(document.getName(), String.join(",", authors), document.getContent(), document.getRegistrationNumber()));
    }

    public void deleteDocument(int id) throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currentUsername")) {
                    username = cookie.getValue();
                }
            }
        }
        if (username != null && username.length() > 0) {
            Optional<User> optUser = userRepository.getByUsername(username);
            if (optUser.isPresent() && optUser.get().getRole() == UserRoles.REVIEWER) {
                documentRepository.delete(id);
            } else {
                System.err.println("No permissions to do such thing!");
            }
        } else {
            System.err.println("Log in before doing so!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            return;
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("reviewer.xhtml");
    }

    public void deleteDocumentJax(int id) {
        documentRepository.delete(id);
    }

    public void updateDocument() throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] cookies = request.getCookies();
        String username = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currentUsername")) {
                    username = cookie.getValue();
                }
            }
        }
        if (username != null && username.length() > 0) {
            Optional<User> optUser = userRepository.getByUsername(username);
            if (optUser.isPresent() && optUser.get().getRole() == UserRoles.REVIEWER) {
                documentRepository.update(documentName, documentContent, documentId);
            } else {
                System.err.println("No permissions to do such thing!");
            }
        } else {
            System.err.println("Log in before doing so!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            return;
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("reviewer.xhtml");
    }

    public void updateDocumentJax(Document document) {
        documentRepository.update(document.getName(), document.getContent(), document.getId());
    }

    public List<Document> getDocuments() {
        return documentRepository.getAll();
    }

    public Document getDocument(int id) {
        return documentRepository.getAll().get(id);
    }

    public String getAdditionalAuthors() {
        return additionalAuthors;
    }

    public void setAdditionalAuthors(String additionalAuthors) {
        this.additionalAuthors = additionalAuthors;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
