package com.example.restaurantapii.dto;

public class PlaceRestDTO {
    private Long id;
    private String name;
    private Long tablePiece;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTablePiece() {
        return tablePiece;
    }

    public void setTablePiece(Long tablePiece) {
        this.tablePiece = tablePiece;
    }
}
