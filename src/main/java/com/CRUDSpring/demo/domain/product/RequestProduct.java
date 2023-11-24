package com.CRUDSpring.demo.domain.product;


import org.antlr.v4.runtime.misc.NotNull;

public record RequestProduct(
        String id,
        @NotNull
        String name,
        @NotNull
        Integer price) {

}
