package com.example.demo.documentation;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class UserDocumentation {

    static final String base = "user/";
    public static RestDocumentationResultHandler getUsers() {
        return document(base+"getAll"
//                responseFields(
//                        fieldWithPath("[].nickname").type(JsonFieldType.STRING).description("This is nickname"),
//                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("This is name"),
//                        fieldWithPath("[].email").type(JsonFieldType.STRING).description("This is email")
//                )
        );
    }

    public static RestDocumentationResultHandler getByNickname(){
        return document(base+"getByNickname");
    }

    public static RestDocumentationResultHandler getByName(){
        return document(base+"getByName");
    }
    public static RestDocumentationResultHandler insertUser(){
        return document(base+"insert");
    }

    public static RestDocumentationResultHandler deleteUser(){
        return document(base+"delete");
    }

    public static RestDocumentationResultHandler modifyUser(){
        return document(base+"modify");
    }

}
