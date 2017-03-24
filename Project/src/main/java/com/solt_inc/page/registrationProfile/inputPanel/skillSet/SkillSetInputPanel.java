package com.solt_inc.page.registrationProfile.inputPanel.skillSet;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;

import com.solt_inc.model.dao.DevelopmentProcessDao;
import com.solt_inc.model.dto.SkillSetDto;
import com.solt_inc.model.entity.DevelopmentProcessEntity;
import com.solt_inc.model.entity.SkillSetEntity;

public class SkillSetInputPanel extends Panel {

    private List<SkillSetDto> skillSet;
    private IModel<List<SkillSetDto>> skillSetListModel;
    private ListView<SkillSetDto> skillSetList;

    public SkillSetInputPanel(String id, IModel<List<SkillSetDto>> skillSetListModel1) {

        super(id, skillSetListModel1);

        this.skillSetListModel = skillSetListModel1;

        Form<?> form = new Form<Void>("form");
        add(form);

        WebMarkupContainer wmc = new WebMarkupContainer("wmc");
        form.add(wmc);
        wmc.setOutputMarkupId(true);

        this.skillSetListModel = new ListModel<SkillSetDto>();
        this.skillSetList = this.createListView();
        wmc.add(this.skillSetList);

        AjaxButton addSkillSetButton = new AjaxButton("addSkillSet") {
            @Override
            public void onSubmit(final AjaxRequestTarget target) {

                if (skillSet == null) {
                    skillSet = new ArrayList<SkillSetDto>();
                } else {
                    skillSet = skillSetListModel.getObject();
                }

                SkillSetDto skillSetDto = new SkillSetDto();
                SkillSetEntity skillSetEntity = new SkillSetEntity();
                skillSetDto.setSkillSetEntity(skillSetEntity);
                DevelopmentProcessEntity processStartEntity = new DevelopmentProcessEntity();
                skillSetDto.setProcessStartEntity(processStartEntity);
                DevelopmentProcessEntity processEndEntity = new DevelopmentProcessEntity();
                skillSetDto.setProcessEndEntity(processEndEntity);
                skillSet.add(skillSetDto);
                skillSetListModel.setObject(skillSet);

                target.add(wmc);

            }
        };
        form.add(addSkillSetButton);
    }

    private ListView<SkillSetDto> createListView() {

        List<DevelopmentProcessEntity> processList;
        DevelopmentProcessDao developmentProcessDao = new DevelopmentProcessDao();

        processList = developmentProcessDao.getProcessList();

        skillSetList = new ListView<SkillSetDto>("skillSetList", this.skillSetListModel) {
            @Override
            protected void populateItem(ListItem<SkillSetDto> item) {

                SkillSetDto skillSetDto = (SkillSetDto) item.getModelObject();
                SkillSetEntity skillSetEntity = skillSetDto.getSkillSetEntity();
                DevelopmentProcessEntity processStartEntity = skillSetDto.getProcessStartEntity();
                DevelopmentProcessEntity processEndEntity = skillSetDto.getProcessEndEntity();

                List<Integer> years = new ArrayList<Integer>();
                for (int year = 1; year <= 12; year++) {
                    years.add(year);
                }
                List<Integer> months = new ArrayList<Integer>();
                for (int month = 1; month <= 31; month++) {
                    months.add(month);
                }

                Label title = new Label("title", new Model<String>("SkillSet No." + (item.getIndex() + 1)));
                item.add(title);

                IModel<Integer> projectStartYearModel = new Model<Integer>();
                DropDownChoice<Integer> projectStartYear = new DropDownChoice<Integer>("projectStartYear",
                        projectStartYearModel, years);
                item.add(projectStartYear);
                IModel<Integer> projectStartMonthModel = new Model<Integer>();
                DropDownChoice<Integer> projectStartMonth = new DropDownChoice<Integer>("projectStartMonth",
                        projectStartMonthModel, months);
                item.add(projectStartMonth);

                IModel<Integer> projectEndYearModel = new Model<Integer>();
                DropDownChoice<Integer> projectEndYear = new DropDownChoice<Integer>("projectEndYear",
                        projectEndYearModel, years);
                item.add(projectEndYear);
                IModel<Integer> projectEndMonthModel = new Model<Integer>();
                DropDownChoice<Integer> projectEndMonth = new DropDownChoice<Integer>("projectEndMonth",
                        projectEndMonthModel, months);
                item.add(projectEndMonth);

                IModel<String> projectNameModel = new PropertyModel<String>(skillSetEntity, "projectName");
                TextField<String> projectName = new TextField<String>("projectName", projectNameModel);
                item.add(projectName);

                IModel<String> projectDescriptionModel = new PropertyModel<String>(skillSetEntity,
                        "projectDescription");
                TextField<String> projectDescription = new TextField<String>("projectDescription",
                        projectDescriptionModel);
                item.add(projectDescription);

                // if (skillSetEntity.getProcessStart() != 0 &&
                // processStartEntity.getId() == 0) {
                // processStartEntity =
                // DevelopmentProcessDao.getProcess(skillSetEntity.getProcessStart());
                // }
                IModel<DevelopmentProcessEntity> processStartModel = new Model<DevelopmentProcessEntity>(
                        processStartEntity);
                DropDownChoice<DevelopmentProcessEntity> processStartList = new DropDownChoice<DevelopmentProcessEntity>(
                        "processStart", processStartModel, processList, new ChoiceRenderer("processName", "id"));
                item.add(processStartList);

                // if (skillSetEntity.getProcessEnd() != 0 &&
                // processEndEntity.getId() == 0) {
                // processEndEntity =
                // DevelopmentProcessDao.getProcess(skillSetEntity.getProcessEnd());
                //
                // }
                IModel<DevelopmentProcessEntity> processEndModel = new Model<DevelopmentProcessEntity>(
                        processEndEntity);
                DropDownChoice<DevelopmentProcessEntity> processEndList = new DropDownChoice<DevelopmentProcessEntity>(
                        "processEnd", processEndModel, processList, new ChoiceRenderer("processName", "id"));
                item.add(processEndList);

                IModel<String> usedProgrammingLanguageModel = new PropertyModel<String>(skillSetEntity, "DB");
                TextField<String> usedProgrammingLanguage = new TextField<String>("programmingLanguage",
                        usedProgrammingLanguageModel);
                item.add(usedProgrammingLanguage);

                IModel<String> usedDBModel = new PropertyModel<String>(skillSetEntity, "DB");
                TextField<String> usedDB = new TextField<String>("usedDB", usedDBModel);
                item.add(usedDB);

                IModel<String> usedIDEModel = new PropertyModel<String>(skillSetEntity, "IDE");
                TextField<String> usedIDE = new TextField<String>("usedIDE", usedIDEModel);
                item.add(usedIDE);

            }
        };
        return skillSetList;

    }

    public IModel<List<SkillSetDto>> getSkillSetListModel() {

        return this.skillSetListModel;
    }

}