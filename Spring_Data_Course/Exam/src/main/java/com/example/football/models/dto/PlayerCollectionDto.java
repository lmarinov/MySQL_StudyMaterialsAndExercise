package com.example.football.models.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerCollectionDto {

    @XmlElement(name = "players")
    @Expose
    private List<PlayerCreateDto> players;

    public PlayerCollectionDto() {
    }

    public List<PlayerCreateDto> getPlayers() {
        return this.players;
    }

    public void setPlayers(List<PlayerCreateDto> players) {
        this.players = players;
    }
}
