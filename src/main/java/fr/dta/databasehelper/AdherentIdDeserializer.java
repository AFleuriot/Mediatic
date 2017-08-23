package fr.dta.databasehelper;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import fr.dta.adherent.dao.AdherentDAO;
import fr.dta.adherent.modele.Adherent;

public class AdherentIdDeserializer extends StdDeserializer<Adherent> {
	
	@Autowired
	AdherentDAO dao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected AdherentIdDeserializer() {
		super(Integer.class);

	}

	@Override
	public Adherent deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		try {
			return dao.findOne(p.readValueAs(Integer.class));
		}
		catch(IOException e) {
			return p.readValueAs(Adherent.class);
		}
	}

}
