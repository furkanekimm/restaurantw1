package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.PlaceRestDTO;

public class PlaceRestDTOBuilder extends Builder{
    private String name;
    private Long tablePiece;


    @Override
    public PlaceRestDTO build() {
        PlaceRestDTO placeRestDTO = new PlaceRestDTO();
        placeRestDTO.setId(super.id);
        placeRestDTO.setName(this.name);
        placeRestDTO.setTablePiece(this.tablePiece);
        return placeRestDTO;
    }

    public PlaceRestDTOBuilder id(Long id){
        super.id=id;
        return this;
    }

    public PlaceRestDTOBuilder name(String name){
        this.name=name;
        return this;
    }

    public PlaceRestDTOBuilder tablePiece(Long tablePiece){
        this.tablePiece=tablePiece;
        return this;
    }
}
