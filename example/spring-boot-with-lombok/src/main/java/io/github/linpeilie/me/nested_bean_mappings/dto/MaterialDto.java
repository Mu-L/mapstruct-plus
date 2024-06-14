/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.github.linpeilie.me.nested_bean_mappings.dto;

import lombok.Data;

@Data
public class MaterialDto {

    private String manufacturer;
    private MaterialTypeDto materialType;

}
