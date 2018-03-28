package com.avides.vaadinpush.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("example-template")
@HtmlImport("src/example-template.html")
public class ExampleTemplate extends PolymerTemplate<ExampleTemplate.ExampleTemplateModel>
{
    public ExampleTemplate(String value)
    {
        getModel().setExampleValue(value);
    }

    public interface ExampleTemplateModel extends TemplateModel
    {
        void setExampleValue(String value);
    }
}
