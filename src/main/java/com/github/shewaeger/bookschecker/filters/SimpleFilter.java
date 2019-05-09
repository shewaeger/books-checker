package com.github.shewaeger.bookschecker.filters;

import lombok.Data;

@Data
public class SimpleFilter {
    String search;

    public String anySearch(){
        return "%" + search + "%";
    }
}
