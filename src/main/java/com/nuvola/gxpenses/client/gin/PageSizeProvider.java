package com.nuvola.gxpenses.client.gin;

import com.nuvola.gxpenses.client.BootStrapper;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class PageSizeProvider implements Provider<Integer> {

    private final BootStrapper bootStrapper;
	
	@Inject
	public PageSizeProvider(final BootStrapper bootStrapper) {
		this.bootStrapper = bootStrapper;
	}	

	@Override
	public Integer get() {
		return bootStrapper.getCurrentUser().getPageSize().getValue();
	}

}
