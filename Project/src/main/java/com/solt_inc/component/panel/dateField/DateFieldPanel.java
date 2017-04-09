package com.solt_inc.component.panel.dateField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class DateFieldPanel extends Panel {

    private IModel<Integer> year = new Model<Integer>();
    private IModel<Integer> month = new Model<Integer>();
    private IModel<Integer> day = new Model<Integer>();
    private IModel<LocalDate> date;
    private List<Integer> years = new ArrayList<Integer>();
    private List<Integer> months = new ArrayList<Integer>();
    private List<Integer> dates = new ArrayList<Integer>();

    private Form<?> form = new Form("form") {
        @Override
        public void onSubmit() {
            if (year.getObject() != null && month.getObject() != null && day.getObject() != null) {
                date.setObject(LocalDate.of(year.getObject(), month.getObject(), day.getObject()));
            }
        }
    };
    private DropDownChoice<Integer> yearsList = new DropDownChoice<Integer>("years", this.year, years);
    private DropDownChoice<Integer> monthsList = new DropDownChoice<Integer>("months", this.month, months);
    private DropDownChoice<Integer> datesList = new DropDownChoice<Integer>("dates", this.day, dates);

    public DateFieldPanel(String id, IModel<LocalDate> date) {
        super(id);
        this.date = date;

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

        add(this.form);
        form.add(yearsList);
        form.add(monthsList);
        form.add(datesList);
    }

    // public IModel<LocalDate> getDate() {

    // this.date = new Model<LocalDate>(); if (this.year.getObject() != null
    // &&
    // this.month.getObject() != null && this.day.getObject() != null) {
    // this.date.setObject(LocalDate.of(year.getObject(), month.getObject(),
    // day.getObject())); }

    // return this.date;
    // }

}
