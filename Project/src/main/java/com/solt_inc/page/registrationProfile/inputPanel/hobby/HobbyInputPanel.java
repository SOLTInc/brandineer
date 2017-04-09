package com.solt_inc.page.registrationProfile.inputPanel.hobby;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.file.Folder;

import com.solt_inc.WicketApplication;
import com.solt_inc.component.file.ImageFile;
import com.solt_inc.component.folder.UploadFolder;
import com.solt_inc.component.panel.fileUpload.FileUploadPanel;
import com.solt_inc.model.dto.HobbyDto;
import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.HobbyImageEntity;

public class HobbyInputPanel extends Panel {

    private final UploadFolder UPLOAD_FOLDER = new UploadFolder(
            ((WicketApplication) Application.get()).getUploadFolder(),
            "user" + File.separator + "hobby" + File.separator + "image" + File.separator);
    private final IModel<Folder> uploadFolderModel = Model.of(UPLOAD_FOLDER);

    private List<HobbyDto> hobbyDtoList = new ArrayList<HobbyDto>();
    private IModel<List<HobbyDto>> hobbyListModel = new ListModel<HobbyDto>(hobbyDtoList);

    private Form<?> form = new Form<Void>("form") {
        @Override
        public void onSubmit() {

        }

    };

    private WebMarkupContainer wmc = new WebMarkupContainer("wmc");

    private ListView<HobbyDto> hobbyListView = new HobbyListView("hobbyList", hobbyListModel);

    private AjaxButton addHobbyList = new AjaxButton("addHobbyList") {
        private static final long serialVersionUID = 1L;

        @Override
        public void onSubmit(AjaxRequestTarget target) {
            if (hobbyListModel == null) {
                List<HobbyDto> hobbyList = new ArrayList<HobbyDto>();
                hobbyListModel.setObject(hobbyList);
            }
            List<HobbyDto> hobbyList = hobbyListModel.getObject();
            hobbyList.add(new HobbyDto());

            hobbyListView.setList(hobbyList);
            target.add(wmc);
        }
    };

    public HobbyInputPanel(String id, IModel<List<HobbyDto>> hobbyDtoListModel) {

        super(id, hobbyDtoListModel);
        this.hobbyListModel = hobbyDtoListModel;

        queue(form);
        wmc.setOutputMarkupId(true);
        form.queue(wmc);
        hobbyListView.setReuseItems(true);
        wmc.queue(hobbyListView);
        form.queue(addHobbyList);

    }

    private class HobbyListView extends ListView<HobbyDto> {

        public HobbyListView(String id, IModel<List<HobbyDto>> model) {
            super(id, model);
        }

        @Override
        protected void populateItem(ListItem<HobbyDto> item) {
            HobbyDto hobbyDto = (HobbyDto) item.getModelObject();

            if (hobbyDto.getHobbyEntity() == null) {
                HobbyEntity hobbyEntity = new HobbyEntity();
                hobbyDto.setHobbyEntity(hobbyEntity);
            }
            HobbyEntity hobbyEntity = hobbyDto.getHobbyEntity();

            Label title = new Label("title", Model.of("Hobby No." + (item.getIndex() + 1)));
            item.add(title);

            IModel<String> hobbyNameModel = LambdaModel.of(hobbyEntity::getHobbyName, hobbyEntity::setHobbyName);
            TextField<String> hobbyName = new TextField<String>("hobbyName", hobbyNameModel);
            item.add(hobbyName);

            IModel<String> hobbyDescriptionModel = LambdaModel.of(hobbyEntity::getDescription,
                    hobbyEntity::setDescription);
            TextArea<String> hobbyDescription = new TextArea<String>("hobbyDescription", hobbyDescriptionModel);
            item.add(hobbyDescription);

            WebMarkupContainer imageListContainer = new WebMarkupContainer("imageListContainer");
            imageListContainer.setOutputMarkupId(true);
            item.add(imageListContainer);

            IModel<ImageFile> hobbyImageModel = Model.of();
            List<HobbyImageEntity> hobbyImageEntityList = hobbyDto.getHobbyImageEntityList();
            IModel<List<HobbyImageEntity>> hobbyImageEntityListModel = new ListModel<HobbyImageEntity>(
                    hobbyImageEntityList);
            ListView<HobbyImageEntity> imageListView = new ImageListView("imageList", hobbyImageEntityListModel);
            imageListView.setReuseItems(true);
            imageListContainer.add(imageListView);

            FileUploadPanel hobbyImagePanel = new FileUploadPanel("uploadButtonPanel", hobbyImageModel,
                    uploadFolderModel, imageListContainer);
            item.add(hobbyImagePanel);
        }
    }

    private class ImageListView extends ListView<HobbyImageEntity> {

        public ImageListView(String id, IModel<List<HobbyImageEntity>> model) {
            super(id, model);
        }

        @Override
        protected void populateItem(ListItem<HobbyImageEntity> item) {
            HobbyImageEntity hobbyImageEntity = (HobbyImageEntity) item.getModelObject();

            WebMarkupContainer hobbyImage = new WebMarkupContainer("hobbyImage");
            hobbyImage
                    .add(new AttributeModifier("src", UPLOAD_FOLDER.getUploadPath() + hobbyImageEntity.getImageName()));
            item.add(hobbyImage);

        }

        @Override
        public void onEvent(IEvent<?> event) {

            Object payload = event.getPayload();
            if (payload instanceof ImageFile) {
                ImageFile imageFile = (ImageFile) payload;
                HobbyImageEntity hobbyImageEntity = new HobbyImageEntity();
                hobbyImageEntity.setImageName(imageFile.getName());
                this.getList().add(hobbyImageEntity);
            }
        }
    }
}