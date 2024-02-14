package com.green.gogiro.community.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class CommunityPicsInsVo {
    private int iboard;
    private List<String> pics;
}
