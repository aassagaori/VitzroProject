package com.vitzro.dto;

public interface IReceivedMessage<I> extends IMessage {

	public void decode(I i);
}
