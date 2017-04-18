package com.solt_inc.model.dto;

import java.io.Serializable;

import com.solt_inc.model.entity.FollowEntity;

public class FollowDto implements Serializable {

    private FollowEntity followEntity;

    public FollowEntity getFollowEntity() {
        return followEntity;
    }

    public void setFollowEntity(FollowEntity followEntity) {
        this.followEntity = followEntity;
    }
}
