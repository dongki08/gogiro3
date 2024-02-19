package com.green.gogiro.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CommunityInsFavDto {
    @JsonIgnore
    private int iuser;
    private int iboard;
}
