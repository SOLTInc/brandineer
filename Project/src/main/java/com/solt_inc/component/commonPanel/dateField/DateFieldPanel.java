package com.solt_inc.component.commonPanel.dateField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class DateFieldPanel extends Panel {

    private Form<?> form;
    private IModel<Integer> year = new Model<Integer>();
    private IModel<Integer> month = new Model<Integer>();
    private IModel<Integer> day = new Model<Integer>();
    private IModel<LocalDate> date;

    public DateFieldPanel(String id, IModel<LocalDate> date) {
        super(id);
        this.date = date;

        this.form = new Form("form");
        add(this.form);
        createSelectListDate();
    }

    private void createSelectListDate() {

        List<Integer> years = new ArrayList<Integer>();
        List<Integer> months = new ArrayList<Integer>();
        List<Integer> dates = new ArrayList<Integer>();

        int thisYear = LocalDate.now().getYear();
        for (int i = 1917; i <= thisYear; i++) {
            years.add(i);
        }
        for (int i = 1; i <= 12; i++) {
            months.add(i);
        }
        for (int i = 1; i <= 31; i++) {
            dates.add(i);
        }

        DropDownChoice<Integer> yearsList = new DropDownChoice<Integer>("years", this.year, years);
        form.add(yearsList);
        DropDownChoice<Integer> monthsList = new DropDownChoice<Integer>("months", this.month, months);
        form.add(monthsList);
        DropDownChoice<Integer> datesList = new DropDownChoice<Integer>("dates", this.day, dates);
        form.add(datesList);

    }

    public IModel<LocalDate> getDate() {

        this.date = new Model<LocalDate>();
        if (this.year.getObject() != null && this.month.getObject() != null && this.day.getObject() != null) {
            this.date.setObject(LocalDate.of(year.getObject(), month.getObject(), day.getObject()));
        }

        return this.date;
    }
}
