package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.MediaDTO;

public class MediaDTOBuilder extends Builder {
    private String name;
    private byte[] fileContent;

    @Override
    public MediaDTO build() {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setName(this.name);
        mediaDTO.setFileContent(this.fileContent);
        mediaDTO.setId(super.id);
        return mediaDTO;
    }

    public MediaDTOBuilder fileContent(byte[] fileContent){
        this.fileContent=fileContent;
        return this;
    }

    public MediaDTOBuilder name(String name){
        this.name=name;
        return this;
    }

    public MediaDTOBuilder id(Long id){
        super.id = id;
        return this;
    }


}
