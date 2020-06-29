package com.example.demo.documentation;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class UserDocumentation {
    public static RestDocumentationResultHandler getUsers() {
        return document("user/getAll"
//                responseFields(
//                        fieldWithPath("[].nickname").type(JsonFieldType.STRING).description("This is nickname"),
//                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("This is name"),
//                        fieldWithPath("[].email").type(JsonFieldType.STRING).description("This is email")
//                )
        );
    }
}
