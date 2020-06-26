package de.fhg.igd.steep.faas;


import model.processchain.Executable;

public interface IHandler {
    String Handle(Executable request) throws Exception;
}
