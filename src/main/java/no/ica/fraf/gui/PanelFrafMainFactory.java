package no.ica.fraf.gui;

import no.ica.fraf.model.ApplUser;

public interface PanelFrafMainFactory {
	PanelFrafMainInterface create(ApplUser applUser);
}
