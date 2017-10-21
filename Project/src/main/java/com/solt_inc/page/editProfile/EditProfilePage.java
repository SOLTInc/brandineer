package com.solt_inc.page.editProfile;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.solt_inc.model.dao.HobbyDao;
import com.solt_inc.model.dao.HobbyImageDao;
import com.solt_inc.model.dao.SkillSetDao;
import com.solt_inc.model.dao.SkillsetImageDao;
import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.dto.HobbyDto;
import com.solt_inc.model.dto.SkillSetDto;
import com.solt_inc.model.dto.UserDto;
import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.HobbyImageEntity;
import com.solt_inc.model.entity.SkillSetEntity;
import com.solt_inc.model.entity.SkillSetImageEntity;
import com.solt_inc.model.entity.UserEntity;
import com.solt_inc.page.editProfile.editPanel.hobby.EditHobbyPanel;
import com.solt_inc.page.editProfile.editPanel.profile.EditUserPanel;
import com.solt_inc.page.editProfile.editPanel.skillSet.EditSkillSetPanel;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.registrationProfile.inputPanel.profile.ProfileInputPanel;
import com.solt_inc.page.profilePage.panel.profile.UserPanel;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

 @AuthorizeInstantiation("USER")
public class EditProfilePage extends WebPage {
    private static final long serialVersionUID = 7514382722447820L;

    private IModel<UserDto> userModel = Model.of(new UserDto());
    private IModel<List<HobbyDto>> hobbyListModel = new ListModel<HobbyDto>(new ArrayList<HobbyDto>());
    private IModel<List<SkillSetDto>> skillSetListModel = new ListModel<SkillSetDto>(new ArrayList<SkillSetDto>());

    public EditProfilePage(IModel<Integer> userId) {
        UserPanel userPanel = new UserPanel("userPanel", userId);
        add(userPanel);

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        EditUserPanel editUserPanel = new EditUserPanel("profilePanel", userModel, userId);
        EditSkillSetPanel editSkillSetPanel = new EditSkillSetPanel("skillSetPanel", skillSetListModel, userId);
        EditHobbyPanel editHobbyPanel = new EditHobbyPanel("hobbyPanel", hobbyListModel, userId);
        Button editButton = new Button("editButton");
        Form<?> form = new Form<Void>("form") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {

            boolean updateFlg = true;

            if (updateFlg) {
            	System.out.println("updateUser");
            	updateFlg = updateUser(userId.getObject(), userModel.getObject());
            	System.out.println(updateFlg);
            }

            if (updateFlg && skillSetListModel.getObject() != null) {
            	System.out.println("updateSkillSet");
            	updateFlg = updateSkillSet(userId.getObject(), skillSetListModel.getObject());

            	System.out.println(updateFlg);
            }

            if (updateFlg && hobbyListModel.getObject() != null) {
            	System.out.println("updateHobby");
            	updateFlg = updateHobby(userId.getObject(), hobbyListModel.getObject());
            	System.out.println(updateFlg);
           }
            if (updateFlg) {
                setResponsePage(HomePage.class);
            } else {
                error("insert error");
            }

        }

    };
        Button cancelButton = new Button("cancelButton") {
            @Override
            public void onSubmit() {
                setResponsePage(HomePage.class);
            }
        };

        add(feedbackPanel);
        add(form);

        form.add(editUserPanel);
        form.add(editSkillSetPanel);
        form.add(editHobbyPanel);

        form.add(editButton);
        cancelButton.setDefaultFormProcessing(false);
        form.add(cancelButton);
    }
    
    private boolean updateUser(int userId, UserDto userDto) {
    	boolean updateFlg = true;

        UserDao userDao = new UserDao();

        UserEntity userEntity = userDto.getUserEntity();
        updateFlg = userDao.update(userId, userEntity);

        return updateFlg;
    }
    
    private boolean updateSkillSet(int userId, List<SkillSetDto> skillsetDtoList) {

    	boolean updateFlg = true;

        SkillSetDao skillSetDao = new SkillSetDao();
        
        skillSetDao.delete(userId);

        for (SkillSetDto skillSetDto : skillsetDtoList) {
            SkillSetEntity skillSetEntity = skillSetDto.getSkillSetEntity();
            if (skillSetDto.getProcessStartEntity() != null) {
                int processStart = skillSetDto.getProcessStartEntity().getId();
                skillSetEntity.setProcessStart(processStart);
            }
            if (skillSetDto.getProcessEndEntity() != null) {
                int processEnd = skillSetDto.getProcessEndEntity().getId();
                skillSetEntity.setProcessEnd(processEnd);
            }
            if(skillSetEntity.getId() != 0) {
                updateFlg = skillSetDao.update(skillSetEntity.getId(), skillSetEntity);
            } else {
            	updateFlg = skillSetDao.insert(userId, skillSetEntity);
            }
            if(!updateFlg) {
            	System.out.println("skillset can not update");
            	break;	
            }
            int skillsetId = skillSetDao.getSkillSetId(skillSetEntity.getProjectName());

            List<SkillSetImageEntity> skillsetImageEntityList = skillSetDto.getSkillSetImageEntityList();
            if(skillsetId != -1 && skillsetImageEntityList.size() != 0 && updateFlg) {
            	SkillsetImageDao skillsetImageDao = new SkillsetImageDao();
            	skillsetImageDao.delete(skillsetId);
            	for(SkillSetImageEntity entity: skillsetImageEntityList) {
                    updateFlg = skillsetImageDao.insert(skillsetId, entity);
                    if(!updateFlg) {
                    	System.out.println("sillsetImage can not update");
                    	break;
                    }
                }
            }

        }
        return updateFlg;
   	
    }
    
    private boolean updateHobby(int userId, List<HobbyDto> hoobbyDtoList) {
    	boolean updateFlg = true;

        HobbyDao hobbyDao = new HobbyDao();
        
        hobbyDao.delete(userId);

        for(HobbyDto hobbyDto : hobbyListModel.getObject()) {
            HobbyEntity hobbyEntity = hobbyDto.getHobbyEntity();

            if(hobbyEntity.getId() != 0) {
                updateFlg = hobbyDao.update(hobbyEntity.getId(), hobbyEntity);
            } else {
            	updateFlg = hobbyDao.insert(userId, hobbyEntity);
            }
        	if(!updateFlg) {
        		System.out.println("hobby can not update");
        		break;
        	}
 
            int hobbyId = hobbyDao.getHobbyId(hobbyEntity.getHobbyName());
            if (hobbyId != -1 && updateFlg && hobbyDto.getHobbyImageEntityList() != null) {

                HobbyImageDao hobbyImageDao = new HobbyImageDao();
            	hobbyImageDao.delete(hobbyId);
                List<HobbyImageEntity> hobbyImageList = hobbyDto.getHobbyImageEntityList();
                for (HobbyImageEntity hobbyImage : hobbyImageList) {
                    updateFlg = hobbyImageDao.insert(hobbyId, hobbyImage);
                    if(!updateFlg) {
                       	System.out.println("hobbyImage can not update");
                       	break;
                    }
                }
            }
        }
        return updateFlg;
    }
}
