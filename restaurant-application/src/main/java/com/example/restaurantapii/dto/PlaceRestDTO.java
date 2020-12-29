package com.example.restaurantapii.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlaceRestDTO extends BaseDTO implements Serializable {
    private String name;
    private Long tablePiece;
    private MediaDTO media;
}
