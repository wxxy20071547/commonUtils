package com.kevin.common.clone;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Address implements Serializable{
    private String province;
    private String city;
}
