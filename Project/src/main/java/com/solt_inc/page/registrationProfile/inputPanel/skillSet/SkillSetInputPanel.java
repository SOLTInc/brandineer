package com.solt_inc.page.registrationProfile.inputPanel.skillSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.solt_inc.component.file.ImageFile;
import com.solt_inc.component.panel.dateField.DateFieldPanel;
import com.solt_inc.model.dao.DevelopmentProcessDao;
import com.solt_inc.model.dto.SkillSetDto;
import com.solt_inc.model.entity.DevelopmentProcessEntity;
import com.solt_inc.model.entity.SkillSetEntity;

public class SkillSetInputPanel extends Panel {

    private IModel<List<SkillSetDto>> skillSetListModel = new ListModel<SkillSetDto>(new ArrayList<SkillSetDto>());

    private Form<?> form = new Form<Void>("form");
    private WebMarkupContainer wmc = new WebMarkupContainer("wmc");
    private AjaxButton addSkillSetButton = new AjaxButton("addSkillSet") {
        private static final long serialVersionUID = 1L;

        @Override
        public void onSubmit(final AjaxRequestTarget target) {

            if (skillSetListModel == null) {
                List<SkillSetDto> skillSetDtoList = new ArrayList<SkillSetDto>();
                skillSetListModel = new ListModel<SkillSetDto>(skillSetDtoList);
            }

            List<SkillSetDto> skillSetDtoList = skillSetListModel.getObject();
            skillSetDtoList.add(new SkillSetDto());

            skillSetList.setList(skillSetDtoList);

            target.add(wmc);

        }

    };
    private SkillSetListView skillSetList = new SkillSetListView("skillSetList", skillSetListModel);

    public SkillSetInputPanel(String id, IModel<List<SkillSetDto>> skillSetListModel1) {

        super(id, skillSetListModel1);
        this.skillSetListModel = skillSetListModel1;

        queue(form);
        wmc.setOutputMarkupId(true);
        skillSetList.setReuseItems(true);
        wmc.queue(skillSetList);

        form.queue(wmc);
        form.queue(addSkillSetButton);
    }

    class SkillSetListView extends ListView<SkillSetDto> {

        private static final long serialVersionUID = 6483489885119506981L;

        public SkillSetListView(String id, IModel<? extends List<SkillSetDto>> model) {
            super(id, model);
        }

        @Override
        protected void populateItem(ListItem<SkillSetDto> item) {
            SkillSetDto skillSetDto = (SkillSetDto) item.getModelObject();
            SkillSetEntity skillSetEntity;
            if (skillSetDto.getSkillSetEntity() != null) {
                skillSetEntity = skillSetDto.getSkillSetEntity();
            } else {
                skillSetEntity = new SkillSetEntity();
                skillSetDto.setSkillSetEntity(skillSetEntity);
            }
            if (skillSetDto.getProcessStartEntity() == null) {
                skillSetDto.setProcessStartEntity(new DevelopmentProcessEntity());
            }
            if (skillSetDto.getProcessEndEntity() == null) {
                skillSetDto.setProcessEndEntity(new DevelopmentProcessEntity());
            }
            DevelopmentProcessDao processDao = new DevelopmentProcessDao();
            List<DevelopmentProcessEntity> processList = processDao.getProcessList();
            IModel<List<DevelopmentProcessEntity>> processListModel = new ListModel<DevelopmentProcessEntity>(
                    processList);
            IChoiceRenderer<DevelopmentProcessEntity> processRender = new ChoiceRenderer<DevelopmentProcessEntity>(
                    "processName", "id");

            IModel<Integer> listIndexModel = Model.of(item.getIndex());
            IModel<LocalDate> projectStartModel = LambdaModel.of(skillSetEntity::getProjectStart,
                    skillSetEntity::setProjectStart);
            IModel<LocalDate> projectEndModel = LambdaModel.of(skillSetEntity::getProjectEnd,
                    skillSetEntity::setProjectEnd);
            IModel<String> projectNameModel = LambdaModel.of(skillSetEntity::getProjectName,
                    skillSetEntity::setProjectName);
            IModel<String> projectDescriptionModel = LambdaModel.of(skillSetEntity::getProjectDescription,
                    skillSetEntity::setProjectDescription);
            IModel<DevelopmentProcessEntity> processStartModel = LambdaModel.of(skillSetDto::getProcessStartEntity,
                    skillSetDto::setProcessStartEntity);
            IModel<DevelopmentProcessEntity> processEndModel = LambdaModel.of(skillSetDto::getProcessEndEntity,
                    skillSetDto::setProcessEndEntity);
            IModel<String> programmingLanguageModel = LambdaModel.of(skillSetEntity::getProgrammingLanguage,
                    skillSetEntity::setProgrammingLanguage);
            IModel<String> dbModel = LambdaModel.of(skillSetEntity::getDB, skillSetEntity::setDB);
            IModel<String> ideModel = LambdaModel.of(skillSetEntity::getIDE, skillSetEntity::setIDE);

            TextField<Integer> listIndex = new TextField<Integer>("listIndex", listIndexModel);
            item.queue(listIndex);

            AjaxButton removeLink = new AjaxButton("removeLink") {

                @Override
                public void onSubmit(AjaxRequestTarget target) {

                    item.modelChanging();
                    getList().remove(item.getIndex());
                    SkillSetListView.this.modelChanged();
                    SkillSetListView.this.removeAll();

                    target.add(wmc);

                }

            };

            IModel<List<ImageFile>> imageListModel = new ListModel<ImageFile>();
            // ImageListViewPanel imageListViewPanel = new
            // ImageListViewPanel("imageListPanel", imageListModel);
            Label title = new Label("title", Model.of("SkillSet No." + (item.getIndex() + 1)));
            DateFieldPanel projectStartPanel = new DateFieldPanel("projectStartPanel", projectStartModel);
            DateFieldPanel projectEndPanel = new DateFieldPanel("projectEndPanel", projectEndModel);
            TextField<String> projectName = new TextField<String>("projectName", projectNameModel);
            TextField<String> projectDescription = new TextField<String>("projectDescription", projectDescriptionModel);
            DropDownChoice<DevelopmentProcessEntity> processStart = new DropDownChoice<DevelopmentProcessEntity>(
                    "processStart", processStartModel, processListModel, processRender);
            DropDownChoice<DevelopmentProcessEntity> processEnd = new DropDownChoice<DevelopmentProcessEntity>(
                    "processEnd", processEndModel, processListModel, processRender);

            TextField<String> programmingLanguage = new TextField<String>("programmingLanguage",
                    programmingLanguageModel);
            TextField<String> usedDB = new TextField<String>("usedDB", dbModel);
            TextField<String> usedIDE = new TextField<String>("usedIDE", ideModel);

            item.queue(title);
            item.queue(removeLink);

            item.queue(projectStartPanel);
            item.queue(projectEndPanel);
            item.queue(projectName);
            item.queue(projectDescription);
            item.queue(processStart);
            item.queue(processEnd);
            item.queue(programmingLanguage);
            item.queue(usedDB);
            item.queue(usedIDE);

        }

    }

}