package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryRecord(
        Integer idCategory,
        String nameCategoryRecord,
        String descriptionCategoryRecord,
        boolean enabledCategoryRecord
) {
}
