package com.elvis.springapp.springrecipe.Service;

import java.util.Set;

import com.elvis.springapp.springrecipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
	Set<UnitOfMeasureCommand> listAllUoMs();
}
