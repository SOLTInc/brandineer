package com.solt_inc.page.registrationProfile.inputPanel.hobby;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.solt_inc.component.panel.fileUpload.FileUploadPanel;
import com.solt_inc.model.dto.HobbyDto;
import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.HobbyImageEntity;

public class HobbyInputPanel extends Panel {

    private IModel<List<HobbyDto>> hobbyDtoListModel;

    public HobbyInputPanel(String id, IModel<List<HobbyDto>> hobbyDtoListModel) {

        super(id, hobbyDtoListModel);
        this.hobbyDtoListModel = hobbyDtoListModel;
        Form<?> form = new Form<Void>("form");
        add(form);

        WebMarkupContainer wmc = new WebMarkupContainer("wmc");
        wmc.setOutputMarkupId(true);
        form.add(wmc);

        ListView<HobbyDto> hobbyList = this.createListView();
        wmc.add(hobbyList);

        AjaxButton addHobbyList = new AjaxButton("addHobbyList") {

            @Override
            public void onSubmit(AjaxRequestTarget target) {
                List<HobbyDto> hobbyList;

                if (hobbyDtoListModel.getObject() == null) {
                    hobbyList = new ArrayList<HobbyDto>();
                } else {
                    hobbyList = hobbyDtoListModel.getObject();
                }
                HobbyDto hobbyDto = new HobbyDto();
                HobbyEntity hobbyEntity = new HobbyEntity();
                hobbyDto.setHobbyEntity(hobbyEntity);
                HobbyImageEntity hobbyImageEntity = new HobbyImageEntity();
                hobbyDto.setHobbyImageEntity(hobbyImageEntity);
                hobbyList.add(hobbyDto);

                hobbyDtoListModel.setObject(hobbyList);
                target.add(wmc);
            }
        };
        form.add(addHobbyList);

    }

    private ListView<HobbyDto> createListView() {
        ListView<HobbyDto> hobbyList = new ListView<HobbyDto>("hobbyList", this.hobbyDtoListModel) {

            @Override
            protected void populateItem(ListItem<HobbyDto> item) {
                HobbyDto hobbyDto = (HobbyDto) item.getModelObject();
                HobbyEntity hobbyEntity = hobbyDto.getHobbyEntity();

                Label title = new Label("title", new Model<String>("Hobby No." + (item.getIndex() + 1)));
                item.add(title);

                IModel<String> hobbyNameModel = new PropertyModel<String>(hobbyEntity, "hobbyName");
                TextField<String> hobbyName = new TextField<String>("hobbyName", hobbyNameModel);
                item.add(hobbyName);

                IModel<String> hobbyDescriptionModel = new PropertyModel<String>(hobbyEntity, "description");
                TextArea<String> hobbyDescription = new TextArea<String>("hobbyDescription", hobbyDescriptionModel);
                item.add(hobbyDescription);

                IModel<String> photoName = new PropertyModel<String>(hobbyEntity, "hobbyName");
                IModel<String> photoPath = new Model<String>(getString("user.hobby.photo.path"));
                FileUploadPanel hobbyImagePanel = new FileUploadPanel("hobbyImage", photoName, photoPath);
                item.add(hobbyImagePanel);
            }

        };

        return hobbyList;
    }

    public IModel<List<HobbyDto>> getHobbyListModel() {

        return this.hobbyDtoListModel;
    }
}