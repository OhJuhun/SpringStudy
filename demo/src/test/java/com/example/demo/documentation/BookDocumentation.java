package com.example.demo.documentation;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

public class BookDocumentation {

    static final String base = "book/";
    public static RestDocumentationResultHandler getBooks(){
        return document(base+"getAll");
    }

    public static RestDocumentationResultHandler insertBook(){
        return document(base+"insert");
    }

    public static RestDocumentationResultHandler getBookByIsbn(){
        return document(base+"getByIsbn");
    }

    public static RestDocumentationResultHandler modifyBook(){
        return document(base+"modify");
    }

    public static RestDocumentationResultHandler deleteBook(){
        return document(base+"delete");
    }
}
