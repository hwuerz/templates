package io.steep.faas;


import model.processchain.Executable;

public interface IHandler {
    String Handle(Executable request) throws Exception;
}
