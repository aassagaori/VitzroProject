package com.vitzro.dto;

public interface IReceivedMessage extends IMessage {

	public void decode(byte[] array);
}
