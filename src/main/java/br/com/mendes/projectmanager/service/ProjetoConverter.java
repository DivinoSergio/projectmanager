package br.com.mendes.projectmanager.service;

import br.com.mendes.projectmanager.model.Projeto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

@Named
@ApplicationScoped
@FacesConverter(value = "projetoConverter", managed = true)
public class ProjetoConverter  implements Converter<Projeto> {

	@Override
	public Projeto getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Projeto arg2) throws ConverterException {
		// TODO Auto-generated method stub
		return null;
	}

}
