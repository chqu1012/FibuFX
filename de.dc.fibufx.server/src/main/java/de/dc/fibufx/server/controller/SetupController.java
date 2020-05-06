package de.dc.fibufx.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dc.fibufx.server.setup.BuchungsvorgangInitializer;

@RestController
public class SetupController {

	@Autowired
	BuchungsvorgangInitializer initializer;

	@GetMapping("/setup")
	public void setup() {
		initializer.init();
	}
}
