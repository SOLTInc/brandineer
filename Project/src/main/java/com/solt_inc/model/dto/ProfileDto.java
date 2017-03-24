package com.solt_inc.model.dto;

import java.io.Serializable;
import java.util.List;

public class ProfileDto implements Serializable {

    private UserDto userDto;
    private List<HobbyDto> hobbyList;
    private List<SkillSetDto> skillSetList;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<HobbyDto> getHobbyList() {
        return this.hobbyList;
    }

    public void setHobbyList(HobbyDto hobbyDto) {

        this.hobbyList.add(hobbyDto);
    }

    public List<SkillSetDto> getSkillList() {
        return this.skillSetList;
    }

    public void setSkillSetList(SkillSetDto skillSetDto) {

        this.skillSetList.add(skillSetDto);
    }
}
