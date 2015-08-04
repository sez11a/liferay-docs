package com.liferay.docs.exampleconfigportlet.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "com.liferay.docs.exampleconfigportlet.configuration.ExampleConfiguration")
public interface ExampleConfiguration {
	
	@Meta.AD(
        deflt = "es",
        required = false
    )
	public String defaultLanguage();
	
	@Meta.AD(
		deflt = "en|es|pt",
		required = false
    )
	public String validLanguages();
	
	@Meta.AD(required = false)
	public int itemsPerPage();

}
