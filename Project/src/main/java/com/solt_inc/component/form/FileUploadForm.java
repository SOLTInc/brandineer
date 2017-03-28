package com.solt_inc.component.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.lang.Bytes;

public class FileUploadForm extends Form<Void> {

    public FileUploadForm(String id) {
        super(id);

        setMultiPart(true);
        // total
        setMaxSize(Bytes.kilobytes(100));
        // each
        setFileMaxSize(Bytes.kilobytes(100));

    }

}
