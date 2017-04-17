package com.solt_inc.page.editProfile.editPanel.hobby;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
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
import com.solt_inc.model.dao.HobbyDao;
import com.solt_inc.model.dao.HobbyImageDao;
import com.solt_inc.model.dto.HobbyDto;
import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.HobbyImageEntity;

public class EditHobbyPanel extends Panel {

	private static final long serialVersionUID = 1L;
	private final UploadFolder UPLOAD_FOLDER = new UploadFolder(
            ((WicketApplication) Application.get()).getUploadFolder(),
            "user" + File.separator + "hobby" + File.separator + "image" + File.separator);

    private List<HobbyDto> hobbyDtoList = new ArrayList<HobbyDto>();
    private IModel<List<HobbyDto>> hobbyListModel = new ListModel<HobbyDto>(hobbyDtoList);

    public EditHobbyPanel(String id, IModel<List<HobbyDto>> hobbyDtoListModel, IModel<Integer> userIdModel) {

        super(id, hobbyDtoListModel);
        this.hobbyListModel = hobbyDtoListModel;
        
        hobbyListModel.setObject(getHobbyList(userIdModel.getObject()));


     Form<?> form = new Form<Void>("form") {

		private static final long serialVersionUID = 1L;
		@Override
        public void onSubmit() {
        }

    };

     WebMarkupContainer wmc = new WebMarkupContainer("wmc");

     ListView<HobbyDto> hobbyListView = new HobbyListView("hobbyList", hobbyListModel, wmc);

     AjaxButton addHobbyList = new AjaxButton("addHobbyList") {
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


        queue(form);
        wmc.setOutputMarkupId(true);
        form.queue(wmc);
        hobbyListView.setReuseItems(true);
        wmc.queue(hobbyListView);
        form.queue(addHobbyList);

    }
    private List<HobbyDto> getHobbyList(int userId) {
    	List<HobbyDto> hobbyDtoList = new ArrayList<HobbyDto>();
    	HobbyDao hobbyDao = new HobbyDao();
    	HobbyImageDao hobbyImageDao = new HobbyImageDao();
    	List<HobbyEntity> hobbyEntityList = hobbyDao.getUserHobby(userId);
    	for(HobbyEntity entity: hobbyEntityList) {
    		HobbyDto hobbyDto = new HobbyDto();
    		hobbyDto.setHobbyEntity(entity);
    		hobbyDto.setHobbyImageEntityList(hobbyImageDao.getHobbyImage(entity.getId()));
    		hobbyDtoList.add(hobbyDto);
    	}
    	
    	return hobbyDtoList;
    }

    private class HobbyListView extends ListView<HobbyDto> {

		private static final long serialVersionUID = 1L;

		WebMarkupContainer wmc;

		public HobbyListView(String id, IModel<List<HobbyDto>> model, WebMarkupContainer wmc) {
            super(id, model);
            this.wmc = wmc;
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
            item.queue(title);

            AjaxLink<?> removeLink = new AjaxLink<Void>("removeLink") {

                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public void onClick(AjaxRequestTarget target) {

                    item.modelChanging();
                    getList().remove(item.getIndex());
                    HobbyListView.this.modelChanged();
                    HobbyListView.this.removeAll();

                    target.add(wmc);
                }

            };
            item.queue(removeLink);

            IModel<String> hobbyNameModel = LambdaModel.of(hobbyEntity::getHobbyName, hobbyEntity::setHobbyName);
            TextField<String> hobbyName = new TextField<String>("hobbyName", hobbyNameModel);
            item.queue(hobbyName);

            IModel<String> hobbyDescriptionModel = LambdaModel.of(hobbyEntity::getDescription,
                    hobbyEntity::setDescription);
            TextArea<String> hobbyDescription = new TextArea<String>("hobbyDescription", hobbyDescriptionModel);
            item.queue(hobbyDescription);

            WebMarkupContainer imageListContainer = new WebMarkupContainer("imageListContainer");
            imageListContainer.setOutputMarkupId(true);
            item.queue(imageListContainer);

            List<HobbyImageEntity> hobbyImageEntityList = hobbyDto.getHobbyImageEntityList();
            IModel<List<HobbyImageEntity>> hobbyImageEntityListModel = new ListModel<HobbyImageEntity>(
                    hobbyImageEntityList);
            ListView<HobbyImageEntity> imageListView = new ImageListView("imageList", hobbyImageEntityListModel,
                    imageListContainer);
            imageListView.setReuseItems(true);
            imageListContainer.queue(imageListView);

            FileUploadPanel hobbyImagePanel = new FileUploadPanel("uploadButtonPanel",
                    UPLOAD_FOLDER, imageListContainer);
            item.queue(hobbyImagePanel);
        }
    }

    private class ImageListView extends ListView<HobbyImageEntity> {

		private static final long serialVersionUID = 1L;
		private WebMarkupContainer parentConteiner;

        public ImageListView(String id, IModel<List<HobbyImageEntity>> model, WebMarkupContainer parentContainer) {
            super(id, model);
            this.parentConteiner = parentContainer;
        }

        @Override
        protected void populateItem(ListItem<HobbyImageEntity> item) {
            HobbyImageEntity hobbyImageEntity = (HobbyImageEntity) item.getModelObject();

            WebMarkupContainer hobbyImage = new WebMarkupContainer("hobbyImage");
            hobbyImage
                    .add(new AttributeModifier("src", UPLOAD_FOLDER.getUploadPath() + hobbyImageEntity.getImageName()));

            AjaxLink<?> imgDeleteLink = new AjaxLink<Void>("imgDeleteLink") {

				private static final long serialVersionUID = 1L;

				@Override
                public void onClick(AjaxRequestTarget target) {

                    item.modelChanging();

                    getList().remove(item.getIndex());

                    ImageListView.this.modelChanged();
                    ImageListView.this.removeAll();
                    target.add(parentConteiner);
                }
            };

            item.queue(hobbyImage);
            item.queue(imgDeleteLink);

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