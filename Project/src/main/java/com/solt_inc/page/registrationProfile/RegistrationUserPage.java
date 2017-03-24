package com.solt_inc.page.registrationProfile;

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
import com.solt_inc.model.dao.SkillSetDao;
import com.solt_inc.model.dao.UserDao;
import com.solt_inc.model.dto.HobbyDto;
import com.solt_inc.model.dto.SkillSetDto;
import com.solt_inc.model.dto.UserDto;
import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.SkillSetEntity;
import com.solt_inc.model.entity.UserEntity;
import com.solt_inc.page.homePage.HomePage;
import com.solt_inc.page.registrationProfile.inputPanel.hobby.HobbyInputPanel;
import com.solt_inc.page.registrationProfile.inputPanel.profile.ProfileInputPanel;
import com.solt_inc.page.registrationProfile.inputPanel.skillSet.SkillSetInputPanel;

public class RegistrationUserPage extends WebPage {
    private static final long serialVersionUID = 7514382722447820L;

    private IModel<UserDto> userModel;
    private IModel<List<HobbyDto>> hobbyListModel;
    private IModel<List<SkillSetDto>> skillSetListModel;
    private Button cancelButton;
    private ProfileInputPanel profileInputPanel;
    private SkillSetInputPanel skillSetInputPanel;
    private HobbyInputPanel hobbyInputPanel;

    public RegistrationUserPage(PageParameters params) {

        add(new FeedbackPanel("feedback"));

        Form<?> form = new Form("form");
        add(form);

        userModel = new Model<UserDto>();
        profileInputPanel = new ProfileInputPanel("profilePanel", userModel);
        form.add(profileInputPanel);

        skillSetListModel = new ListModel<SkillSetDto>();
        skillSetInputPanel = new SkillSetInputPanel("skillSetPanel", skillSetListModel);
        form.add(skillSetInputPanel);

        hobbyListModel = new ListModel<HobbyDto>();
        hobbyInputPanel = new HobbyInputPanel("hobbyPanel", hobbyListModel);
        form.add(hobbyInputPanel);

        form.add(new Button("register") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {

                boolean insertFlg = true;
                int userId = 0;

                if (insertFlg) {
                    UserDao userDao = new UserDao();
                    userModel = profileInputPanel.getUserModel();
                    UserEntity userEntity = userModel.getObject().getUserEntity();
                    insertFlg = userDao.insert(userEntity);
                    userId = userDao.getUserId(userEntity.getFirstName());
                }

                skillSetListModel = skillSetInputPanel.getSkillSetListModel();
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

                hobbyListModel = hobbyInputPanel.getHobbyListModel();
                if (insertFlg && userId != 0 && hobbyListModel.getObject() != null) {
                    HobbyDao hobbyDao = new HobbyDao();
                    for (HobbyDto hobbyDto : hobbyListModel.getObject()) {
                        HobbyEntity hobbyEntity = hobbyDto.getHobbyEntity();
                        insertFlg = hobbyDao.insert(userId, hobbyEntity);
                    }
                }
                if (insertFlg) {
                    setResponsePage(HomePage.class);
                } else {
                    error("insert error");
                }

            }

        });
        cancelButton = new Button("cancel") {
            @Override
            public void onSubmit() {
                setResponsePage(HomePage.class);
            }

        };
        cancelButton.setDefaultFormProcessing(false);
        form.add(cancelButton);

    }

}