package com.example.restaurantapii.services;

import com.example.restaurantapii.dto.InfoProfileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoService {

    @Value("${"+ InfoProfileDTO.SERVERPORT+"}")
    public String serverPort;

    @Value("${"+InfoProfileDTO.H2ENABLED+"}")
    public String h2enabled;

    @Value("${"+InfoProfileDTO.DATASOURCE+"}")
    public String dataSource;

    @Value("${"+InfoProfileDTO.SHOWSQL+"}")
    public String showSql;

    @Value("${"+InfoProfileDTO.HIBERNATEFORMATSQL+"}")
    public String hibernateFormatSql;

    @Value("${"+InfoProfileDTO.LOGGING+"}")
    public String logging;

    @Value("${"+InfoProfileDTO.ACTIVEPROF+"}")
    public String activeProf;

    public List<InfoProfileDTO> getServerPort(){
        List<InfoProfileDTO> infoList = new ArrayList<>();
        InfoProfileDTO infoProfileDTO = new InfoProfileDTO();
        infoProfileDTO.setKey(InfoProfileDTO.SERVERPORT);
        infoProfileDTO.setValue(serverPort);
        infoList.add(infoProfileDTO);

        infoProfileDTO=new InfoProfileDTO();
        infoProfileDTO.setKey(InfoProfileDTO.H2ENABLED);
        infoProfileDTO.setValue(h2enabled);
        infoList.add(infoProfileDTO);

        infoProfileDTO=new InfoProfileDTO();
        infoProfileDTO.setKey(InfoProfileDTO.DATASOURCE);
        infoProfileDTO.setValue(dataSource);
        infoList.add(infoProfileDTO);

        infoProfileDTO=new InfoProfileDTO();
        infoProfileDTO.setKey(InfoProfileDTO.SHOWSQL);
        infoProfileDTO.setValue(showSql);
        infoList.add(infoProfileDTO);

        infoProfileDTO=new InfoProfileDTO();
        infoProfileDTO.setKey(InfoProfileDTO.HIBERNATEFORMATSQL);
        infoProfileDTO.setValue(hibernateFormatSql);
        infoList.add(infoProfileDTO);

        infoProfileDTO=new InfoProfileDTO();
        infoProfileDTO.setKey(InfoProfileDTO.LOGGING);
        infoProfileDTO.setValue(logging);
        infoList.add(infoProfileDTO);

        infoProfileDTO=new InfoProfileDTO();
        infoProfileDTO.setKey(InfoProfileDTO.ACTIVEPROF);
        infoProfileDTO.setValue(activeProf);
        infoList.add(infoProfileDTO);
        return infoList;
    }
}
