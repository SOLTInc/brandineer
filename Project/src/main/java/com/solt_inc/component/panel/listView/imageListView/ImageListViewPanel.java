package com.solt_inc.component.panel.listView.imageListView;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.solt_inc.component.file.ImageFile;

class ImageListViewPanel extends Panel {

    public ImageListViewPanel(String id, IModel<List<ImageFile>> model) {
        super(id, model);
        Form<?> form = new Form<Void>("form");
        WebMarkupContainer wmc = new WebMarkupContainer("imageListConteiner");
        ImageListView imageListView = new ImageListView("imageList", model, wmc);

        queue(form);
        wmc.setOutputMarkupId(true);
        form.queue(wmc);
        wmc.queue(imageListView);

    }

    private class ImageListView extends ListView<ImageFile> {

        private WebMarkupContainer parentConteiner;

        public ImageListView(String id, IModel<List<ImageFile>> model, WebMarkupContainer parentContainer) {
            super(id, model);
            this.parentConteiner = parentContainer;
        }

        @Override
        protected void populateItem(ListItem<ImageFile> item) {
            ImageFile image = (ImageFile) item.getModelObject();

            WebMarkupContainer imageConteiner = new WebMarkupContainer("image");
            imageConteiner.add(new AttributeModifier("src", image.getImagePath()));

            AjaxLink<?> imgDeleteLink = new AjaxLink<Void>("imgDeleteLink") {

                @Override
                public void onClick(AjaxRequestTarget target) {

                    item.modelChanging();

                    getList().remove(item.getIndex());

                    ImageListView.this.modelChanged();
                    ImageListView.this.removeAll();
                    target.add(parentConteiner);
                }
            };

            item.queue(imageConteiner);
            item.queue(imgDeleteLink);

        }

        @Override
        public void onEvent(IEvent<?> event) {

            Object payload = event.getPayload();
            if (payload instanceof ImageFile) {
                ImageFile imageFile = (ImageFile) payload;
                this.getList().add(imageFile);
            }
        }
    }
}