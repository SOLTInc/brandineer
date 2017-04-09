package com.solt_inc.page.registrationProfile;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.solt_inc.model.dao.HobbyDao;
import com.solt_inc.model.dao.HobbyImageDao;
import com.solt_inc.model.dao.SkillSetDao;
import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.dto.HobbyDto;
import com.solt_inc.model.dto.SkillSetDto;
import com.solt_inc.model.dto.UserDto;
import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.HobbyImageEntity;
import com.solt_inc.model.entity.SkillSetEntity;
import com.solt_inc.model.entity.UserEntity;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.registrationProfile.inputPanel.hobby.HobbyInputPanel;
import com.solt_inc.page.registrationProfile.inputPanel.profile.ProfileInputPanel;
import com.solt_inc.page.registrationProfile.inputPanel.skillSet.SkillSetInputPanel;

public class RegistrationUserPage extends WebPage {
    private static final long serialVersionUID = 7514382722447820L;

    private IModel<UserDto> userModel = Model.of(new UserDto());
    private IModel<List<HobbyDto>> hobbyListModel = new ListModel<HobbyDto>(new ArrayList<HobbyDto>());
    private IModel<List<SkillSetDto>> skillSetListModel = new ListModel<SkillSetDto>(new ArrayList<SkillSetDto>());
    private FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
    private ProfileInputPanel profileInputPanel = new ProfileInputPanel("profilePanel", userModel);
    private SkillSetInputPanel skillSetInputPanel = new SkillSetInputPanel("skillSetPanel", skillSetListModel);
    private HobbyInputPanel hobbyInputPanel = new HobbyInputPanel("hobbyPanel", hobbyListModel);
    private Button registerButton = new Button("register");
    private Form<?> form = new Form<Void>("form") {

        private static final long serialVersionUID = 1L;

        @Override
        public void onSubmit() {

            boolean insertFlg = true;
            int userId = 0;

            if (insertFlg) {
                UserDao userDao = new UserDao();
                // userModel = profileInputPanel.getUserModel();
                UserDto userDto = userModel.getObject();
                UserEntity userEntity = userDto.getUserEntity();
                insertFlg = userDao.insert(userEntity);
                userId = userDao.getUserId(userEntity.getFirstName());
            }

            if (insertFlg && userId != 0 && skillSetListModel.getObject() != null) {
                SkillSetDao skillSetDao = new SkillSetDao();
                for (SkillSetDto skillSetDto : skillSetListModel.getObject()) {
                    SkillSetEntity skillSetEntity = skillSetDto.getSkillSetEntity();
                    if (skillSetDto.getProcessStartEntity() != null) {
                        int processStart = skillSetDto.getProcessStartEntity().getId();
                        skillSetEntity.setProcessStart(processStart);
                    }
                    if (skillSetDto.getProcessEndEntity() != null) {
                        int processEnd = skillSetDto.getProcessEndEntity().getId();
                        skillSetEntity.setProcessEnd(processEnd);
                    }
                    insertFlg = skillSetDao.insert(userId, skillSetEntity);
                }
            }

            if (insertFlg && userId != 0 && hobbyListModel.getObject() != null) {
                HobbyDao hobbyDao = new HobbyDao();
                HobbyImageDao hobbyImageDao = new HobbyImageDao();
                for (HobbyDto hobbyDto : hobbyListModel.getObject()) {
                    HobbyEntity hobbyEntity = hobbyDto.getHobbyEntity();
                    insertFlg = hobbyDao.insert(userId, hobbyEntity);
                    int hobbyId = hobbyDao.getHobbyId(hobbyEntity.getHobbyName());
                    if (hobbyId != -1 && insertFlg && hobbyDto.getHobbyImageEntityList() != null) {
                        List<HobbyImageEntity> hobbyImageList = hobbyDto.getHobbyImageEntityList();
                        for (HobbyImageEntity hobbyImage : hobbyImageList) {
                            insertFlg = hobbyImageDao.insert(hobbyId, hobbyImage);
                        }
                    }
                }
            }
            if (insertFlg) {
                setResponsePage(HomePage.class);
            } else {
                error("insert error");
            }

        }

    };

    private Button cancelButton = new Button("cancel") {
        @Override
        public void onSubmit() {
            setResponsePage(HomePage.class);
        }

    };

    public RegistrationUserPage(PageParameters params) {

        add(feedbackPanel);
        add(form);

        form.add(profileInputPanel);
        form.add(skillSetInputPanel);
        form.add(hobbyInputPanel);

        form.add(registerButton);
        cancelButton.setDefaultFormProcessing(false);
        form.add(cancelButton);

    }

}